FILESEXTRAPATHS_prepend_intel-corei7-64 := "${THISDIR}/${PN}:"
FILESEXTRAPATHS_prepend_intel-core2-32 := "${THISDIR}/${PN}:"

SRC_URI += "file://40_custom"

do_install_append() {
    install -d {D}${sysconfdir}/grub.d
    install -m 0755 ${WORKDIR}/40_custom {D}${sysconfdir}/grub.d/40_custom
}

