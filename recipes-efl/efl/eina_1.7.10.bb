DESCRIPTION = "Eina is the Enlightenment data library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9cc092d35d7bbfcd986232cde130a551"

DEPENDS += "pkgconfig-native"

BBCLASSEXTEND = "native"

inherit autotools

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "${libdir}/eina"

SRC_URI = "\
    ${E_MIRROR}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "7da04c402d24078be9c4e5e8d8a35f17"
SRC_URI[sha256sum] = "3f33ae45c927faedf8d342106136ef1269cf8dde6648c8165ce55e72341146e9"