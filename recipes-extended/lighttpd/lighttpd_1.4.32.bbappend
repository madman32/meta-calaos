FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}"
FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC := "${@int(PRINC) + 8}"

inherit systemd

BBFILE_PRIORITY_${PN} = "20"

SRC_URI += "file://lighttpd.service \
            file://lighttpd.conf \
            file://lighttpd_gencert.sh"

do_install_append() {
    install -m 0755 ${WORKDIR}/lighttpd_gencert.sh ${D}${sbindir}
}

