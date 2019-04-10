DESCRIPTION = "Calaos Web GUI"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PE = "1"
PV = "2.99+${PR}+git${SRCPV}"
SRCREV = "7e980a1c9692d55e8a782df832473233ca226e30"

SRC_URI = "git://github.com/calaos/calaos-web-app.git;protocol=git;branch=master"

S = "${WORKDIR}/git"

inherit allarch

do_install() {
    install -d ${D}${datadir}/calaos/app
    ( cd ${WORKDIR}/git/dist/
      find . -type d -exec install -d ${D}${datadir}/calaos/app/{} \;
      find . -type f -exec install -m 0644 ${WORKDIR}/git/dist/{} ${D}${datadir}/calaos/app/{} \;
    );
}

RDEPENDS_${PN} +=" haproxy"

FILES_${PN} += "${datadir}/calaos/app"

#All arch package
PACKAGE_ARCH = "all"

#Only default package is needed, no -dev/-doc/-locale
PACKAGES = "${PN}"

