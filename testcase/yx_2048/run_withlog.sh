#!/bin/bash -x
cd "$(dirname "$0")" 
ip_of_android="192.168.0.141"
tmp_result_dir="lkp_tmp_result"
./fortest.sh $ip_of_android  $tmp_result_dir  2>&1 | tee ./test_log.txt
