# Simple initramfs image. Mostly used for live images.
DESCRIPTION = "Calaos OS Update image"

PACKAGE_INSTALL = "busybox \
		udev \
		base-passwd \
		util-linux \
		btrfs-tools \
		initramfs-boot \
		${ROOTFS_BOOTSTRAP_INSTALL} \
		"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "calaos-os-update"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

BAD_RECOMMENDATIONS += "busybox-syslog"

# static /dev for /deV/null and /dev/console
USE_DEVFS = "0"

