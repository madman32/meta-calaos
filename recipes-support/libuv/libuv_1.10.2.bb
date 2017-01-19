SUMMARY = "A multi-platform support library with a focus on asynchronous I/O"
HOMEPAGE = "https://github.com/libuv/libuv"
BUGTRACKER = "https://github.com/libuv/libuv/issues"
LICENSE = "MIT & BSD-2-Clause & BSD-3-Clause & ISC"

LIC_FILES_CHKSUM = "file://LICENSE;md5=bb5ea0d651f4c3519327171906045775"
SRC_URI = "http://dist.libuv.org/dist/v${PV}/libuv-v${PV}.tar.gz"

SRC_URI[md5sum] = "fad96b56f517c1ad3f274a19a10c53b2"
SRC_URI[sha256sum] = "4ceb0fe9c4efc24354c05883464d77269d41812d9ab1b7f260db8babe1826533"

inherit autotools

S = "${WORKDIR}/${PN}-v${PV}"

do_configure() {
    ${S}/autogen.sh || bbnote "${PN} failed to autogen.sh"
    oe_runconf
}

