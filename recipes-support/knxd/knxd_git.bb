SUMMARY = "KNXD extends the IP-reach of the KNX bus and supports multiple concurrent clients"
SECTION = "base"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=8264535c0c4e9c6c335635c4026a8022"

SRC_URI = "git://github.com/knxd/knxd.git;protocol=https;branch=master \
           file://use-pkgconfig-instead-of-pth-config.patch \
           file://knxd.service \
           file://knxd.socket \
           file://knxd.conf \
           "

SRCREV = "c8a4bb3b042de1fd692a93cdb7a307546f2be359"
S = "${WORKDIR}/git/"

inherit autotools-brokensep gettext pkgconfig systemd

EXTRA_OECONF = "--without-pth-test --enable-eibnetip --enable-eibnetiptunnel --enable-usb --enable-eibnetipserver --enable-systemd \
                --enable-ft12 --enable-pei16s --enable-dummy --enable-tpuarts --enable-ncn5120 --enable-groupcache \ 
               "

DEPENDS += "pthsem systemd libusb"
RDEPENDS_${PN} = "pthsem"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/knxd.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/knxd.socket ${D}${systemd_unitdir}/system
    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/knxd.conf ${D}${sysconfdir}/default

    rm ${D}${sysconfdir}/knxd.conf
    rm -r ${D}${libdir}/sysusers.d
}

PACKAGES =+ " ${PN}-examples-dbg  ${PN}-examples"

FILES_${PN}-examples += "${datadir}/knxd/examples \
                         ${datadir}/knxd/eibclient.php \
                         ${datadir}/knxd/EIB* \
                        "

SYSTEMD_SERVICE_${PN} = "knxd.service knxd.socket"
#do not enable knxd daemon by default
SYSTEMD_AUTO_ENABLE_${PN} = "disable"
