INITRAMFS_IMAGE = "calaos-os-update"
INITRAMFS_IMAGE_BUNDLE = "1"

do_configure_append() {
    kernel_configure_variable BLK_DEV_INITRD y
    kernel_configure_variable INITRAMFS_SOURCE ""
    kernel_configure_variable RD_GZIP y
    kernel_configure_variable BTRFS_FS y
}
