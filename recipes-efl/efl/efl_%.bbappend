FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0002-Fix-RPI-build-EGL_OPENGL_ES3_BIT_KHR-is-undefined.patch \
            file://0001-evas-use-void-instead-of-struct-wl_-to-avoid-compila.patch \
            file://0002-evas-move-all-GL-safe-define-to-a-common-header-and-.patch \
            file://0001-evas-Add-defines-for-GL_COLOR_EXT-GL_DEPTH_EXT-GL_ST.patch \
"

PACKAGECONFIG = "opengl-es luajit"

#disable neon cpu for raspberrypi, it is not done automatically by EFL
EXTRA_OECONF_append_raspberrypi = "--disable-neon"


