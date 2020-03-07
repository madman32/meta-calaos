include influxdb.inc

inherit go systemd

SRC_URI += "\
	file://influxdb.service \
	file://influxdb.conf \
"

GO_IMPORT = "github.com/influxdata/influxdb/cmd/influxd"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/influxdb.service ${D}${systemd_system_unitdir}/influxdb.service
    install -d ${D}/${sysconfdir}/influxdb
    install -m 0644 ${WORKDIR}/influxdb.conf ${D}${sysconfdir}/influxdb/influxdb.conf
}

SYSTEMD_SERVICE_${PN} = "\
    influxdb.service \
"

SYSTEMD_AUTO_ENABLE_${PN} = "disable"

FILES_${PN} += "\
	${systemd_unitdir} \
	${sysconfdir}/influxdb \
"

