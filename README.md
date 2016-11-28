# oto_lkp

##使用前必读
lkp本身需要在root权限执行。因此以下所有操作请在root账号下操作。以免出现不必要的麻烦。  
vim /etc/hostname
修改各个机器中ubuntu系统的hostname为不同的名字，不要出现重复的hostname。

下文的PC2中的linux必须是ubuntu15.10 x86-64版本，并且以uefi+gpt形式安装到PC2。必须注意，以免做无用功。
## 概述
目标： 在openthos的chroot环境下面运行lkp，获取测试结果的json数据和csv数据

代码目录说明：
lkp-tests-master目录存放的是lkp的源代码，其可以在androidx86的chroot ubuntu环境上面运行。 

testcase目录存放的是打包好的测试用例。
## 原理以及功能描述
- 通过chroot使得lkp可以运行在androidx86内核上面的ubuntu环境中。使得lkp可以采集androidx86的内核信息。
- 通过adb达到自动化测试框架远程控制lkp运行
- 为了让gui app在运行的同时，lkp可以采集androidx86内核的信息，增加了chrooted ubuntu和androidx86通过telnet+expect通信协作的功能。

##自动运行测试用例的方法【用于合并到自动化测试框架】：
```
git clone https://github.com/openthos/oto_lkp
cd oto_lkp
cd -
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
生成的csv文件存放在lkp_tmp_result/result/ebizzy(即testcase的名字)目录下面，请检查是否出现csv文件。并且csv文件是否有内容。
如果有内容则测试用例成功执行了。
测试日志存放在PC1的~/oto_lkp/testcase/lkpebizzy/test_log.txt文件当中。
```

[lkpebizzy在androidx86上面的测试日志](https://github.com/openthos/oto_lkp/blob/master/testcase/lkpebizzy/test_log.txt) 

[lkpebizzy在androidx86上面的测试数据json](https://github.com/openthos/oto_lkp/tree/master/testcase/lkpebizzy/lkp_tmp_result) 

[lkpebizzy在androidx86上面的测试数据csv](https://github.com/openthos/oto_lkp/tree/master/testcase/lkpebizzy/lkp_tmp_result/result/ebizzy)
## TODO
- 编写新增新增测试用例的文档 https://github.com/openthos/oto_lkp/issues/3
- 增加dev分支。https://github.com/openthos/oto_lkp/issues/4  

##感谢
- openthos项目提供的chroot环境支持
- [lkp collet](https://github.com/openthos/lkp-analysis)等相关人员的代码贡献。
- [lkp项目原始出处](https://github.com/fengguang/lkp-tests)本repo中lkp-tests-master中的代码是其2016.6月份左右的一个版本。

##LKP官方帮助信息
- https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/README.md
- https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/doc/lkp-howto.md
