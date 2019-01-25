DESCRIPTION = "github.com/xenolf/lego"

GO_IMPORT = "github.com/xenolf/lego"

inherit go

SRC_URI = "git://github.com/xenolf/lego;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=0fb5231b70744f4c6974679615181b2b"

GO_INSTALL = ""

FILES_${PN} += "${GOBIN_FINAL}/*"
