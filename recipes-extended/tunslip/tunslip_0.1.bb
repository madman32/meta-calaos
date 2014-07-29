DESCRIPTION = "tunslip is a free library written in C to transfer datagram over USB" 
PR = "r1"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://tunslip6.c;endline=33;md5=abd6836e4fefd42d9dc8f818d44579f3"

S = "${WORKDIR}/git/tunslip6"

SRCREV = "d80571c0e7fc0145853bd08afc1711cfa7e64b67"
SRC_URI = "git://github.com/JulienMasson/6LoWPAN.git;protocol=http;branch=master \
	   file://tunslip@.service \
	   file://tunslip.default"

inherit systemd

do_compile() {
    ${CC} tunslip6.c -o tunslip6
}


do_install() {
    install -d ${D}${bindir}
    install -m 0755 tunslip6 ${D}${bindir}
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/tunslip@.service ${D}${systemd_unitdir}/system
    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/tunslip.default ${D}${sysconfdir}/default/tunslip
}


SYSTEMD_SERVICE_${PN} = "tunslip@.service"
