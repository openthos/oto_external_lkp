### LKP官方需要添加的文件（以下均以wechat为例）
- 在oto_lkp/lkp-tests-master/目录下的pack、stats、tests、jobs中添加相应的文件
 
 + lkp-tests-master/pack/wechat #编译安装测试用例
 
   ```
  #!/bin/bash
  VERSION="2.2.3"
# wechat需要的所有文件的压缩包
WEB_URL="https://github.com/openthos/oto_lkp/raw/master/benchmark_mirror/wechat.v0.1.tar.gz"
install()
{  
#将压缩包的文件都cp到/data/ubuntu/lkp/benchmarks/下
cp  -af $BM_NAME  $BM_ROOT
cp -af *.apk $BM_ROOT
cp -af *.jar $BM_ROOT
cp -af  auto_interact.sh $BM_ROOT  
cp -af  wechat_benchmark.expt $BM_ROOT   
$LKP_SRC/pack/wechat_pack.expt
}
   ```
 + lkp-tests-master/stats/wechat #将benchmark的原始输出，转变为key:value中间结果
 
 + lkp-tests-master/tests/wechat #benchmark的运行方法
  
   ```
   #!/bin/sh
# - test
cd $BENCHMARK_ROOT/wechat || exit
start_time=$(date +%s)
for i in $(seq 1 $iterations)
do
	echo Iteration: $i
	cmd ./wechat
done
   ```
 
 + kp-tests-master/jobs/wechat.yaml #描述benchmark的配置文件
 
   ```
   testcase: wechat
category: benchmark

iterations: 1x

wechat:
  duration: 10s
   ```
- 在oto_lkp/benchmark_mirror目录下添加benchmark压缩包

 + /benchmark_mirror/wechat.v0.1.tar.gz #benchmark压缩包【压缩包里的文件将在下边具体说明】
 
### 合并自动化测试框架需添加的文件
- 在oto_lkp/目录下的joblist、testcase、benchmark_mirror添加相应的文件

 + joblist/wechat.sh #运行lkp
 
```
# 运行lkp的benchmark方法
lkp install $LKP_SRC/jobs/wechat.yaml
lkp split $LKP_SRC/jobs/wechat.yaml
lkp run $LKP_SRC/wechat-1x.yaml
lkp collect -c testcase=wechat -o /result/wechat/wechat.csv
  ```
  
#### testcase/目录下面增加文件夹的方法：
以testcase/ebizzy为模板，构造wechat和nbench
```
oto_lkp/testcase# cp ebizzy/ -R  wechat
oto_lkp/testcase# mv wechat/ebizzy.sh   wechat/wechat.sh

oto_lkp/testcase# cp ebizzy/  -R nbench  
oto_lkp/testcase# mv nbench/ebizzy.sh   nbench/nbench.sh
 ```
 
 + testcase/lkpwechat/run_withlog.sh #测试的启动脚本【自己本地测试时的启动脚本】
  
 + testcase/lkpwechat/fortest.sh #adb connect openthos 【自己本地测试时需要的基本配置】
 
  ```
#!/bin/bash -x
# 设置openthos的ip
ip_of_android="192.168.0.55"
tmp_result_dir="lkp_tmp_result"
cd "$(dirname "$0")" 
apt-get install -y android-tools-adb
adb connect $ip_of_android
adb -s $ip_of_android:5555   push ./commitId.txt /data/
# 调用wechat测试开始的脚本
./lkpwechat.sh  $ip_of_android  5555   $tmp_result_dir
  ```
  
 + testcase/lkpwechat/lkp_test/before_chroot.sh #启动Android的telnet【自己添加用例时不需要更改此脚本】
 
  ```
cd /data/
/system/xbin/telnetd -l /system/xbin/sh
cd -
  ```
  
 + testcase/lkpwechat/lkp_test/chroot_run.sh #chroot openthos到ubuntu【自己添加用例时不需要更改此脚本】
 
 + testcase/lkpwechat/lkp_test/mount-static #chroot的工具【拷贝到自己要添加的测试用例相同目录下即可】
 
 + testcase/lkpwechat/lkp_test/ubuntu_lkp_test.sh #chroot到ubuntu进行lkp操作【自己添加用例时不需要更改此脚本】
 
