ESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r15"

DEPENDS = "libsigc++-2.0 owfs log4cpp libvmime jansson lua5.1 elementary"

SRCREV = "f560d7224c5cc5de8807df7777a049a077718753"
SECTION = "x11/multimedia"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/calaos/calaos_base.git;protocol=http;branch=master \
           file://calaos-server.service \
           file://calaos-home.service \
           file://calaos_home.sh"

inherit autotools gettext systemd update-alternatives

do_configure_prepend() {
    autopoint || touch config.rpath
}

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/calaos-server.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/calaos-home.service ${D}${systemd_unitdir}/system
	install -m 0755 ${WORKDIR}/calaos_home.sh ${D}${bindir}
}

PACKAGES = "calaos-server calaos-home calaos-base-dbg calaos-base"

FILES_calaos-server = "${bindir}/calaos_server \
                    ${systemd_unitdir}/system/calaos-server.service"

FILES_calaos-home = "${bindir}/calaos_home \
                ${bindir}/calaos_thumb \
	            ${datadir}/calaos/* \
	            ${datadir}/locale/* \
	            ${systemd_unitdir}/system/calaos-home.service \
	            ${bindir}/calaos_home.sh"

FILES_calaos-dbg = "${srcdir}/* ${bindir}/.debug"

SYSTEMD_SERVICE = "calaos-server.service calaos-home.service"

