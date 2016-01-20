DESCRIPTION = "EET is the Enlightenment data storage library"
DEPENDS = "pkgconfig zlib jpeg openssl eina pkgconfig-native"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=da947f414a2ca4323245f1abb1980953"

inherit autotools

BBCLASSEXTEND = "native"

PACKAGES =+ "${PN}-utils"

EXTRA_OECONF += "--disable-gnutls --enable-openssl"

FILES_${PN}-utils = "\
    ${bindir}/${PN} \
"

SRC_URI = "\
    ${E_MIRROR}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "90b4672f898779cc8d2df2b040e9cb78"
SRC_URI[sha256sum] = "c424821eb8ba09884d3011207b1ecec826bc45a36969cd4978b78f298daae1ee"

