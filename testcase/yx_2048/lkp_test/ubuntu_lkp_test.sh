#!/bin/bash -x
testjob="$1"
echo  "i am in ubuntu: `hostname`"
hostname -F /etc/hostname
echo  "i am in ubuntu changed_hostname: `hostname`"
apt-get update
echo "install expect"
apt-get install -y expect
a=$(which expect)
if  [ ! -n "$a" ] ;then
    echo "expect installation failed"
    exit
else
    echo "install git"
    apt-get install -y git
    b=$(which git)
    if  [ ! -n "$b" ] ;then
        echo "git installation failed"
        exit
    else
	echo "install ruby-git"
        apt-get install -y ruby-git
        c=$(dpkg -l | grep ruby-git)
        if  [ ! -n "$c" ] ;then
            echo "ruby-git installation failed"
            exit
        fi
    fi
fi
#apt-get install -y expect
#apt-get install -y git
#apt-get install -y ruby-git
pwd
#rm -rf ./oto_lkp
#rm -rf  /result/
#git clone https://github.com/openthos/oto_lkp.git
#cd ./oto_lkp/lkp-tests-master
# rm -rf ./oto_lkp
rm -rf  /result/
if  [ ! -d "./oto_lkp" ] ;then
    git clone https://github.com/openthos/oto_lkp.git
    cd ./oto_lkp/lkp-tests-master
else
    cd ./oto_lkp
    git pull
    cd lkp-tests-master
fi
export LKP_SRC=$PWD
export PATH=$PATH:$LKP_SRC/bin
source  ../joblist/$testjob
echo "lkp test over, leave ubuntu"
