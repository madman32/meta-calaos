DESCRIPTION = "github.com/jawher/mow.cli"

GO_IMPORT = "github.com/jawher/mow.cli"

inherit go

SRC_URI = "git://github.com/jawher/mow.cli;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=efbd2b4c3461ddf54e45c6e6c8277e81"

FILES_${PN} += "${GOBIN_FINAL}/*"
