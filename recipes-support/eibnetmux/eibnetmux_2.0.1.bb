SUMMARY = "EIBnetmux"
DESCRIPTION = "EIBnetmux extends the IP-reach of the KNX bus and supports multiple concurrent clients." 

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://eibnetmux/main.c;beginline=1;endline=17;md5=502031be558ad05e07a5e8248d5f5acc"

DEPENDS = "zlogger pth"

SRC_URI = "http://sourceforge.net/projects/${BPN}/files/${BPN}/${PV}/${BPN}-${PV}.tar.gz \
           file://eibnetmux.service \
           file://eibnetmux \
          "

SRC_URI[md5sum] = "8ee23dc7f11738c34dc86a828b0041ad"
SRC_URI[sha256sum] = "aa9b6fdf5388bc7245731cb9e8fffebdd13b7effce622690aae3229aacb3520a"

inherit autotools pkgconfig systemd

EXTRA_OECONF = " --enable-busmonitor \
                 --with-doxygen=none \
               "

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/eibnetmux.service ${D}${systemd_unitdir}/system
    install -d ${D}etc/default
    install -m 0644 ${WORKDIR}/eibnetmux ${D}etc/default
}

SYSTEMD_SERVICE_${PN} = "eibnetmux.service"
#do not enable eibnetmux daemon by default
SYSTEMD_AUTO_ENABLE_${PN} = "disable"
