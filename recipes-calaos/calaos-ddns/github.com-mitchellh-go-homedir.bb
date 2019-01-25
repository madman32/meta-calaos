DESCRIPTION = "github.com/mitchellh/go-homedir"

GO_IMPORT = "github.com/mitchellh/go-homedir"

inherit go

SRC_URI = "git://github.com/mitchellh/go-homedir;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "${AUTOREV}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=3f7765c3d4f58e1f84c4313cecf0f5bd"

FILES_${PN} += "${GOBIN_FINAL}/*"
