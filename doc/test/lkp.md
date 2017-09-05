# 自动测试-LKP测试（毛英明、薛海龙）
##lkp的测试过程
###例如ebizzy
https://github.com/openthos/oto_external_lkp/blob/master/joblist/ebizzy.sh
```
lkp install $LKP_SRC/jobs/ebizzy.yaml  #安装测试用例
lkp split $LKP_SRC/jobs/ebizzy.yaml    #根据测试参数排列组合，形成多个测试任务
lkp run $LKP_SRC/ebizzy-200%-5x-5s.yaml  #执行其中一个测试任务，收集benchmark和monitor信息，形成json文件
lkp collect -c testcase=ebizzy  -o /result/ebizzy/ebizzy.csv #收集某一项benchmark不同情况排列组合，的数据形成 csv文件
```
##为了通过chroot运行lkp并且测试androidx86额外增加的文件
###安装过程额外增加的文件
https://github.com/openthos/oto_external_lkp/blob/master/lkp-tests-master/pack/jishigou_pack.expt 

目的是为了执行pm install命令安装apk，如果不是测试apk,即运行普通的benchmark（例如ebizzy,nbench）则不需要额外增加此类型文件。


###运行过程额外增加的文件（存在于benchmark测试包中）
https://github.com/openthos/oto_external_lkp/blob/master/benchmark_mirror/jishigou.v0.1/jishigou_benchmark.expt 

目的是为了执行am,uiautomator等程序，自动启动apk,app自动交互，如果不是测试apk,即运行普通的benchmark（例如ebizzy,nbench）则不需要额外增加此类型文件。
