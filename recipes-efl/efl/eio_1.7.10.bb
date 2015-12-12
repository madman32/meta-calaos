DESCRIPTION = "Enlightenment Input Output Library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=180fca752525726bd6ba021689509a08"
DEPENDS = "ecore eina"

inherit efl

BBCLASSEXTEND = "native"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "152279353ec0e90dd5977b3e985f6f01"
SRC_URI[sha256sum] = "9d15a1c818463b6fc5280a5b65461b3624879b161437cc0ab7484ba23aa44fce"


