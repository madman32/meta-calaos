FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append_mx6 = " file://0002-Add-preprocessor-definitions-for-Vivante-generic.patch"


#disable neon cpu for raspberrypi, it is not done automatically bu EFL
EXTRA_OECONF_append_raspberrypi = "--disable-neon"


