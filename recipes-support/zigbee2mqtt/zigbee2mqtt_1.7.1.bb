DESCRIPTION = "Zigbee to MQTT bridge, get rid of your proprietary Zigbee bridges"
HOMEPAGE = "https://www.zigbee2mqtt.io/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

SRC_URI = " \
    https://github.com/Koenkk/zigbee2mqtt/archive/${PV}.tar.gz \
    file://configuration.yaml \
    file://zigbee2mqtt@.service \
    file://zigbee2mqtt_confport \
"

SRC_URI[md5sum] = "b14b40410019c281961965437daa5fd2"
SRC_URI[sha256sum] = "d468a7312610c79aedcb15d0d54d027bcb80102898b51b2ab2cb76c448322a40"

DEPENDS += " nodejs"
RDEPENDS_${PN} += " nodejs mosquitto"

inherit npm-install-global systemd

do_install_append() {
    install -d ${D}${sysconfdir}
    ln -sf /usr/lib/node_modules/zigbee2mqtt/data ${D}${sysconfdir}/zigbee2mqtt

    install -m 0644 ${WORKDIR}/configuration.yaml ${D}/usr/lib/node_modules/zigbee2mqtt/data/configuration.yaml

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/zigbee2mqtt@.service ${D}${systemd_unitdir}/system

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/zigbee2mqtt_confport ${D}${bindir}
}

SYSTEMD_SERVICE_${PN} = "zigbee2mqtt@.service"

