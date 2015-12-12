DESCRIPTION = "The Enlightenment multimedia library"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=69f44058245ce5c596d56bb622d5dabd"
# we no longer build the libxine backend, since the gstreamer backend seems more promising
DEPENDS = "eet eeze evas ecore edje gstreamer gst-plugins-base eio"

inherit efl

EXTRA_OECONF = "--disable-xine --disable-generic-vlc --enable-gstreamer --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc"

PACKAGES =+ "emotion-backend-gstreamer"
FILES_emotion-backend-gstreamer = "${libdir}/emotion/*.so"
RRECOMMENDS_${PN} = "emotion-backend-gstreamer"
# upgrade path from libemotion0 to libemotion1
RREPLACES_${PN} = "libemotion0"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "74c00aa715c350cb80e345b96849fc87"
SRC_URI[sha256sum] = "f954d79da70c21831a963ec3536ae983ad93241c97fdd590fc75c0383a028daf"

