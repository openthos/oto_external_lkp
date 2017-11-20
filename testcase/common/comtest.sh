#!/bin/bash -x
androidIP=$1
port=$2
foldName=$3

cd $(dirname "$0")
if [ -z $foldName ];then
 echo "foldName is empty !"
 exit 0
fi

if [ -d $foldName ];then
	trash=$RANDOM
	mkdir -p /tmp/lkp_trash/$trash
	mv   $foldName/* /tmp/lkp_trash/$trash/
else
	mkdir $foldName
fi

filename=$(basename $0)
testcase=${filename%.sh}
source /etc/profile

./${testcase}Make.sh $testcase

test -e "before_run.sh" && ( source before_run.sh; do_before )

adb -s $androidIP:$port push ./bin/$testcase.jar /data/local/tmp

dir_lkp_test=`adb -s $androidIP:$port shell 'test -d /data/lkp_test ; echo $?'`
if [ "${dir_lkp_test:0:1}"x != "0"x ];then
adb -s $androidIP:$port push ./lkp_test /data/
adb -s $androidIP:$port shell busybox chmod +x /data/lkp_test/chroot_run.sh
fi

adb -s $androidIP:$port shell "/data/lkp_test/chroot_run.sh $filename"

mkdir $foldName/$testcase
adb -s $androidIP:$port pull /data/ubuntu/result/$testcase $foldName/
if [ $? -ne 0 ];then
echo -e "\033[31mCan not get $testcase result!\033[0m"
exit 1
fi

sleep 2 #To avoid "error: closed"
test -e "packagename" && adb -s $androidIP:$port shell pm uninstall -k $(cat packagename)
rm -rf /tmp/lkp_trash/$trash

echo "$testcase test is over!"
