DESCRIPTION = "github.com/dghubble/sling"

GO_IMPORT = "github.com/dghubble/sling"

inherit go

SRC_URI = "git://github.com/dghubble/sling;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=3f26e43da477d67e26af43403abeb186"

DEPENDS += "github.com-google-go-querystring"

GO_INSTALL = ""

FILES_${PN} += "${GOBIN_FINAL}/*"
