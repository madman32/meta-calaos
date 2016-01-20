SUMMARY = "Calaos Mobile Application"

DESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/calaos/calaos_mobile.git;protocol=https;branch=devs/raoulh/calaos_home"
SRCREV = "41e02f0cf618654044001b5cb09df3c278f280ee"
S = "${WORKDIR}/git/"

QMAKE_PROFILES = "../git/desktop.pro"

require recipes-qt/qt5/qt5.inc

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/calaos_home ${D}${bindir}/CalaosHome
}

DEPENDS = "qtdeclarative qtgraphicaleffects qtwebsockets"
RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"


