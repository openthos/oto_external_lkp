# 自动测试分析系统框架（敖权）
目前编译整体的框架如下图所示
![framework](https://github.com/openthos/oto_lkp/raw/master/doc/images/testFramework1.png)
##编译 
* 每隔一个小时检测OPENTHOS所有的github代码仓库的更新情况，如果存在更新，则向[OTO](https://github.com/openthos/OTO)仓库中提交更新,OTO仓库相当于一个标志仓库，OPENTHOS的repo中包含的github仓库只要任何一个有更新就会导致OTO仓库的更新
* 测试框架的起始脚本[updateGit.sh](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/kernelci-analysis/updateGIT.sh)会定时每隔十分钟检测一次OTO仓库的更新情况，存在更新则进行测试，没有更新则十分钟后再次检查,定时运行updateGit.sh采用crontab工具,即updateGit.sh脚本运行结束之后，再设置一个十分钟后运行updateGit.sh的定时任务
* 利用repo工具管理了OPENTHOS的代码仓库，检测到更新则repo sync同步本地代码
* 代码同步完毕执行make，完成编译，生成iso 
* 编译采用了一共有两套机制: 
 * 最初采用的是直接从github获取源码的方式进行编译，编译信息[普通编译](https://github.com/openthos/oto_lkp/blob/master/doc/compiler/git.md)，但是后来由于实验室的网络访问问题，
便修改为从内网中的服务器中获取代码进行编译
 * 目前编译过程位于docker容器中，编译具体信息链接[docker中编译](https://github.com/openthos/oto_lkp/blob/master/doc/compiler/docker.md),docker中配置好了所有的编译环境，因此对外界环境依赖少，环境部署更方便

##部署
部署需要指定需要指定被测试机的IP地址以及机器名等信息，该信息有一个配置文件[configs](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/configs)指定,详细内容参照[部署文档](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/README.md)
* 在机器中安装OPENTHOS，目前分为两种环境——QEMU模拟器和真机，根据安装环境不同，部署openthos的方式也不一样
 * QEMU模拟器相对简单，只需要将实现准备好的raw格式的虚拟磁盘挂在进来，将iso的相应内容拷贝到指定的位置中即可
 * 真机中部署openthos相对麻烦，先需要准备一台ubuntu15.10的机器，并且预留一部分磁盘空间，测试机服务器通过rsync将iso以及相应的脚本拷贝到被测试机中，然后被测试机执行脚本进行分区
、拷贝以及修改ubuntu grub启动顺序等。详细信息参考[双系统部署](https://github.com/openthos/oto_lkp/blob/master/doc/deploy/pair.md)
* 安装完openthos之后，启动openthos，测试服务器连接上openthos之后还需要进行一些环境的设置以及安装CTS测试的apk，此处会重启一次
 
##测试 
目前测试采用的是多台机器之间并行测试，但是各种类型的测试采用串行测试，测试包含一下三类测试

其中GUI测试一起LKP测试需要遵守一定的[接口规范](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/kernelci-analysis/testcasereadme.md),并且测试的结果放入到指定目录，即web服务器的根目录，使得结果能够从浏览器中查看
* LKP测试
 * LKP测试目前仅仅在真实机器中进行测试，目前依赖chroot技术  
 * chroot之后可以执行完整的LKP测试，新增测试用例按照LKP的规范添加到[oto_lkp](https://github.com/openthos/oto_lkp)仓库中  
 * 测试中会自动从oto_lkp仓库中下载测试代码，进行编译、运行
 * 最后生成的结果通过adb pull取回到服务器
* GUI测试  
 * 从[oto_Uitest](https://github.com/openthos/oto_Uitest)仓库中下载测试用例的代码
 * 将测试用例的代码编译成二进制可执行文件
 * 运行测试用例，并把结果取出来
* CTS测试  
 * 筛选测试包，存入测试计划文件
测试需要注意一个配置文件[testcaseReboot.txt](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/testcaseReboot.txt)，该配置文件指定了每测试完一个GUI或者LKP的测试用例之后，是否重启机器  

测试过程中加入一些错误恢复机制，例如重启OPENTHOS、向用户发送邮件

##结果分析
测试完毕之后，所有的测试结果将通过web向用户展示,展示内容主要包含一下几个方面
* cvs曲线，以曲线图形式查看LKP测试的结果
* 测试的详细结果 
  * 快速筛选测试结果
  * 按照目录结构，逐级查找测试结果
* 编译结果的展示，列出详细的编译输出信息，有助于编译错误的定位
* 测试概要信息展现
 * 概要信息中包含测试耗费时间、GUI测试中完成情况
 * 点击在GUI测试中概要信息中可以跳转到详细信息的界面
