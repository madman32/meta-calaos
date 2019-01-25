DESCRIPTION = "Calaos dynamic dns client tool"

inherit go

SRC_URI = "git://github.com/calaos/calaos_dns.git;protocol=http;branch=master;destsuffix=${PN}-${PV}/src/github.com/calaos/calaos_dns"
SRCREV = "${AUTOREV}"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://src/github.com/calaos/calaos_dns/COPYING;md5=d32239bcb673463ab874e80d47fae504"

GO_IMPORT = "github.com/calaos/calaos_dns/calaos_ddns"

DEPENDS += "\
    github.com-briandowns-spinner \
    github.com-dghubble-sling \
    github.com-fatih-color \
    github.com-jawher-mow.cli \
    github.com-mattn-go-isatty \
    github.com-mitchellh-go-homedir \
    github.com-xenolf-lego \
"

