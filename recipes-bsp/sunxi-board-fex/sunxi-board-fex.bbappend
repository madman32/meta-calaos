do_compile_prepend() {
    sed -i 's/MAC = "000000000000"/MAC = "00CE39B6257E"/g' ${S}/${SUNXI_FEX_FILE}
    sed -i 's/fb0_framebuffer_num = 2/fb0_framebuffer_num = 3/g' ${S}/${SUNXI_FEX_FILE}
    cp ${S}/${SUNXI_FEX_FILE} ${S}/${SUNXI_FEX_FILE}.hdmi
    sed -i 's/screen0_output_type = 4/screen0_output_type = 3/g' ${S}/${SUNXI_FEX_FILE}
    sed -i 's/screen0_output_mode = 5/screen0_output_mode = 4/g' ${S}/${SUNXI_FEX_FILE}
    sed -i 's/screen1_output_type = 1/screen1_output_type = 3/g' ${S}/${SUNXI_FEX_FILE}
}

do_compile_append() {
    fex2bin "${S}/${SUNXI_FEX_FILE}.hdmi" > "${B}/${SUNXI_FEX_BIN_IMAGE}.hdmi"
    cp ${B}/${SUNXI_FEX_BIN_IMAGE}.hdmi ${B}/${SUNXI_FEX_BIN_IMAGE}
}

do_deploy_append() {
    install -m 0644 ${B}/${SUNXI_FEX_BIN_IMAGE}.hdmi ${DEPLOYDIR}/
}
