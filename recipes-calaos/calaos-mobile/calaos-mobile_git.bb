SUMMARY = "Calaos Mobile Application"

DESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/madman32/calaos_mobile.git;protocol=https;branch=master \
           file://calaos-home.service \
          "
SRCREV = "52d937b34c612dd9f478a634afe94608168e0c13"
S = "${WORKDIR}/git/"

inherit systemd

DEPENDS = "qtdeclarative qtgraphicaleffects qtwebsockets qtquickcontrols qtsvg qtvirtualkeyboard qtwebengine qtcharts"
RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins qtquickcontrols-qmlplugins qtsvg-plugins \
                  qtvirtualkeyboard qtvirtualkeyboard-plugins qtvirtualkeyboard-qmlplugins qtwebengine-qmlplugins qtcharts-qmlplugins\
                "

QMAKE_PROFILES = "../git/desktop.pro"
EXTRA_QMAKEVARS_PRE = "CONFIG+=DISABLE_QM"

require recipes-qt/qt5/qt5.inc

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/calaos_home ${D}${bindir}/calaos_home_qt

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/calaos-home.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "calaos-home.service"

