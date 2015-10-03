DESCRIPTION = "Calaos Web GUI"
HOMEPAGE = "http://www.calaos.fr"

PE = "1"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PE = "1"
PV = "2.99+${PR}+git${SRCPV}"
SRCREV = "5c60084f1f4a12c2b6b635aa4a2447b3e06a2384"

SRC_URI = "git://github.com/calaos/calaos-web-app.git;protocol=git;branch=devs/raoulh/new_v3"

S = "${WORKDIR}/git"

inherit systemd

do_install() {
    install -d ${D}/www/pages/
    cp -a ${WORKDIR}/git/dist/* ${D}/www/pages/
}

FILES_${PN} += "/www/pages"
PACKAGE_ARCH = "all"

