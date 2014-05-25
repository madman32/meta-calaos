FILESEXTRAPATHS := "${THISDIR}/${PN}"

PRINC := "${@int(PRINC) + 1}"

SRC_URI += "file://libEGL.so"

do_install_append() {
    # patch include files to find vcos_platform_types.h
    find ${D}/${prefix}/include/interface -type f -exec sed -i 's/vcos_platform.h/pthreads\/vcos_platform.h/g' {} \;
    find ${D}/${prefix}/include/interface -type f -exec sed -i 's/vcos_platform_types.h/pthreads\/vcos_platform_types.h/g' {} \;

    #Overwrite libEGL.so with the Hacked version of EGL found here : https://www.youtube.com/watch?v=8Fy63w6WxOw
    cp ${WORKDIR}/libEGL.so ${D}/${libdir}/libEGL.so

}



