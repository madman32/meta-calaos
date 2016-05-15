DESCRIPTION = "Image with Calaos."

LICENSE = "MIT"

include calaos-os-minimal.bb
IMAGE_FEATURES_append_intel-corei7-64 = " x11-base"
IMAGE_FEATURES_append_intel-core2-32 = " x11-base"

IMAGE_LINGUAS = "en-us fr-fr de-de es-es hi-in"

IMAGE_INSTALL += "haproxy"
IMAGE_INSTALL += "calaos-server calaos-web"
IMAGE_INSTALL += "calaos-mobile"

IMAGE_INSTALL += "lua-socket"
IMAGE_INSTALL += "heyu"
IMAGE_INSTALL += "linuxconsoletools"

IMAGE_INSTALL_append_intel-corei7-64 = " mesa-megadriver setxkbmap"
IMAGE_INSTALL_append_intel-core2-32 = " mesa-megadriver setxkbmap"

XSERVER_append_intel-corei7-64 = " xf86-video-vesa"
XSERVER_append_intel-core2-32 = " xf86-video-vesa"
