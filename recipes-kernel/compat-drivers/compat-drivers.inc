DESCRIPTION = "Latest compat-drivers drivers"
HOMEPAGE = "https://backports.wiki.kernel.org/index.php/Main_Page"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=d7810fab7487fb0aad327b76f1be7cd7"
RDEPENDS_${PN} = "wireless-tools"
INC_PR = "r1"

SHRT_VER = "${@d.getVar('PV',1).split('-')[0]}"

SRC_URI = " \
    https://www.kernel.org/pub/linux/kernel/projects/backports/stable/v${SHRT_VER}/compat-drivers-${PV}.tar.xz \
"

S = "${WORKDIR}/compat-drivers-${PV}"

inherit module

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D} -Wno-error prefix=${D}"


do_configure_append() {
    sed -i "s#@./scripts/update-initramfs## " Makefile
    cd ${S}
    ./scripts/driver-select alx
}

do_install() {
    install -d 770 ${D}/usr/sbin
    # Remove hardcoded references to host depmod
    sed -i "s#@/sbin/depmod -a## " Makefile
    sed -i "s#@/sbin/depmod -ae## " Makefile
    oe_runmake DEPMOD=/bin/echo DESTDIR="${D}" INSTALL_MOD_PATH="${D}" LDFLAGS="" prefix=${D} install-modules
    install -d "${D}"/usr/bin

    install -d "${D}"/usr/lib/udev/rules.d
    install udev/compat_firmware.sh	"${D}"/usr/lib/udev
    install udev/50-compat_firmware.rules "${D}"/usr/lib/udev/rules.d
}
