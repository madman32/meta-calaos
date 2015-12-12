DESCRIPTION = "EFL based thumbnail generation library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a6db9e80255adbafa16e817d9a4d8c"
DEPENDS = "libexif eet-native evas ecore edje eet edbus emotion"

inherit efl

EXTRA_OECONF = "\
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    --with-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
    --disable-docs \
"

# Some upgrade path tweaking, as in evas
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "\
    ${bindir}/ethumbd \
    ${libexecdir}/ethumbd_slave \
"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"


SRC_URI[md5sum] = "a31808936e89170772a1bbc3e1328b7f"
SRC_URI[sha256sum] = "40c20946ddd311a03546c5d971c5f02acc7ff7bf054ee5f25aed9bc5e1ea67fe"
