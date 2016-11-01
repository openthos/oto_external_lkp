# oto_lkp

## 概述
目标： 在openthos的chroot环境下面运行lkp，获取测试结果的json数据和csv数据

代码目录说明：
lkp-tests-master目录存放的是lkp的源代码，其可以在androidx86的chroot ubuntu环境上面运行。

lkpebizzy是用来通过adb远程在androidx86上面运行的ebizzy测试用例。 

lkpnbench是用来通过adb远程在androidx86上面运行的nbench测试用例。 

##本地测试方法
```
环境准备
PC1 运行linux 
PC2 linux和androidx86双系统。linux在sda2【目前必须在sda2，代码写死了在sda2】,androidx86安装在sda40【非必须sda40】


在PC1上面执行以下操作即可完成lkp的本地化测试以验证测试用例是否正常执行。
su - root
cd ~
git clone https://github.com/openthos/oto_lkp

cd ~/oto_lkp/lkpebizzy
修改 fortst.sh
中ip_of_android="192.168.2.8"【PC2 androidx86 ip地址】

root@elwin-virtual-machine:~/oto_lkp/lkpebizzy# ./fortest.sh 
执行完毕以后结果存放在PC1的~/oto_lkp/lkpebizzy/ebizzy_result目录下面。请检查是否出现json文件。并且json文件是否有内容。
如果有内容则测试用例成功执行了。
```

[lkpebizzy在androidx86上面的测试日志](https://github.com/openthos/oto_lkp/blob/master/lkpebizzy_test_log.txt) 

[lkpebizzy在androidx86上面的测试数据](https://github.com/openthos/oto_lkp/tree/master/lkpebizzy/ebizzy_result)

## TODO
- 新增新增测试用例的方法
- 将lkp collect的代码merge进来，达到将json数据转换为csv的目的

##感谢
- openthos项目提供的chroot环境支持
- lkp collet相关人员的代码贡献。
