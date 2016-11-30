#!/bin/bash -x
<<<<<<< HEAD
ip_of_android="192.168.2.52"
=======
ip_of_android="192.168.0.138"
>>>>>>> c880f2ac823971469c0667d13ec1d4bcec2874b4
tmp_result_dir="lkp_tmp_result"
cd "$(dirname "$0")" 
apt-get install -y android-tools-adb
adb connect $ip_of_android

adb -s $ip_of_android:5555   push ./commitId.txt /data/

./lkpjishigou.sh  $ip_of_android  5555   $tmp_result_dir
