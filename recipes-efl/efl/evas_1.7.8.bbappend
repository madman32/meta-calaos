#force building gl (sunxi-mali) before evas otherwise evas will not build gles support
DEPENDS_mele += "sunxi-mali"
DEPENDS_cubieboard += "sunxi-mali"

RRECOMMENDS_${PN} += "evas-engine-gl-x11"
