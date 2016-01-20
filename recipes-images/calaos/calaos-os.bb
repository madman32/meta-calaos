DESCRIPTION = "Image with Calaos."

LICENSE = "MIT"

include calaos-os-minimal.bb
IMAGE_FEATURES_intel_corei7-64 += "x11-base"
IMAGE_FEATURES_intel_core2-32  += "x11-base"

IMAGE_LINGUAS = "en-us fr-fr de-de es-es hi-in"

IMAGE_INSTALL += "haproxy"
IMAGE_INSTALL += "calaos-server calaos-web"
IMAGE_INSTALL += "calaos-mobile"

IMAGE_INSTALL += "lua-socket"
IMAGE_INSTALL += "heyu"
IMAGE_INSTALL += "linuxconsoletools"

XSERVER_append_intel-corei7-64 = "xf86-video-vesa"
XSERVER_append_intel-core2-32 = "xf86-video-vesa"
