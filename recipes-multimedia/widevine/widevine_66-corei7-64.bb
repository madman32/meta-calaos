
#SRC_URI = "http://www.slimjetbrowser.com/chrome/lnx/chrome64_76.0.3809.100.deb"
SRC_URI = "https://www.slimjetbrowser.com/release/slimjet_amd64.tar.xz"
SRC_URI[md5sum] = "05cd66904e822ff7054b63e1ec910244"
SRC_URI[sha256sum] = "7cfc4de7e157340dfc3e67416d12e9c2a5e978d3e2955b6a5571476a5d2c9238"

require widevine.inc

COMPATIBLE_HOST = "(x86_64).*-linux"
