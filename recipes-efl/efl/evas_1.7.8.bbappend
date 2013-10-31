#force building gl (sunxi-mali) before evas otherwise evas will not build gles support
PRINC := "${@int(PRINC) + 1}"
DEPENDS += "virtual/libgles2 virtual/egl"
RRECOMMENDS_${PN} += "evas-engine-gl-x11"
