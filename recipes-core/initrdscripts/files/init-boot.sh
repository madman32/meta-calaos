#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

mkdir /proc
mkdir /sys
mount -t proc proc /proc
mount -t sysfs sysfs /sys

mknod -m 600 /dev/loop0 b 7 0
mknod -m 600 /dev/loop1 b 7 1

# mount sdcard
mkdir -p /mnt/boot
mount /dev/mmcblk0p1 /mnt/boot

#Mount current calaos-os-system partition
if [ ! -f /mnt/boot/calaos-os-system.btrfs ]; then
    echo "Unable to find calaos-os-system.btrfs, launching rescue shell ..."
    exec /bin/sh
fi

losetup /dev/loop0 /mnt/boot/calaos-os-system.btrfs
mkdir /mnt/calaos-os
mount /dev/loop0 /mnt/calaos-os

#Mount calaos-os-user partition
if [ ! -f /mnt/boot/calaos-os-user.btrfs ]; then
    # Partition doesn't exists, create a new one
    dd if=/dev/zero of=/mnt/boot/calaos-os-user.btrfs count=1 bs=100M
    losetup /dev/loop1 /mnt/boot/calaos-os-user.btrfs 
    # Format with btrfs format
    mkfs.btrfs /dev/loop1
else
    losetup /dev/loop1 /mnt/boot/calaos-os-user.btrfs
fi

# Mount calaos-os-user as home partition
mount /dev/loop1 /mnt/calaos-os/home

# Remount the whole partition in RW 
mount -o remount,rw /mnt/calaos-os

# Switch root
cd /mnt/calaos-os
exec switch_root . /sbin/init

echo "Unable to switch root and boot calaos-os system, launching rescue shell ..."
exec /bin/sh
