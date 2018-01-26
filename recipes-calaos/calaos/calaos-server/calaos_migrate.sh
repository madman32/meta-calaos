#!/bin/sh

set -e #stop if any error occurs
set -o nounset #stop if variable are uninitialised

tmpdir="/tmp/old_calaos"

# Run as root, of course.
if [ "$(id -u)" != "0" ]
then
    echo "Must be root to run this script."
    exit 1
fi

echo "============================================"
echo "        Calaos-OS Migration script"
echo "============================================"
echo

clean_temp()
{
    set +e
    #clean tempdir if needed
    mkdir -p $tmpdir
    umount $tmpdir > /dev/null 2>&1
    set -e
}

clean_temp

echo "[*] Searching for an old calaos installation..."
#search for an old calaos partition
cdrive=""
while IFS='' read -r line || [[ -n "$line" ]]
do
	drive=$(echo $line | cut -d' ' -f4)
	if [ "${drive:0:3}" == "ram" ]; then continue; fi
	if [ "${drive:0:4}" == "loop" ]; then continue; fi
	
	if [ ! -b /dev/$drive ]; then continue; fi
	
	set +e
	mount /dev/$drive $tmpdir > /dev/null 2>&1
	if [ $? -eq 0 ]; then
		if [ -d $tmpdir/etc/calaos ]
		then
			cdrive=$drive
			break
		fi
	fi
	set -e
done < /proc/partitions

if [ x$cdrive == x ]
then
	echo "No calaos installation found! Cannot migrate."
	echo "Are you sure a calaos-os was installed?"
	echo "Aborting."
	exit 1
fi

echo
echo "A calaos-os installation was found on /dev/$cdrive"
echo "The migration will continue using this disk."
echo
read -p "Continue (y/[N])?" choice
case "$choice" in 
  y|Y ) echo "[*] Starting migration process...";;
  * ) echo "Aborted."; exit 1;;
esac

#stop running services before changing config files
systemctl stop calaos-server
systemctl stop calaos-home
systemctl stop haproxy
systemctl stop olad
systemctl stop knxd


echo "[*] Mounting old system"
set +e
umount /dev/$drive > /dev/null 2>&1
set -e
mount /dev/$cdrive $tmpdir

echo "[*] Copy calaos configuration"
cp -R $tmpdir/etc/calaos /etc

echo "[*] Copy systemd service files"
dir=$(pwd)
cd $tmpdir/etc/systemd/system/multi-user.target.wants/

is_service() {
	if [ "$1" == "calaos-home.service" ]; then return 0; fi
	if [ "$1" == "calaos-server.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyUSB0.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyUSB1.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyUSB2.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyUSB3.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyS0.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyS1.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyS2.service" ]; then return 0; fi
	if [ "$1" == "usb-serial-touchscreen@ttyS3.service" ]; then return 0; fi
	if [ "$1" == "olad.service" ]; then return 0; fi
	if [ "$1" == "knxd.service" ]; then return 0; fi
	return 1
}

for s in *.service
do
    if ! is_service "$s"
    then
        continue
    fi

    if [ -e $tmpdir/etc/systemd/system/multi-user.target.wants/$s ]
    then
        if [ ! -e /etc/systemd/system/multi-user.target.wants/$s ]
        then
            #service is not enabled
            echo " - Enabling service: $s"
            systemctl enable $s
        fi
    else
        if [ -e /etc/systemd/system/multi-user.target.wants/$s ]
        then
            #service was disabled, but is enabled on new os
            echo " - Disabling service: $s"
            systemctl disable $s
        fi
    fi
done
cd $dir

echo "[*] Copy touchscreen calibration data"
dir=$(pwd)

is_xconf() {
	if [ "$1" == "10-evdev.conf" ]; then return 0; fi
	if [ "$1" == "10-quirks.conf" ]; then return 0; fi
	if [ "$1" == "50-synaptics.conf" ]; then return 0; fi
	return 1
}

cd $tmpdir/usr/share/X11/xorg.conf.d
for c in *
do
    if is_xconf "$c"
    then
        continue
    fi

	echo "  copying $c"
	test -d /usr/share/X11/xorg.conf.d && cp $tmpdir/usr/share/X11/xorg.conf.d/$c /usr/share/X11/xorg.conf.d/$c
done
cd $dir

echo "[*] Copy ssh keys and root password"
test -d $tmpdir/home/root/.ssh && cp -R $tmpdir/home/root/.ssh /home/root/
rootpw=$(cat $tmpdir/etc/shadow | grep "root:")
sed -i "s/^root:.*\$/$(echo $rootpw | sed -e 's/[]\/$*.^|[]/\\&/g')/g" /etc/shadow

echo "[*] Setup timezone"
cp -P $tmpdir/etc/localtime /etc/localtime
systemctl stop ntpd
systemctl start ntpd

echo "[*] Setup locale"
cp $tmpdir/etc/locale.conf /etc/locale.conf
lc=$(cat $tmpdir/etc/locale.conf | cut -d= -f2)
km=$(cat $tmpdir/etc/vconsole.conf | cut -d= -f2)
xk=$(cat $tmpdir/etc/X11/xorg.conf.d/00-keyboard.conf | grep XkbLayout | cut -d\" -f4)

localectl set-locale LANG="$lc"
localectl set-keymap $km
localectl set-x11-keymap $xk

echo "[*] Install SSL certificate"
if [ -e $tmpdir/etc/ssl/haproxy/server.pem ]
then
    cp $tmpdir/etc/ssl/haproxy/server.pem /etc/ssl/haproxy/server.pem
elif [ -e $tmpdir/etc/lighttpd/server.pem ]
then
    cp $tmpdir/etc/lighttpd/server.pem /etc/ssl/haproxy/server.pem
fi

if [ -e $tmpdir/etc/default/eibnetmux ]
then
	echo "[*] Copy eibnetmux configuration"
	cp $tmpdir/etc/default/eibnetmux /etc/default/eibnetmux
fi

if [ -d $tmpdir/etc/ola ]
then
	echo "[*] Copy OLA configuration"
	cp -R $tmpdir/etc/ola /etc/
fi

echo "[*] Copy network configuration"
cp -R $tmpdir/var/lib/connman/* /var/lib/connman

sync
umount $tmpdir

echo "[*] Killing network"
killall -9 connmand

echo "--------------------------"
echo "Migration successfully done."
echo
echo "Rebooting now."
echo

reboot

