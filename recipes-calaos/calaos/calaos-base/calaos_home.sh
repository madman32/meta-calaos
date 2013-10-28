#!/bin/sh

export EML_ENGINE=gl
export DISPLAY=:0
cp /usr/share/calaos/default.edj /tmp/calaos_home.edj

calaos_home --theme /tmp/calaos_home.edj

