# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "package-management"

# Include modules in rootfs
IMAGE_INSTALL += " \
        connman \
        calaos-version \
	"
IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "linux-firmware"
IMAGE_INSTALL += "tzdata tzdata-europe ntp"
IMAGE_INSTALL += "avahi-utils"
IMAGE_FEATURES += "ssh-server-dropbear splash"
IMAGE_INSTALL += "nano htop procps"
IMAGE_INSTALL += "openssl ca-certificates"
IMAGE_INSTALL += "cpu-performance"
IMAGE_INSTALL += "connman-wait-online-calaos connman-client connman-autoconnect"
IMAGE_INSTALL += "wget"
IMAGE_INSTALL += "pciutils usbutils"
#Install the real less to make systemctl display correctly colors
IMAGE_INSTALL += "less"

#disable syslinux serial IO
SYSLINUX_SERIAL = ""
SYSLINUX_OPTS = ""
SYSLINUX_SERIAL_TTY = ""
SYSLINUX_SPLASH = "${THISDIR}/files/calaos.png"

#disable serial console on intel platforms
SERIAL_CONSOLES_intel-corei7-64 = ""
SERIAL_CONSOLES_intel-corei2-32 = ""
APPEND_intel-corei7-64 = " rootwait"
APPEND_intel-corei2-32 = " rootwait"

IMAGE_PREPROCESS_COMMAND_append = " symlink_lib64; "

#Add link to /lib64 -> /lib for some packages to work
symlink_lib64() {
    ln -s /lib ${IMAGE_ROOTFS}/lib64
}

