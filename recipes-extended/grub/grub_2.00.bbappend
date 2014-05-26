FILESEXTRAPATHS_prepend_nuc := "${THISDIR}/${PN}:"
FILESEXTRAPATHS_prepend_n450 := "${THISDIR}/${PN}:"

PRINC_nuc := "${@int(PRINC) + 1}"
PRINC_n450 := "${@int(PRINC) + 1}"

