DESCRIPTION = "Calaos Web GUI"
HOMEPAGE = "http://www.calaos.fr"

PR = "r2"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "d5d22a083a09226ce9c874eeee89c73f41db87dd"

SRC_URI = "git://github.com/calaos/calaos_web.git;protocol=git"	

S = "${WORKDIR}/git"

inherit systemd

do_install() {
    install -d ${D}/www/pages/
    cp -a ${WORKDIR}/git/* ${D}/www/pages/
}

FILES_${PN} += "/www/pages"
PACKAGE_ARCH = "all"

