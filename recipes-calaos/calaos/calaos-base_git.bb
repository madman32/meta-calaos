DESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r21"

DEPENDS = "libsigc++-2.0 owfs log4cpp libvmime jansson lua5.1 elementary"

SRCREV = "8f2a5ba753825e55ad3f61aa9e473409dfb63b4d"
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

FILES_${PN}-dbg += "${srcdir}/* ${bindir}/.debug ${libdir}/calaos/widgets/*/.debug"
FILES_${PN}-dev += "${libdir}/calaos/widgets/*/*.la"

PACKAGES = "calaos-server calaos-home calaos-base calaos-base-dbg calaos-base-dev calaos-tools"

#Clock widget
PACKAGES += "calaos-home-widget-clock"
FILES_calaos-home-widget-clock = "${libdir}/calaos/widgets/clock/module.so"
FILES_calaos-home-widget-clock += "${datadir}/calaos/widgets/clock/*.edj"

#Note widget
PACKAGES += "calaos-home-widget-note"
FILES_calaos-home-widget-note = "${libdir}/calaos/widgets/note/module.so"
FILES_calaos-home-widget-note += "${datadir}/calaos/widgets/note/*.edj"

FILES_calaos-server = "${bindir}/calaos_server \
                    ${systemd_unitdir}/system/calaos-server.service"

FILES_calaos-home = "${bindir}/calaos_home \
                ${bindir}/calaos_thumb \
	            ${datadir}/calaos/default.edj \
	            ${datadir}/locale/* \
	            ${systemd_unitdir}/system/calaos-home.service \
	            ${bindir}/calaos_home.sh"

FILES_calaos_tools = "${bindir}/calaos_config \
                ${bindir}/wago_test \
                "

RRECOMMENDS_${PN} += "calaos-home-widget-clock \
                      calaos-home-widget-note \
                      calaos-tools \
                      "

SYSTEMD_SERVICE = "calaos-server.service calaos-home.service"

