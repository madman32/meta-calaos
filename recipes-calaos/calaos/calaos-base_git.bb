ESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r14"

DEPENDS = "libsigc++-2.0 owfs log4cpp libvmime jansson lua5.1 elementary"

SRCREV = "f8531d928fc8ecaeba4ce95a0f81bda7ad21de3d"
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

FILES_calaos-server = "${bindir}/calaos_home \
                    ${systemd_unitdir}/system/calaos-server.service"

FILES_calaos-home = "${bindir}/calaos_home \
                ${bindir}/calaos_thumb \
	            ${datadir}/calaos/* \
	            ${datadir}/locale/* \
	            ${systemd_unitdir}/system/calaos-home.service \
	            ${bindir}/calaos_home.sh"

FILES_calaos-dbg = "${srcdir}/* ${bindir}/.debug"


FILES_calaos-server = "${bindir}/calaos_server"

SYSTEMD_SERVICE = "calaos-server.service"

ALTERNATIVE_calaos-home = "x-window-manager"
ALTERNATIVE_TARGET[x-window-manager] = "${bindir}/calaos_home"
ALTERNATIVE_PRIORITY[x-window-manager] = "10"
