#!/bin/bash -x
ip_of_android="192.168.2.74"
tmp_result_dir="lkp_tmp_result"
testcasename=$(basename `pwd`)
cd "$(dirname "$0")" 
apt-get install -y android-tools-adb
adb connect $ip_of_android

adb -s $ip_of_android:5555   push ./commitId.txt /data/

./$testcasename.sh  $ip_of_android  5555   $tmp_result_dir
