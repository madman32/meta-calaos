FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC := "${@int(PRINC) + 9}"

inherit systemd

BBFILE_PRIORITY_${PN} = "20"

SRC_URI += "file://lighttpd.service \
            file://lighttpd.conf \
            file://lighttpd_gencert.sh"

EXTRA_OECONF = " \
             --without-bzip2 \
             --without-ldap \
             --without-lua \
             --without-memcache \
             --with-pcre \
             --without-webdav-props \
             --without-webdav-locks \
             --with-openssl \
             --disable-static \
"

do_install_append() {
    install -m 0755 ${WORKDIR}/lighttpd_gencert.sh ${D}${sbindir}
    rm -fr ${D}/www/pages/index.html
}

