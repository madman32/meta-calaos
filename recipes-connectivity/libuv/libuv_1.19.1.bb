SUMMARY = "A multi-platform support library with a focus on asynchronous I/O"
HOMEPAGE = "https://github.com/libuv/libuv"
BUGTRACKER = "https://github.com/libuv/libuv/issues"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a68902a430e32200263d182d44924d47"

SRC_URI = "https://github.com/libuv/${BPN}/archive/v${PV}.tar.gz;downloadfilename=${BP}.tar.gz"

SRC_URI[md5sum] = "f69deb537239af334698101b4ea02d7b"
SRC_URI[sha256sum] = "8d96a6230af4f9f33d6ec021b599e157279a2af72ed38cf4a8c25e6d58d952ef"

inherit autotools

do_configure() {
    ${S}/autogen.sh || bbnote "${PN} failed to autogen.sh"
    oe_runconf
}

BBCLASSEXTEND = "native"
