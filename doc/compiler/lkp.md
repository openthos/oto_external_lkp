# LKP测试框架（薛海龙、毛英明）

LKP是一个内核测试框架，可以指定内核测试需要的测试用例编译描述；指定测试用例的部署环境和部署方法；在Linux系统上运行测试用例的过程中，利用monitor监控各种内核参数指标；事后对测试过程中获取的系统参数进行分析。[wiki页面](http://os.cs.tsinghua.edu.cn/research/kernel/Openthos4H170pro2016#A20160829-lkp.2BfPt.2B31IGZ5A-)上有一些LKP的分析文档。

以eibzzy benchmark为示例进行说明。
## 下载、编译、安装benchmark
```
lkp install $LKP_SRC/jobs/ebizzy.yaml
```
lkp install过程中会使用到$LKP_SRC/pack/ebizzy文件。该文件中的WEB_URL指定了benchmark下载的URL，lkp install过程会使用wget从WEBURL中下载benchmark压缩包，并解压执行其中的makefile，来进行benchmark的编译和安装，该文件中的install函数，指定了需要把benchmark中的哪些可执行文件和数据安装到/lkp/benchmarks/ebizzy/目录下面。这样lkp run阶段就会执行/lkp/benchmarks/ebizzy/目录下面的可执行文件。


## 根据测试参数排列组合，拆分成多个原子测试任务
```
# lkp split $LKP_SRC/jobs/ebizzy.yaml
./jobs/ebizzy.yaml => ./ebizzy-200%-100x-10s.yaml
./jobs/ebizzy.yaml => ./ebizzy-200%-100x-5s.yaml
./jobs/ebizzy.yaml => ./ebizzy-200%-5x-10s.yaml
./jobs/ebizzy.yaml => ./ebizzy-200%-5x-5s.yaml
```

## 运行一个原子测试任务
```
lkp run $LKP_SRC/ebizzy-200%-5x-5s.yaml
```
lkp run的过程中会通过$LKP_SRC/tests/ebizzy来执行ebizzy对应的benchmark，通过$LKP_SRC/monitors/vmstat等命令在benchmark执行的同时，开启monitor收集系统信息。

当benchmark运行结束以后，monitors也会停止运行。然后会通过$LKP_SRC/stats/ebizzy和$LKP_SRC/stats/vmstat等命令，来对benchmark和monitors的原始输出，进行处理形成json文件。

最后lkp会通过ruby脚本把ebizzy.json，vmstat.json文件整合成一个matrix.json，并且把这次的测试结果，和之前的测试结果取平均值，形成avg.json文件。

## 将json转换为csv方便数据统计、处理以及可视化展现
```
lkp collect -c testcase=ebizzy  -o /result/ebizzy/ebizzy.csv 
```
上面的命令将json结果转换成了csv


##详细的编译过程
lkp 的benchmark是在lkp install过程中编译和安装的。 

LKP每个benchmark需要提供一个$LKP_SRC/pack/$benchmarkname文件，来实现benchmark的自动下载、编译、安装。 


##例如ebizzy
https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/pack/ebizzy
```
#!/bin/bash

VERSION=0.3
WEB_URL="https://github.com/openthos/oto_lkp/raw/master/benchmark_mirror/ebizzy-0.3.tar.gz"

install()
{
	cp -af $BM_NAME $BM_ROOT
}
```
其中WEB_URL指定了测试用例的下载URL。lkp install阶段会，通过wget从WEB_URL下载该benchmark压缩包到/tmp目录。（因为随着时间的流逝一些benmark的url失效了，导致wget失败，因此我们采取了将其原来的url(例如sourceforge)放到https://github.com/openthos/oto_lkp/tree/master/benchmark_mirror 上的措施） 

压缩包下载完毕以后，会进行解压，然后执行该目录中的./configure 然后make编译benchmark，make install,安装benchmark。(如果没有configure文件，有Makefile文件，则会直接执行make命令) 

而且还会执行/pack/$benchmarkname文件中的install()函数，把benchmark安装到/lkp/benchmarks/$benchmarkname/目录下面，并且会对/lkp/benchmarks/$benchmarkname打包成一个$benchmarkname-LKP.deb包，并且安装该deb包到/lkp/benchmarks/$benchmarkname/目录。

##新增测试用例(以jishigou apk为例)：
###需要benchmark包：
https://github.com/openthos/oto_lkp/raw/master/benchmark_mirror/jishigou.v0.1.tar.gz
https://github.com/openthos/oto_lkp/tree/master/benchmark_mirror/jishigou.v0.1
```
auto_interact.sh #该文件会被jishigou调用，用来启动apk,自动执行app
jishigou  #该文件会被lkp run调用，用来执行benchmark
jishigou_benchmark.expt  #该文件会被auto_interact.sh调用。
Makefile  
net.jishigou.t2.8.0.apk #待测试的apk
```

###需要pack文件
https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/pack/jishigou
```
#!/bin/bash

VERSION="2.2.3"
#WEB_URL="http://www.tux.org/~mayer/linux/nbench-byte-2.2.3.tar.gz"
WEB_URL="https://github.com/openthos/oto_lkp/raw/master/benchmark_mirror/jishigou.v0.1.tar.gz"

install()
{
    #make  
cp  -af $BM_NAME  $BM_ROOT
cp -af *.apk $BM_ROOT
cp -af  auto_interact.sh $BM_ROOT    ##安装自动交互脚本  
cp -af  jishigou_benchmark.expt $BM_ROOT   #安装自动交互脚本
$LKP_SRC/pack/jishigou_pack.expt         #安装apk
}
```
###由于chroot和自动安装apk需要额外增加的脚本
因为lkp运行在chroot环境中，编译完benchmark（即自动运行apk的程序）后，需要安装apk到androidx86，因此使用了telent+expect方式，在chroot ubuntu环境中发送pm install命令给androidx86，来安装apk. 

即install函数中的$LKP_SRC/pack/jishigou_pack.expt命令和
脚本https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/pack/jishigou_pack.expt
```
#!/usr/bin/expect
spawn  telnet localhost  #通过telnet连接到androidx86
expect "#"
send "pm install /data/ubuntu/lkp/benchmarks/jishigou/net.jishigou.t2.8.0.apk\r" #安装apk到androidx86
expect "#"
send "sleep 1"
expect "#"
send "exit\r"
```


