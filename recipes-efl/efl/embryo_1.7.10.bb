DESCRIPTION = "The Enlightenment C-like scripting language for Edje"

inherit efl

LICENSE = "MIT & BSD & CompuPhase"
LIC_FILES_CHKSUM = "file://COPYING;md5=220a7f1107df42c62428d8ebe559ed14"

BBCLASSEXTEND = "native"

DEPENDS += "eina"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "63e2d59f85c134249503e29ea744c816"
SRC_URI[sha256sum] = "d6700ba34d7903f53695246ca3edd3fe463ed1acbadd283219ec9678bc4989a0"
