DESCRIPTION = "Ecore is the Enlightenment application framework library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=d6ff2c3c85de2faf5fd7dcd9ccfc8886"
DEPENDS = "virtual/libiconv tslib curl eet openssl pkgconfig-native"
DEPENDS_virtclass-native = "eet-native gettext-native"

inherit gettext autotools

BBCLASSEXTEND = "native"

do_configure_prepend() {
    touch ${S}/po/Makefile.in.in || true
    sed -i -e 's: po::g' ${S}/Makefile.am
}

FILESPATHPKG =. "${BPN}-${PV}:${BPN}:"

PACKAGES =+ "\
    ${PN}-con \
    ${PN}-file \
    ${PN}-ipc \
"
# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} = "${libdir}/libecore*.so.* \
    ${bindir} \
"
FILES_${PN}-con = "${libdir}/libecore_con*.so.*"
FILES_${PN}-file = "${libdir}/libecore_file*.so.*"
FILES_${PN}-ipc = "${libdir}/libecore_ipc*.so.*"

CFLAGS=""

EXTRA_OECONF = "\
    --disable-ecore-x-composite \
    --disable-ecore-x-damage \
    --disable-ecore-x-dpms \
    --disable-ecore-x-randr \
    --disable-ecore-x-render \
    --disable-ecore-x-screensaver \
    --disable-ecore-x-shape \
    --disable-ecore-x-gesture \
    --disable-ecore-x-sync \
    --disable-ecore-x-xfixes \
    --disable-ecore-x-xinerama \
    --disable-ecore-x-xprint \
    --disable-ecore-x-xtest \
    --disable-ecore-x-cursor \
    --disable-ecore-x-input \
    --disable-ecore-x-dri \
    --disable-ecore-x \
    --disable-ecore-input \
    --disable-ecore-input-evas \
    --disable-ecore-fb \
    --disable-ecore-imf \
    --disable-nls \
    --disable-glib \
    --x-includes=${STAGING_INCDIR}/X11 \
    --disable-gnutls \
    --enable-openssl \
"


SRC_URI = "\
    ${E_MIRROR}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "5a1c3b258f42559ef6fe8e28a803a48d"
SRC_URI[sha256sum] = "d62ffba357df4f799efe95b6429bc6cb5fe6201f74666df62fbb132057864164"

INSANE_SKIP_${PN} += "compile-host-path"
