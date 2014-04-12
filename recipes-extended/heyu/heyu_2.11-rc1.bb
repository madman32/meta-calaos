DESCRIPTION = "HEYU is a text-based console program for remotely controlling lights and appliances in the home or office."
HOMEPAGE = "http://www.heyu.org/"
PRIORITY = "optional"

PR = "r0"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI[md5sum] = "4cfb933abbd44ac674e202df7d23dbfd"
SRC_URI[sha256sum] = "97b49f0925194beb753f4549360ca12ef848c1022132b388b72009c36e1ff502"

SRC_URI = "http://www.heyu.org/download/${PN}-${PV}.tar.gz"

inherit autotools pkgconfig
