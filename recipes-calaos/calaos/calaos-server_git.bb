DESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS += "libsigc++-2.0 owfs jansson luajit eina eet ecore libusb ola eibnetmux"
RDEPENDS_${PN} += "heyu lua-socket"

PE = "1"
PV = "2.99.0+${PR}+git${SRCPV}"

SRCREV = "ff00a7591e282d4485bd42b3d6d143871d71106b"

SECTION = "utils"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/calaos/calaos_base.git;protocol=http;branch=master \
           file://calaos-server.service"

inherit autotools gettext systemd

do_compile_prepend() {
  cd ${S}
  autopoint --force
  autoreconf -vif
}

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/calaos-server.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = "${bindir}/calaos_server \
               ${systemd_unitdir}/system/calaos-server.service \
               ${bindir}/calaos_* \
               ${bindir}/wago_test \
               ${datadir}/calaos/camfail.jpg "

PACKAGES += "calaos-web-debug" 

FILES_calaos-web-debug = "${datadir}/calaos/debug/*"

SYSTEMD_SERVICE_${PN} = "calaos-server.service"
