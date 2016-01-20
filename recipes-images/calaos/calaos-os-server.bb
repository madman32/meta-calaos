DESCRIPTION = "Image with Calaos."

LICENSE = "MIT"

include calaos-os-minimal.bb

IMAGE_INSTALL += "calaos-server calaos-web"

