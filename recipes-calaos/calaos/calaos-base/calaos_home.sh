#!/bin/sh

export ELM_ENGINE=gl
export DISPLAY=:0
cp /usr/share/calaos/default.edj /tmp/calaos_home.edj

mkdir -p /home/root/.cache/calaos /etc/calaos

calaos_home --theme /tmp/calaos_home.edj --config /etc/calaos --cache /home/root/.cache/calaos --set-elm-config

