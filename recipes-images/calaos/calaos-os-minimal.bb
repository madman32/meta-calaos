# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "package-management"

# Include modules in rootfs
IMAGE_INSTALL += " \
        connman \
        calaos-version \
	"
IMAGE_INSTALL += "tzdata tzdata-europe ntp"
IMAGE_INSTALL += "avahi-utils"
IMAGE_FEATURES += "ssh-server-dropbear splash"
IMAGE_INSTALL += "nano htop procps"
IMAGE_INSTALL += "openssl ca-certificates"
IMAGE_INSTALL += "cpu-performance"
IMAGE_INSTALL += "connman-wait-online connman-client connman-autoconnect"
IMAGE_INSTALL += "wget"

#disable syslinux serial IO
SYSLINUX_SERIAL = ""
SYSLINUX_SERIAL_TTY = ""