FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Fix-build-of-evas-gl-module-when-wayland-is-not-pres.patch"

PACAKGE_CONFIG = "opengl-es luajit"
