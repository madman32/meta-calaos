FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Fix-build-of-evas-gl-module-when-wayland-is-not-pres.patch \
            file://0002-Fix-RPI-build-EGL_OPENGL_ES3_BIT_KHR-is-undefined.patch"

PACAKGE_CONFIG = "opengl-es luajit"
