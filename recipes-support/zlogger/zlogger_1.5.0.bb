SUMMARY = "zlogger"
DESCRIPTION = "Simple & efficient logging library for C with support for multiple appenders/formats, including ring buffer for debug messages."

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://zlogger.h;beginline=1;endline=17;md5=6c6ee3ea8c667605436fe73c5b3e6ddc"

SRC_URI = "http://sourceforge.net/projects/${BPN}/files/${BPN}/${PV}/${BPN}-${PV}.tar.bz2 \
           file://0001_include_top_src_dir.patch \
          "

SRC_URI[md5sum] = "c1d537d30b7a527c1d42bd70c55dc0b7"
SRC_URI[sha256sum] = "87039573552a0af5e2d7fddfc91518526c527f37ff4eebc858a9964e9cdf4458"

inherit autotools pkgconfig

EXTRA_OECONF = " --with-plugins \
                 --with-doxygen=none \
               "

PACKAGES += "${PN}-tools"
FILES_${PN}-tools = "${bindir}/*"
FILES_${PN}-dbg += "${libdir}/zlogger/plugins/${PV}/.debug"
FILES_${PN}-staticdev += "${libdir}/zlogger/plugins/${PV}/*.a"
FILES_${PN}-dev += "${libdir}/zlogger/plugins/${PV}/*.la"

