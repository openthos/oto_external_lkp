#!/bin/bash -x
testjob="$1"
echo  "i am in ubuntu"
pwd
rm -rf ./oto_lkp
git clone https://github.com/openthos/oto_lkp.git
cd ./oto_lkp/lkp-tests-master
export LKP_SRC=$PWD
export PATH=$PATH:$LKP_SRC/bin
source  ../joblist/$testjob
echo "lkp test over, leave ubuntu"
