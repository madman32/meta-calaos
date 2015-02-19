


INITRAMFS_IMAGE = "calaos-os-update"
INITRAMFS_IMAGE_BUNDLE = "1"

kernel_configure_variable() {
    # Remove the config
    CONF_SED_SCRIPT="$CONF_SED_SCRIPT /CONFIG_$1[ =]/d;"
    if test "$2" = "n"
    then
        echo "# CONFIG_$1 is not set" >> ${B}/.config
    else
        echo "CONFIG_$1=$2" >> ${B}/.config
    fi
}


do_configure_append() {
    kernel_configure_variable BLK_DEV_INITRD y
    kernel_configure_variable INITRAMFS_SOURCE ""
    kernel_configure_variable RD_GZIP y
    kernel_configure_variable BTRFS_FS y
}