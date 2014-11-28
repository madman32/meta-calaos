#disable neon cpu for raspberrypi, it is not done automatically bu EFL
EXTRA_OECONF_append_raspberrypi = "--disable-neon"


