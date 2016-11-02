#!/system/bin/sh
##su -  #会导致后面的命令不执行了，因此需要提前切换到root账号，执行本脚本
CHMOUNT=/data/ubuntu
if [ ! -d $CHMOUNT ];then
    mkdir $CHMOUNT
fi
umount  $CHMOUNT
##看android x86的isolinux的判断目录是否mount的写法。
mount -t ext4 /dev/block/sda2 $CHMOUNT


if [ ! "`grep net_raw /data/ubuntu/etc/group`" ];then
    echo "inet:x:3003:root" >> $CHMOUNT/etc/group
    echo "net_raw:x:3004:root" >> $CHMOUNT/etc/group
    echo "nameserver 192.168.0.1" $CHMOUNT/etc/resolv.conf
fi


export PATH=/usr/bin:/usr/sbin:/bin:/sbin:$PATH

mount -t sysfs sysfs $CHMOUNT/sys
mount -t proc proc $CHMOUNT/proc
cp /data/mount-static  /system/bin
chmod 777 /system/bin/mount-static
mount-static --bind /dev $CHMOUNT/dev
mount -t devpts devpts $CHMOUNT/dev/pts
chroot $CHMOUNT  su -

##下面的代码会在chroot结束以后执行
echo "chroot over"
