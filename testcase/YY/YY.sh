#!/bin/bash -x
androidIP=$1
port=$2
foldName=$3
cd "$(dirname "$0")"
if  [ -z "$foldName"  ] ; then
 echo "foldName is empty !"
 exit
fi
mkdir $foldName
#adb -s $androidIP:$port push ebizzy /data/local/tmp
#adb -s $androidIP:$port shell /data/local/tmp/ebizzy > $foldName/testResult

filename=$(basename $0)
if  [  -d $foldName  ];then
      trash=$RANDOM  
      mkdir -p /tmp/lkp_trash/$trash 
      mv   $foldName/* /tmp/lkp_trash/$trash
fi
 source /etc/profile
./YYMake.sh
adb connect $androidIP
adb -s $androidIP:$port   push ./bin/YY.jar /data/local/tmp

adb -s $androidIP:$port  shell  rm -rf  /data/lkp_test
adb -s $androidIP:$port  shell  mkdir /data/lkp_test
adb -s $androidIP:$port   push ./lkp_test /data/lkp_test
adb -s $androidIP:$port   shell busybox chmod +x /data/lkp_test/chroot_run.sh

adb -s $androidIP:$port   shell /data/lkp_test/chroot_run.sh $filename

filename1=${filename%.sh}
mkdir $foldName/$filename1
adb -s $androidIP:$port pull  /data/ubuntu/result/$filename1  $foldName/$filename1/

rm -rf /tmp/lkp_trash/


echo "test over! all done!"
