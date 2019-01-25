DESCRIPTION = "github.com/mattn/go-isatty"

GO_IMPORT = "github.com/mattn/go-isatty"

inherit go

SRC_URI = "git://github.com/mattn/go-isatty;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=f509beadd5a11227c27b5d2ad6c9f2c6"

FILES_${PN} += "${GOBIN_FINAL}/*"
