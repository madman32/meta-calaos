DESCRIPTION = "github.com/fatih/color"

GO_IMPORT = "github.com/fatih/color"

inherit go

SRC_URI = "git://github.com/fatih/color;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE.md;md5=316e6d590bdcde7993fb175662c0dd5a"

DEPENDS += "\
    github.com-mattn-go-isatty \
    github.com-mattn-go-colorable \
"

FILES_${PN} += "${GOBIN_FINAL}/*"
