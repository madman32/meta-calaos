
# Some more info on calibration:
# evdev calibration using xinput property "Evdev Axis Calibration" is not working anymore.
# Thus, matrix calibration can be done using xinput property "libinput Calibration Matrix"
# A fork of xinput_calibrator exists that enable this matrix calculations.
#
#   https://github.com/sebt3/xinput_calibrator
#
# Another calibration tool that does matrix (python):
#   https://github.com/reinderien/xcal
#
# Some explanations of the math:
#   https://wiki.archlinux.org/index.php/Talk:Calibrating_Touchscreen
#

DEPENDS += "libeigen"

SRCREV = "8092055ccd25b87939aa7e3005ecbed2b10de208"
SRC_URI = "git://github.com/sebt3/xinput_calibrator.git \
           file://30xinput_calibrate.sh \
          "

do_install_append() {

    #remove autostart calibration files, we don't want them in calaos-os
    rm -f ${D}${sysconfdir}/X11/Xsession.d/30xinput_calibrate.sh
    rm -f ${D}${sysconfdir}/xdg/autostart/xinput_calibrator.desktop
}

