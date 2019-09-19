
#SRC_URI = "http://www.slimjetbrowser.com/chrome/lnx/chrome32_48.0.2564.109.deb"
SRC_URI = "https://www.slimjetbrowser.com/release/slimjet_i386.tar.xz"
SRC_URI[md5sum] = "0df1873049daccc0e026ed63d703f698"
SRC_URI[sha256sum] = "3d877eb9a6a0558f195be3ec0b49a4355da7e4e47874522189fbe62abc54f9a5"

require widevine.inc

COMPATIBLE_HOST = "(i686).*-linux"
