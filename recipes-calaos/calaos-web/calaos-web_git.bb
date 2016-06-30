DESCRIPTION = "Calaos Web GUI"
HOMEPAGE = "http://www.calaos.fr"

PE = "1"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PE = "1"
PV = "2.99+${PR}+git${SRCPV}"
SRCREV = "7317e122a1846d9393e1ba3099e405c90a07db14"

SRC_URI = "git://github.com/calaos/calaos-web-app.git;protocol=git;branch=master"

S = "${WORKDIR}/git"

inherit systemd

fakeroot do_install() {
    install -d ${D}${datadir}/calaos/app
    cp -a ${WORKDIR}/git/dist/* ${D}${datadir}/calaos/app
}

RDEPENDS_${PN} +=" haproxy"

FILES_${PN} += "${datadir}/calaos/app"
PACKAGE_ARCH = "all"

