FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Rewrite-float-array-code-to-prevent-segfault.patch \
            file://0002-Add-code-for-outputing-Matrix-calibration-to-file.patch \
            "

