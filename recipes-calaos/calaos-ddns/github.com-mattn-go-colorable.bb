DESCRIPTION = "github.com/mattn/go-colorable"

GO_IMPORT = "github.com/mattn/go-colorable"

inherit go

SRC_URI = "git://github.com/mattn/go-colorable;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=24ce168f90aec2456a73de1839037245"

DEPENDS += "github.com-mattn-go-isatty"

FILES_${PN} += "${GOBIN_FINAL}/*"
