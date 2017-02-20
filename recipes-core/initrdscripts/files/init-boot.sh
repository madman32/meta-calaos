#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

mkdir /proc
mkdir /sys
mount -t proc proc /proc
mount -t sysfs sysfs /sys

mknod -m 600 /dev/loop0 b 7 0
mknod -m 600 /dev/loop1 b 7 1

root_device=$(cat /proc/cmdline)
root_device="${root_device##*root=}"
root_device="${root_device%% *}"

#Get parent name of root device
root_block=$(lsblk -n -o PKNAME $root_device)

#Write new partitions
echo -e "n\np\n3\n1114112\n+512M\nn\np\n4\n2162688\n\n\nw" | fdisk /dev/$root_block

#Reread the partition table the parent
#blockdev --rereadpt /dev/$root_block

#Format System #2 Partition
mkfs.ext4 /dev/${root_block}p3
#Format User partition
mkfs.ext4 /dev/${root_block}p4


# mount sdcard
mkdir -p /rootfs
mkdir -p /mnt/root-ro
mkdir -p /mnt/root-rw

echo "Root device : $root_device"

# Mount calaos-os-user as home partition
mount --options ro /dev/${root_block}p2 /mnt/root-ro
mount --options rw /dev/${root_block}p4 /mnt/root-rw

mkdir -p /mnt/root-rw/upperdir
mkdir -p /mnt/root-rw/workdir

mount -t overlay overlay -o lowerdir=/mnt/root-ro,upperdir=/mnt/root-rw/upperdir,workdir=/mnt/root-rw/workdir /rootfs

/calaos-upgrade.sh

# Switch root
cd /rootfs
exec switch_root . /sbin/init

echo "Unable to switch root and boot calaos-os system, launching rescue shell ..."
exec /bin/sh
