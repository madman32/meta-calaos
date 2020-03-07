DESCRIPTION = "The tool for beautiful monitoring and metric analytics & dashboards for Graphite, InfluxDB & Prometheus & More"

SRC_URI = "https://dl.grafana.com/oss/release/grafana-${PV}.linux-amd64.tar.gz"
SRC_URI_armv6 = "https://dl.grafana.com/oss/release/grafana-${PV}.linux-armv6.tar.gz"
SRC_URI_armv7a = "https://dl.grafana.com/oss/release/grafana-${PV}.linux-armv7.tar.gz"
SRC_URI_armv7ve = "https://dl.grafana.com/oss/release/grafana-${PV}.linux-armv7.tar.gz"
SRC_URI_armv8a = "https://dl.grafana.com/oss/release/grafana-${PV}.linux-arm64.tar.gz"

SRC_URI += " \
    file://grafana.service \
    file://grafana-server \
"

SRC_URI[md5sum] = "bbb232ba9b7aaf1e7d226a49564d712b"
SRC_URI[sha256sum] = "0a8bc55949aa920682b3bde99e9b1b87eef2c644bde8f8a48fa3ac746920d2ba"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=31f6db4579f7bbf48d02bff8c5c3a6eb"

inherit systemd

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/grafana-cli ${D}${bindir}/grafana-cli
    install -m 0755 ${S}/bin/grafana-server ${D}${bindir}/grafana-server

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/grafana.service ${D}${systemd_system_unitdir}/grafana.service
    
    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/grafana-server ${D}${sysconfdir}/default/
    
    install -d ${D}${sysconfdir}/grafana
    install -m 0644 ${S}/conf/sample.ini ${D}${sysconfdir}/grafana/grafana.ini

    for d in dashboards datasources notifiers
    do
        install -d ${D}${sysconfdir}/grafana/provisioning/${d}
        install -m 0644 ${S}/conf/provisioning/${d}/sample.yaml ${D}${sysconfdir}/grafana/provisioning/${d}/sample.yaml
    done

    # install frontend
    install -d ${D}${datadir}/grafana
    cp -R --no-dereference --preserve=mode,links -v \
    	${S}/public \
	${S}/conf \
	${S}/tools \
	${S}/LICENSE \
	${S}/VERSION \
    	${D}${datadir}/grafana/
}

INSANE_SKIP_${PN} = "ldflags already-stripped build-deps"

SYSTEMD_SERVICE_${PN} = "\
    grafana.service \
"

SYSTEMD_AUTO_ENABLE_${PN} = "disable"

FILES_${PN} += "\
    ${systemd_unitdir} \
    ${sysconfdir}/grafana \
    ${sysconfdir}/default \
"

