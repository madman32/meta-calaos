DESCRIPTION = "Latest compat-drivers drivers"
HOMEPAGE = "https://backports.wiki.kernel.org/index.php/Main_Page"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
RDEPENDS_${PN} = "wireless-tools"
INC_PR = "r1"

SHRT_VER = "${@d.getVar('PV',1).split('-')[0]}"

SRC_URI = " \
    https://www.kernel.org/pub/linux/kernel/projects/backports/stable/v${SHRT_VER}/backports-${PV}.tar.xz \
"

S = "${WORKDIR}/backports-${PV}"

inherit module

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D} -Wno-error prefix=${D} ARCH=${ARCH} CROSS_COMPILE=${CROSS_COMPILE}"

SELECTED_DRIVERS ??= "alx ath9k"
#Possible values : alx ar5523 ath10k ath5k ath6kl ath9k b43 b43legacy brcmfmac brcmsmac carl9170 cw1200 hwsim ieee802154 igb iwlwifi media nfc regulator rtlwifi wcn36xx wifi wil6210 wwany

MAKE_TARGETS = "defconfig-selected"

do_configure_append() {
    for i in ${SELECTED_DRIVERS}; do
    	cat defconfigs/$i >> selected
    done
    mv selected defconfigs/
}
