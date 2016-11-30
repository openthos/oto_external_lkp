### LKP官方需要添加的文件
- 在oto_lkp/lkp-tests-master/目录下的pack、stats、tests、jobs中添加相应的文件。例如jishigou：
  + https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/pack/jishigou #编译安装测试用例
  + https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/stats/jishigou #将benchmark的原始输出，转变为key:value中间结果
  + https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/tests/jishigou #benchmark的运行方法
  + https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/jobs/jishigou.yaml #描述benchmark的配置文件
- 在oto_lkp/benchmark_mirror目录下添加benchmark压缩包
  + https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1.tar.gz #benchmark压缩包

### 合并自动化测试框架需添加的文件
- 在oto_lkp/目录下的joblist、testcase、benchmark_mirror添加相应的文件。例如jishigou：
  + https://github.com/openthos/oto_lkp/blob/master/joblist/jishigou.sh #运行lkp
  + https://github.com/openthos/oto_lkp/tree/master/testcase/lkpjishigou/ #用于自动化测试框架调用的目录
  + https://github.com/openthos/oto_lkp/blob/master/testcase/lkpjishigou/run_withlog.sh #自动化测试的启动脚本
  + https://github.com/openthos/oto_lkp/blob/master/testcase/lkpjishigou/fortest.sh #adb connect openthos
  + https://github.com/openthos/oto_lkp/blob/master/testcase/lkpjishigou/lkp_test/before_chroot.sh #use telnet
  + https://github.com/openthos/oto_lkp/blob/master/testcase/lkpjishigou/lkp_test/chroot_run.sh #chroot到ubuntu
  + https://github.com/openthos/oto_lkp/blob/master/testcase/lkpjishigou/lkp_test/mount-static #chroot的工具
  + https://github.com/openthos/oto_lkp/blob/master/testcase/lkpjishigou/lkp_test/ubuntu_lkp_test.sh #chroot到ubuntu进行lkp操作
  + https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1/ #benchmark压缩包对应的目录
  + https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1/net.jishigou.t2.8.0.apk #要安装的apk
  + https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1/auto_interact.sh #启动activity，gui自动交互的脚本
  + https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1/Makefile #为之添加权限
  + https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1/jishigou #调用chroot与ubuntu交互的脚本

### chroot ubuntu和androidx86通过telnet协作,需要添加的文件
- 在oto_lkp/lkp-tests-master/pack和oto_lkp/benchmark_mirror/jishigou.v0.1/目录下添加expt脚本。例如jishigou：
 + https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/pack/jishigou_pack.expt
 + https://github.com/openthos/oto_lkp/blob/master/benchmark_mirror/jishigou.v0.1/jishigou_benchmark.expt
 
### 需要注意的问题
- 所有添加的脚本必须有可执行权限：chmod -R a+x oto_lkp/*
- 添加oto_lkp/lkp-tests-master/pack/jishigou脚本时，apk的下载链接格式需要注意是否可以下载
- 添加oto_lkp/benchmark_mirror/jishigou.v0.1/auto_interact.sh 脚本时，定义apk的操作时参考AndroidManifest.xml
- [运行oto_lkp步骤参考](https://github.com/openthos/oto_lkp/blob/master/README.md)