```
#!/bin/bash -x
testjob="$1"
echo  "i am in ubuntu"
apt-get update
apt-get install -y expect
apt-get install -y git
apt-get install -y ruby-git
pwd
rm -rf ./oto_lkp
git clone https://github.com/openthos/oto_lkp.git
cd ./oto_lkp/lkp-tests-master
export LKP_SRC=$PWD
export PATH=$PATH:$LKP_SRC/bin
source  ../joblist/$testjob
echo "lkp test over, leave ubuntu"
  ```
 
 + benchmark_mirror/wechat.v0.1/net.wechat.t2.8.0.apk #要安装的apk
 + benchmark_mirror/wechat.v0.1/auto_interact.sh #启动activity，gui自动交互的脚本
 
  ```
#!/system/xbin/sh
# wechat.jar是自己定义的对wechat的窗口拖拉等一系列操作，并用uiautomator命令运行jar包
uiautomator runtest /data/ubuntu/lkp/benchmarks/wechat/wechat.jar -c com.autoTestUI.wechat
  ```
  
 + benchmark_mirror/wechat.v0.1/Makefile #文件夹下的文件添加权限
 
  ```
  all:
	chmod  777  ./wechat
	chmod  777  ./auto_interact.sh
	chmod  777 ./wechat_benchmark.expt
  ```
 
 + benchmark_mirror/wechat.v0.1/wechat#调用chroot与ubuntu交互的脚本
 
  ```
#!/bin/bash -x
./wechat_benchmark.expt
  ```

### chroot到ubuntu后和androidx86通过telnet协作,需要添加的文件
- 在oto_lkp/lkp-tests-master/pack和oto_lkp/benchmark_mirror/wechat.v0.1/目录下添加expt脚本
 
 + lkp-tests-master/pack/wechat_pack.expt
 
 ```
#!/usr/bin/expect
spawn  telnet localhost
expect "#"
#安装测试用例
send "pm install /data/ubuntu/lkp/benchmarks/wechat/net.wechat.t2.8.0.apk\r"
expect "#"
send "sleep 1"
expect "#"
send "exit\r"
 ```

+ benchmark_mirror/wechat.v0.1/wechat_benchmark.expt

 ```
 #!/usr/bin/expect
spawn  telnet localhost
expect "#"
#在openthos上运行自定义的对测试用例的jar包
send "/data/ubuntu/lkp/benchmarks/wechat/auto_interact.sh\r"
expect "#"
send "exit\r"
 ```
### 自动编译jar包需要添加的文件
- 在测试机上搭建编译环境，[配置文档](https://github.com/openthos/testing-analysis/blob/master/Auto-test/uiautomator/%E7%BC%96%E8%AF%91%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA.md)

- 在/oto_lkp/testcase/wechat文件夹下添加以下文件与脚本
 
 + wechatMake.sh
 ```
#!/bin/bash -x
#需要根据环境修改-t参数(现在是1)，查看5.1版本对应android stdio的id（ android list target )

android create uitest-project -n wechat -t 1 -p .
ant build
 ```
 + 对wechat进行操作的java源码文件：bin和src文件夹
 
 +修改wechat.sh脚本
 添加如下几行
 
 ```
 # 使编译环境的配置生效
 source /etc/profile
./wechatMake.sh
adb connect $androidIP
# 把自动编译的jar包push到被测试机上
adb -s $androidIP:$port  push ./bin/wechat.jar /data/local/tmp
 ```
### 需要注意的问题
- 所有添加的脚本必须有可执行权限：chmod -R a+x oto_lkp/*
- 添加oto_lkp/lkp-tests-master/pack/wechat脚本时，apk的下载链接格式需要注意是否可以下载
- 添加oto_lkp/benchmark_mirror/wechat.v0.1/auto_interact.sh 脚本时，定义apk的操作时参考AndroidManifest.xml
- [运行oto_lkp步骤参考](https://github.com/openthos/oto_lkp/blob/master/README.md)
