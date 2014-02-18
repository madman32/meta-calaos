DESCRIPTION = "Calaos Web GUI"
HOMEPAGE = "http://www.calaos.fr"

PE = "1"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PE = "1"
PV = "0.1+git${SRCPV}"
SRCREV = "8bf966f1cb5e9303dfbf60541cefd1ca1cf28297"

SRC_URI = "git://github.com/calaos/calaos-web-app.git;protocol=git \
           file://config.js \
          "

S = "${WORKDIR}/git"

inherit systemd

do_install() {
    install -d ${D}/www/pages/
    cp -a ${WORKDIR}/git/www/* ${D}/www/pages/
    cp ${WORKDIR}/config.js ${D}/www/pages/js/
}

FILES_${PN} += "/www/pages"
PACKAGE_ARCH = "all"

