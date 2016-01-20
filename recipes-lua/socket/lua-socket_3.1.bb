DESCRIPTION = "LuaSocket is the most comprehensive networking support library for the Lua language."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab6706baf6d39a6b0fa2613a3b0831e7"
HOMEPAGE = "https://github.com/diegonehab/luasocket"

PR = "r0"
S = "${WORKDIR}/luasocket-3.0-rc1"

DEPENDS = "luajit"

SRC_URI = "https://github.com/diegonehab/luasocket/archive/v3.0-rc1.zip \
           file://socket_3.0.0-make.patch"

SRC_URI[md5sum] = "2093d9f559e3adaf288ce52c39940716"
SRC_URI[sha256sum] = "1ed0bc56d2dccd92644e8df79c4e52af60a57efdc7c57fe75b1e81c70e780e74"

LUA_LIB_DIR =  "${libdir}/lua/5.1"
LUA_SHARE_DIR = "${datadir}/lua/5.1"

FILES_${PN}-dbg = "${LUA_LIB_DIR}/mime/.debug/core.so \
                   ${LUA_LIB_DIR}/socket/.debug/core.so \
                   ${prefix}/src/debug/lua-socket"

FILES_${PN} = "${LUA_LIB_DIR}/mime/core.so \
               ${LUA_LIB_DIR}/socket/core.so \
               ${LUA_SHARE_DIR}/*.lua \
               ${LUA_SHARE_DIR}/socket/*.lua"

EXTRA_OEMAKE = "MYFLAGS='${CFLAGS} -DLUAV=5.1 ${LDFLAGS} -fPIC'"

do_install() {
        oe_runmake install INSTALL_TOP=${D}${prefix} INSTALL_TOP_SHARE=${D}${LUA_SHARE_DIR} INSTALL_TOP_LIB=${D}${LUA_LIB_DIR}
        install -d ${D}/${docdir}/${PN}-${PV}
        install -m 0644 doc/*.html ${D}/${docdir}/${PN}-${PV}
}
