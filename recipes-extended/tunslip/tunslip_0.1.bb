DESCRIPTION = "tunslip is a free library written in C to transfer datagram over USB" 
PR = "r1"
LICENSE = "GPLv3"

SRCREV = "d80571c0e7fc0145853bd08afc1711cfa7e64b67"
SRC_URI = "git@github.com:JulienMasson/6LoWPAN.git;protocol=http;branch=master"



do_compile() {
    cd tunslip6
    ${CC} tunslip6.c -o tunslip6
    cd ..
}


do_install() {
    install -d ${D}${bindir}
}
