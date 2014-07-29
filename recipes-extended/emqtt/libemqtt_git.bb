DESCRIPTION = "Emqtt is a free mqtt library written in C and use EFL"
DEPENDS = "ecore"
PR = "r1"

LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.GPL2;md5=751419260aa954499f7abaabaa882bbe \
                    file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

PV = "0.1.0+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "d9a4a7fb85f0de9604c4a0d5196e39c9276a059b"
SRC_URI = "git://github.com/JulienMasson/emqtt.git;protocol=http;branch=master"

inherit autotools pkgconfig

PACKAGES += "${PN}-tools"

FILES_${PN} = "${libdir}/*"

FILES_${PN}-tools = "${bindir}/emqtt_sn_broker \
                     ${bindir}/emqtt_sn_sub"