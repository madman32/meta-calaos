DESCRIPTION = "Connman wait online service"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://connman-online \
           file://connman-wait-online.service"

PR = "r0"

inherit systemd

do_install() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/connman-wait-online.service ${D}${systemd_unitdir}/system
    install -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/connman-online ${D}${sbindir}/
}

SYSTEMD_SERVICE_${PN} = "connman-wait-online.service"
