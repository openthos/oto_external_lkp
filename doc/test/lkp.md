# LKP测试框架工作过程（毛英明、薛海龙）
以eibzzy benchmark为示例进行说明。
## 下载、编译、安装测试用例
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



#lkp benchmark下载、编译安装的详细过程
###例如ebizzy
https://github.com/openthos/oto_lkp/blob/master/joblist/ebizzy.sh
```
lkp install $LKP_SRC/jobs/ebizzy.yaml  #安装测试用例
lkp split $LKP_SRC/jobs/ebizzy.yaml    #根据测试参数排列组合，形成多个测试任务
lkp run $LKP_SRC/ebizzy-200%-5x-5s.yaml  #执行其中一个测试任务，收集benchmark和monitor信息，形成json文件
lkp collect -c testcase=ebizzy  -o /result/ebizzy/ebizzy.csv #收集某一项benchmark不同情况排列组合，的数据形成 csv文件
```
##为了通过chroot运行lkp并且测试androidx86额外增加的文件
###安装过程额外增加的文件
https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/pack/jishigou_pack.expt 

目的是为了执行pm install命令安装apk，如果不是测试apk,即运行普通的benchmark（例如ebizzy,nbench）则不需要额外增加此类型文件。


###运行过程额外增加的文件（存在于benchmark测试包中）
https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1/jishigou_benchmark.expt 

目的是为了执行am,uiautomator等程序，自动启动apk,app自动交互，如果不是测试apk,即运行普通的benchmark（例如ebizzy,nbench）则不需要额外增加此类型文件。
