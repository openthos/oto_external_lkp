#!/system/xbin/sh -x
##su -  #会导致后面的命令不执行了，因此需要提前切换到root账号，执行本脚本
testcase=$1
CHMOUNT=/data/ubuntu
BUSYBOX=/system/xbin/busybox
export PATH=/system/xbin/:$PATH 

source  /data/lkp_test/before_chroot.sh


if [ ! -d $CHMOUNT ];then
   mkdir $CHMOUNT
fi
umount  $CHMOUNT
##看android x86的isolinux的判断目录是否mount的写法。
sleep 3
mount -t ext4 /dev/block/sda2 $CHMOUNT
sleep 3
cp /data/lkp_test/ubuntu_lkp_test.sh  $CHMOUNT/root
cp /data/commitId.txt  $CHMOUNT/root

sleep 1
if [ ! "`grep inet $CHMOUNT/ubuntu/etc/group`" ];then
    echo "inet:x:3003:root" >> $CHMOUNT/etc/group
fi

sleep 2
if [ ! "`grep net_raw $CHMOUNT/ubuntu/etc/group`" ];then
    echo "net_raw:x:3004:root" >> $CHMOUNT/etc/group
fi

sleep 3
echo "nameserver 8.8.8.8" > $CHMOUNT/etc/resolv.conf
sleep 1
hostname -F /etc/hostname






export PATH=/usr/bin:/usr/sbin:/bin:/sbin:$PATH

mount -t sysfs sysfs $CHMOUNT/sys
mount -t proc proc $CHMOUNT/proc
cp /data/lkp_test/mount-static  /system/bin
chmod 777 /system/bin/mount-static
mount-static --bind /dev $CHMOUNT/dev
sleep 2
mount -t devpts devpts $CHMOUNT/dev/pts
sleep 2
if [ ! -d  $CHMOUNT/dev/shm ];then
    mkdir  $CHMOUNT/dev/shm 
fi
sleep 2
mount -t tmpfs tmpfs $CHMOUNT/dev/shm
sleep 2
chroot $CHMOUNT  su - root -c "/root/ubuntu_lkp_test.sh  $testcase"

##下面的代码会在chroot 结束以后执行
echo "chroot over"
echo 'do not  ctrl+c please wait until prompt all done'
#pkill nc
