inherit image_types syslinux

# This image depends on the rootfs image
IMAGE_TYPEDEP_calaos-ddimg = "${IMG_ROOTFS_TYPE}"

# Boot partition volume id
BOOTDD_VOLUME_ID ?= "CALAOS_${MACHINE}"

# First partition begin at sector 2048 : 2048*1024 = 2097152
IMG_ROOTFS_ALIGNMENT = "2048"

# Set kernel and boot loader
#IMAGE_BOOTLOADER ?= "syslinux"
IMAGE_BOOTLOADER_raspberrypi ?= "bcm2835-bootfiles"
IMAGE_BOOTLOADER_qemux86-64 ?= "dosfstools-native:do_populate_sysroot \
                                syslinux:do_populate_sysroot \
                                syslinux-native:do_populate_sysroot \
                                parted-native:do_populate_sysroot \
                                mtools-native:do_populate_sysroot "

IMAGE_DEPENDS_calaos-ddimg += " \
			parted-native \
			mtools-native \
			dosfstools-native \
			virtual/kernel \
			${IMAGE_BOOTLOADER} \
			"
IMAGE_COMPRESSION ?= "xz"
ROOTFS_FILE_TYPE ?= "btrfs"
ROOTFS_FILE ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.${ROOTFS_FILE_TYPE}"
KERNEL_FILE ?= "${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin"
FATPAYLOAD ?= ""

SYSLINUX_TIMEOUT ?= "10"
SYSLINUX_ROOT ?= "root=/dev/mem"
SYSLINUX_PROMPT ?= "0"
SYSLINUX_TIMEOUT ?= "10"
SYSLINUX_LABELS = "boot"
LABELS_append = " ${SYSLINUX_LABELS} "


#rootfs[depends] += "sunxi-board-fex:do_deploy"

# SD card image name
IMG = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.calaos-ddimg"

IMAGEDATESTAMP = "${@time.strftime('%Y.%m.%d',time.gmtime())}"

FATPAYLOAD_SIZE ?= "0"

IMAGE_CMD_calaos-ddimg () {

	ROOTFS_SIZE=`du -bksL ${ROOTFS_FILE} | awk '{print $1}'`
	KERNEL_SIZE=`du -bksL ${KERNEL_FILE} | awk '{print $1}'`

	if [ -n ${FATPAYLOAD} ] ; then
		for entry in ${FATPAYLOAD} ; do
		    TEMP_SIZE =`du -bksL ${WORKDIR}/${entry} | awk '{print $1}'`
		    FATPAYLOAD_SIZE=$(5Hexpr ${FATPAYLOAD_SIZE} + ${TEMP_SIZE})
		done
	fi

	BSPACE=$(expr ${BOOT_SPACE} + ${ROOTFS_SIZE} \* 2 + ${KERNEL_SIZE} + ${FATPAYLOAD_SIZE})
	echo $BSPACE
	# Align partitions
	BOOT_SPACE_ALIGNED=$(expr ${BSPACE} + ${IMG_ROOTFS_ALIGNMENT} - 1)
	BOOT_SPACE_ALIGNED=$(expr ${BOOT_SPACE_ALIGNED} - ${BOOT_SPACE_ALIGNED} % ${IMG_ROOTFS_ALIGNMENT})
	IMG_SIZE=$(expr ${IMG_ROOTFS_ALIGNMENT} + ${BOOT_SPACE_ALIGNED} + ${IMG_ROOTFS_ALIGNMENT})

	# Initialize sdcard image file
	dd if=/dev/zero of=${IMG} bs=1 count=0 seek=$(expr 1024 \* ${IMG_SIZE})

	# Create partition table
	parted -s ${IMG} mklabel msdos
	# Create boot partition and mark it as bootable
	parted -s ${IMG} unit KiB mkpart primary fat32 ${IMG_ROOTFS_ALIGNMENT} $(expr ${BOOT_SPACE_ALIGNED} \+ ${IMG_ROOTFS_ALIGNMENT})
	parted -s ${IMG} set 1 boot on
	parted ${IMG} print

	# Create a vfat image with boot files
	BOOT_BLOCKS=$(LC_ALL=C parted -s ${IMG} unit b print | awk '/ 1 / { print substr($4, 1, length($4 -1)) / 512 /2 }')
	mkfs.vfat -n "${BOOTDD_VOLUME_ID}" -S 512 -C ${WORKDIR}/calaos.img $BOOT_BLOCKS

	case "${KERNEL_IMAGETYPE}" in
	"uImage")
	    mcopy -i ${WORKDIR}/calaos.img -s ${DEPLOY_DIR_IMAGE}/u-boot.img ::kernel.img
	    mcopy -i ${WORKDIR}/calaos.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin ::uImage
	    ;;
	*)
	    mcopy -i ${WORKDIR}/calaos.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin ::kernel.img
	    ;;
	esac

	# Copy Rootfs
	mcopy -i ${WORKDIR}/calaos.img -s ${ROOTFS_FILE} ::calaos-os-system.btrfs

	# Add stamp file
	echo "${IMAGE_NAME}-${IMAGEDATESTAMP}" > ${WORKDIR}/image-version-info
	mcopy -i ${WORKDIR}/calaos.img -v ${WORKDIR}//image-version-info ::

	case "${MACHINE}" in
	"raspberrypi")
        	mcopy -i ${WORKDIR}/calaos.img -s ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/* ::/
	        ;;
	"sunxi")
	        dd if=${DEPLOY_DIR_IMAGE}/u-boot-sunxi-with-spl.bin of=${IMG} bs=1024 seek=8 conv=notrunc
	        ;;
        "qemux86-64")
		HDDDIR="${S}/hdd/boot"
		syslinux_hddimg_populate $HDDDIR
         	mcopy -i ${WORKDIR}/calaos.img -s $HDDDIR/* ::/
		dd if=${STAGING_DATADIR}/syslinux/mbr.bin of=${IMG} conv=notrunc
		;;
	"*")
	        ;;
	esac

	if [ -n ${FATPAYLOAD} ] ; then
		echo "Copying payload into VFAT"
		for entry in ${FATPAYLOAD} ; do
				# add the || true to stop aborting on vfat issues like not supporting .~lock files
				mcopy -i ${WORKDIR}/calaos.img -s -v ${WORKDIR}/$entry :: || true
		done
	fi


	# Burn Partitions
	dd if=${WORKDIR}/calaos.img of=${IMG} conv=notrunc seek=1 bs=$(expr ${IMG_ROOTFS_ALIGNMENT} \* 1024) && sync && sync

	# Optionally apply compression
	case "${IMAGE_COMPRESSION}" in
	"gzip")
		gzip -k9 "${IMG}"
		;;
	"bzip2")
		bzip2 -k9 "${IMG}"
		;;
	"xz")
		xz -k "${IMG}"
		;;
	esac

}

python do_syslinuxcfg() {
       bb.build.exec_func('build_syslinux_cfg', d)
}


addtask syslinuxcfg before do_rootfs