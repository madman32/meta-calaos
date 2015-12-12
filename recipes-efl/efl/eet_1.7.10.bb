DESCRIPTION = "EET is the Enlightenment data storage library"
DEPENDS = "pkgconfig zlib jpeg openssl eina gnutls"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=da947f414a2ca4323245f1abb1980953"

inherit efl

BBCLASSEXTEND = "native"

EXTRA_OECONF = "\
    --disable-openssl \
    --disable-gnutls \
    --disable-cipher \
    --disable-signature \
    --enable-old-eet-file-format \
    --disable-assert \
"

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "\
    ${bindir}/${PN} \
"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "90b4672f898779cc8d2df2b040e9cb78"
SRC_URI[sha256sum] = "c424821eb8ba09884d3011207b1ecec826bc45a36969cd4978b78f298daae1ee"

