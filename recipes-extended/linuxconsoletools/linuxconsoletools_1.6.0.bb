DESCRIPTION = "This project maintains the Linux Console tools, which include utilities to test and configure joysticks, connect legacy devices to the kernel's input subsystem (providing support for serial mice, touchscreens etc.), and test the input event layer."
HOMEPAGE = "http://sourceforge.net/projects/linuxconsole/"
PRIORITY = "optional"
PR = "r2"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SRC_URI[md5sum] = "fd52fa4a81455eb95a6c81efb087ce98"
SRC_URI[sha256sum] = "ced2efed00b67b45f82eddc69be07385835d558f658016315ac621fe2eaa8146"
SRC_URI = "${SOURCEFORGE_MIRROR}/linuxconsole/linuxconsoletools-1.6.0.tar.bz2 \
           file://usb-serial-touchscreen@.service \
           "

inherit systemd

do_compile() {
    cd utils
    make inputattach
    cd ..
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 utils/inputattach ${D}${bindir}
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/usb-serial-touchscreen@.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "usb-serial-touchscreen@.service"

