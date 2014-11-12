#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

mkdir /proc
mkdir /sys
mount -t proc proc /proc
mount -t sysfs sysfs /sys

mknod -m 600 /dev/loop0 b 7 0
mknod -m 600 /dev/loop1 b 7 1

mkdir -p /mnt/boot
mount /dev/mmcblk0p1 /mnt/boot
losetup /dev/loop0 /mnt/boot/calaos-os.btrfs
btrfstune -S 1 /dev/loop0
mkdir /mnt/calaos-os
mount /dev/loop0 /mnt/calaos-os

if [ -! -f /mnt/boot/calaos-os-user.btrfs ]; then
    dd if=/dev/zero of=/mnt/boot/calaos-os-user.btrfs count=1 bs=500M
    losetup /dev/loop1 /mnt/boot/calaos-os-user.btrfs
    mkfs.btrfs /dev/loop1
elif
    losetup /dev/loop1 /mnt/boot/calaos-os-user.btrfs
fi

btrfs device add /dev/loop1 /mnt/calaos-os
mount -o remount,rw /mnt/calaos-os

cd /mnt/calaos-os
switch_root . /bin/init

exec sh
