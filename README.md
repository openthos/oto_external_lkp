# oto_lkp

## 概述
目标： 在openthos的chroot环境下面运行lkp，获取测试结果的json数据和csv数据

代码目录说明：
lkp-tests-master目录存放的是lkp的源代码，其可以在androidx86的chroot ubuntu环境上面运行。 

testcase目录存放的是打包好的测试用例。

##自动运行测试用例的方法【用于合并到自动化测试框架】：
```
git clone https://github.com/openthos/oto_lkp
mv ./oto_lkp/testcase/*   somewhere
rm -rf ./oto_lkp
cd somewhere
for adir in `ls `
do
 ./$adir.sh
done
```

##本地测试方法【用于单元测试】
```
环境准备
PC1 运行linux 
PC2 linux和androidx86双系统。linux在sda2【目前必须在sda2，代码写死了在sda2，而且系统必须是ubuntu15.10 x86-64，16.04chroot后apt-get 工作不正常】,androidx86安装在sda40【非必须sda40】


在PC1上面执行以下操作即可完成lkp的本地化测试以验证测试用例是否正常执行。
su - root
cd ~
git clone https://github.com/openthos/oto_lkp

cd ~/oto_lkp/testcase/lkpebizzy
修改 fortest.sh
中ip_of_android="192.168.2.8"【PC2 androidx86 ip地址】

root@elwin-virtual-machine:~/oto_lkp/testcase/lkpebizzy# ./run_withlog.sh
执行完毕以后结果存放在PC1的~/oto_lkp/testcase/lkpebizzy/lkp_tmp_result目录下面。请检查是否出现json文件。并且json文件是否有内容。
如果有内容则测试用例成功执行了。
测试日志存放在PC1的~/oto_lkp/testcase/lkpebizzy/test_log.txt文件当中。
```

[lkpebizzy在androidx86上面的测试日志](https://github.com/openthos/oto_lkp/blob/master/testcase/lkpebizzy/test_log.txt) 

[lkpebizzy在androidx86上面的测试数据](https://github.com/openthos/oto_lkp/tree/master/testcase/lkpebizzy/lkp_tmp_result)

## TODO
- 新增新增测试用例的方法
- 将lkp collect的代码merge进来，达到将json数据转换为csv的目的

##感谢
- openthos项目提供的chroot环境支持
- lkp collet相关人员的代码贡献。
- [lkp项目原始出处](https://github.com/fengguang/lkp-tests)本repo中lkp-tests-master中的代码是其2016.6月份左右的一个版本。
