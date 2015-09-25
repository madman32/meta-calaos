# Base this image on core-image-minimal

CORE_IMAGE_EXTRA_INSTALL = " "
IMAGE_LINGUAS = " "
IMAGE_FEATURE = "read-only-rootfs"

inherit core-image

#IMG_ROOTFS_TYPE = "btrfs"
#IMAGE_FSTYPES += "calaos-ddimg" 

