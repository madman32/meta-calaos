do_compile_prepend() {
    sed -i 's/MAC = "000000000000"/MAC = "00CE39B6257E"/g' ${S}/${SUNXI_FEX_FILE}  
}