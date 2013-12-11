#!/bin/sh

TSDRIVER=`calaos_config get touchscreen_driver`

if [ "$TSDRIVER" == "serial" ]; then
    echo "Starting inputattach..."
    inputattach --daemon -elo /dev/ttyUSB0
fi

