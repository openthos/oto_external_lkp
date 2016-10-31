#!/system/bin/sh
mount -t ext4 /dev/block/sda2 /storage/emulated/legacy/ubuntu
export PATH=/usr/bin:/usr/sbin:/bin:/sbin:$PATH
mount -t sysfs sysfs /storage/emulated/legacy/ubuntu/sys
mount -t proc proc /storage/emulated/legacy/ubuntu/proc
cp /data/mount-static  /system/bin
chmod 777 /system/bin/mount-static
mount-static --bind /dev /storage/emulated/legacy/ubuntu/dev
mount -t devpts devpts /storage/emulated/legacy/ubuntu/dev/pts
chroot /storage/emulated/legacy/ubuntu/ su -

