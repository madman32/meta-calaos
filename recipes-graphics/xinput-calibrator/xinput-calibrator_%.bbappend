
do_install_append() {

    #remove autostart calibration files, we don't want them in calaos-os
    rm -f ${D}${sysconfdir}/X11/Xsession.d/30xinput_calibrate.sh
    rm -f ${D}${sysconfdir}/xdg/autostart/xinput_calibrator.desktop
}

