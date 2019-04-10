SUMMARY = "{fmt} is an open-source formatting library for C++"
LICENSE =  "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=c2e38bc8629eac247a73b65c1548b2f0"

inherit pkgconfig cmake

FILES_${PN} += "${libdir}/cmake/fmt/*.cmake"

SRC_URI = "https://github.com/fmtlib/fmt/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "89863cfec1448aec409a2eecf62600a2"
SRC_URI[sha256sum] = "73d4cab4fa8a3482643d8703de4d9522d7a56981c938eca42d929106ff474b44"
