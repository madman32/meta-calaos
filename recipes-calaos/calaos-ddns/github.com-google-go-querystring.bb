DESCRIPTION = "github.com/google/go-querystring"

GO_IMPORT = "github.com/google/go-querystring"

inherit go

SRC_URI = "git://github.com/google/go-querystring;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=29f156828ca5f2df0d1c12543a75f12a"

FILES_${PN} += "${GOBIN_FINAL}/*"
