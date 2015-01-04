DESCRIPTION = "Image with Calaos."

IMAGE_FEATURES += "splash package-management x11-base ssh-server-dropbear"

LICENSE = "MIT"

inherit core-image

IMAGE_LINGUAS = "en-us fr-fr de-de es-es hi-in"

IMAGE_INSTALL += "connman"
IMAGE_INSTALL += "lighttpd lighttpd-module-fastcgi lighttpd-module-compress lighttpd-module-alias lighttpd-module-rewrite lighttpd-module-redirect lighttpd-module-proxy"
IMAGE_INSTALL += "calaos-server calaos-home calaos-web calaos-base"
IMAGE_INSTALL += "tzdata tzdata-europe ntp"
IMAGE_INSTALL += "avahi-utils"
IMAGE_INSTALL += "heyu"
IMAGE_INSTALL += "nano htop procps"
IMAGE_INSTALL += "linuxconsoletools"
IMAGE_INSTALL += "openssl ca-certificates"
IMAGE_INSTALL += "cpu-performance"
IMAGE_INSTALL += "connman-wait-online connman-client connman-autoconnect"
IMAGE_INSTALL += "setxkbmap"
IMAGE_INSTALL += "wget"

IMAGE_INSTALL += "edje-utils elementary-tests"

XSERVER_append_nuc = "xf86-video-vesa"
XSERVER_append_n450 = "xf86-video-vesa"
