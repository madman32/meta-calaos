DESCRIPTION = "Emqtt is a free mqtt library written in C++ and use EFL"
DEPENDS = "eina"
PR = "r1"
LICENSE = "GPLv3"

SRCREV = "4d0b77d7314b871e8d76f0076905bcc071fae9d5"
SRC_URI = "git://github.com/naguirre/emqtt.git;protocol=http;branch=master"

inherit autotools pkgconfig
