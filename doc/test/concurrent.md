# 自动测试-并发测试（敖权）
[ppt链接](http://os.cs.tsinghua.edu.cn/research/kernel/OpenthosCtsTesting2016?action=AttachFile&do=view&target=openthos_pres_aquan.pptx)
## 自动化测试
### 定时测试
* 测试程序的入口脚本是[updateGit.sh](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/kernelci-analysis/updateGIT.sh)，该脚本首先会去检查github中仓库[OTO]（https://github.com/openthos/OTO） 是否有更新，OTO仓库相当于一个标志仓库，openthos的repo中包含的github仓库只要任何一个有更新就会导致OTO仓库的更新，这样可以updateGit.sh脚本通过检测OTO仓库可以知道是否存在了代码的更新，如果有更新则进行编译。
* 测试框架使用crontab来定时的进行测试，即每隔10分钟便运行一遍updateGit.sh脚本，当updateGit.sh脚本运行结束之后动态修改crontab的配置文件（/var/spool/cron/crontabs/root），设置
为10分钟后再次运行updateGit.sh

### 编译
* updateGit.sh发现有代码更新后，便进入了编译过程，编译采用了一共有两套机制
* 最初采用的是直接从github获取源码的方式进行编译，编译信息[普通编译](https://github.com/openthos/oto_external_lkp/blob/master/doc/compiler/git.md)，但是后来由于实验室的网络访问问题，
便修改为从内网中的服务器中获取代码进行编译
* 目前编译过程位于docker容器中，编译具体信息链接[docker中编译](https://github.com/openthos/oto_external_lkp/blob/master/doc/compiler/docker.md)

### 安装openthos 
* 在机器中安装openthos，目前分为两种环境——QEMU模拟器和真机，根据安装环境不同，部署openthos的方式也不一样。
* QEMU模拟器相对简单，只需要将实现准备好的raw格式的虚拟磁盘挂在进来，将iso的相应内容拷贝到指定的位置中即可。
* 真机中部署openthos相对麻烦，先需要准备一台ubuntu15.10的机器，并且预留一部分磁盘空间，测试机服务器通过rsync将iso以及相应的脚本拷贝到被测试机中，然后被测试机执行脚本进行分区
、拷贝以及修改ubuntu grub启动顺序等。详细信息参考[双系统部署](https://github.com/openthos/oto_external_lkp/blob/master/doc/deploy/pair.md)
* 安装完openthos之后，启动openthos，测试服务器连接上openthos之后还需要进行一些环境的设置以及安装CTS测试的apk，此处会重启一次

### 测试 
* CTS测试
* GUI测试
* LKP测试  
* 测试需要注意一个配置文件[testcaseReboot.txt](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/testcaseReboot.txt)，该配置文件指定了每测试完一个GUI或者LKP的测试用例之后，是否重启机器

### 测试结果
* 测试结果首先会临时放在测试目录中，然后被拷贝到apache的根目录，目前位于/mnt/freenas/result

-------
## 并发测试 
* 由于测试一遍的时间很长，完整的CTS测试一遍的时间大概在21个小时，虽然目前没有测试全部的CTS测试包，但是整个测试过程依旧十分耗时，因此考虑并发测试
* 并发测试的入口脚本是[paraRun.sh](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/paraRun.sh)，运行该脚本之前需要注意一个配置文件[configs](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/configs)，在该配置文件中需要执行测试的一些参数以及测试哪些机器，如果有多条配置信息，则并发进行多台机器测试
* 并发测试中的脚本只有一份，测试用例也只有一份，为了防止测试用例的结果相互覆盖，测试框架为每一个测试用（GUI和LKP）建立了一个文件夹，一次保证结果正确性
* 并发测试中，测试框架会为每一个测试机器分配监听端口，目前该端口主要用于nc命令检测机器的重启过程，端口从52001开始递增,此处需要注意，有冲突的可能性
* 并发测试不支持多个版本的iso同时测试，只能使同一个版本的iso在多台机器上测试
