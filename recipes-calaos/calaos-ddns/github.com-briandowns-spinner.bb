DESCRIPTION = "github.com/briandowns/spinner"

GO_IMPORT = "github.com/briandowns/spinner"

inherit go

SRC_URI = "git://github.com/briandowns/spinner;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=ecf8a8a60560c35a862a4a545f2db1b3"

DEPENDS += "github.com-fatih-color"

FILES_${PN} += "${GOBIN_FINAL}/*"
