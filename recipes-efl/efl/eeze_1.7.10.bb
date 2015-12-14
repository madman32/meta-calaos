DESCRIPTION = "Eeze is a library to simplify the use of devices"
LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=315521fe061b6fd4290ef01db714a3c8"
DEPENDS = "ecore eet udev"

inherit efl

BBCLASSEXTEND = "native"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "${libdir}/enlightenment/utils/eeze_scanner"
FILES_${PN}-dbg += "${libdir}/enlightenment/utils/.debug"

RRECOMMENDS_${PN} += "eject"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "e1ce3d8dd56fd174934c05b976c5b10f"
SRC_URI[sha256sum] = "628691bbf3049066a7870ba9618ba29c47165219c2062a01fbeb13e784685c01"
