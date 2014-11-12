inherit image_types

# Set initramfs extension
KERNEL_INITRAMFS ?= ""

# This image depends on the rootfs image
IMAGE_TYPEDEP_calaos-ddimg = "${IMG_ROOTFS_TYPE}"

# Partition volume id
VOLUME_ID ?= "Calaos"

# Free space of partition size [in KiB]
FREE_SPACE ?= "20480"

# Set alignment to 4MB [in KiB]
IMAGE_ROOTFS_ALIGNMENT = "4096"

ROOTFS_FILE_TYPE ?= "btrfs"
ROOTFS_FILE = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.${ROOTFS_FILE_TYPE}"
KERNEL_FILE = "${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin"
IMAGE_BOOTLOADER ?= ""

IMAGE_DEPENDS_calaos-ddimg = " \
			parted-native \
			mtools-native \
			dosfstools-native \
			virtual/kernel \
			${IMAGE_BOOTLOADER} \
			"

CALAOS_IMG = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.calaos-ddimg"

IMG_DATE_STAMP = "${@time.strftime('%Y.%m.%d',time.gmtime())}"

IMAGE_CMD_calaos-ddimg () {

	# Align partitions

	ROOTFS_SIZE=`du -bks ${ROOTFS_FILE} | awk '{print $1}'`
	KERNEL_SIZE=`du -bks ${KERNEL_FILE} | awk '{print $1}'`
	BOOT_SPACE = $(expr ${ROOTFS_SIZE} * 2 + ${KERNEL_SIZE} + ${FREE_SPACE})
	IMG_SIZE=$(expr ${BOOT_SPACE} + ${IMAGE_ROOTFS_ALIGNMENT} - 1)
	IMG_SIZE=$(expr ${IMG_SIZE} - ${IMG_SIZE} % ${IMAGE_ROOTFS_ALIGNMENT})
	echo "Creating filesystem with one partition of ${IMG_SIZE} KiB"

	# Initialize sdcard image file
	dd if=/dev/zero of=${CALAOS_IMG} bs=1024 count=0 seek=${IMG_SIZE}

	# Create partition table
	parted -s ${CALAOS_IMG} mklabel msdos

	# Create boot partition and mark it as bootable
	parted -s ${CALAOS_IMG} unit KiB mkpart primary fat32 ${IMAGE_ROOTFS_ALIGNMENT} $(expr ${CALAOS_IMG_SIZE} \+ ${IMAGE_ROOTFS_ALIGNMENT})
	parted -s ${IMG} set 1 boot on

	# Create a vfat image with boot files
	BOOT_BLOCKS=$(LC_ALL=C parted -s ${CALAOS_IMG} unit b print | awk '/ 1 / { print substr($4, 1, length($4 -1)) / 512 /2 }')
	mkfs.vfat -n "${VOLUME_ID}" -S 512 -C ${WORKDIR}/calaos.img $BOOT_BLOCKS

	# Copy Kernel
	mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin ::kernel.img

	# Copy Rootfs
	mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_FILE} ::kernel.img

	mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${ROOTFS_FILE} ::calaos-os-system.btrfs

	echo "${IMAGE_NAME}-${IMAGEDATESTAMP}" > ${WORKDIR}/image-version-info

	# Burn Partitions
	dd if=${WORKDIR}/boot.img of=${CALAOS_IMG} conv=notrunc seek=1 bs=$(expr ${IMAGE_ROOTFS_ALIGNMENT} \* 1024) && sync && sync


}
