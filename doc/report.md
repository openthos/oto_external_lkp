# openthos自动测试分析课题结题报告
在这里维护结题报告的素材列表和文档，幻灯片放在[内核分析wiki](http://os.cs.tsinghua.edu.cn/research/kernel/Openthos4H170pro2016#A.2BZeVf1w-)上；

## 自动测试分析系统框架

[自动测试分析系统框架](framework.md)描述整个测试分析系统的模块划分和各模块间的数据交互接口。

## 自动编译
1. ISO的编译（[基于git](compiler/git.md)（敖权）、[基于docker](compiler/docker.md)（王建兴））
1. 测试用例的编译（[测试用例接口规范](compiler/interface.md)、[GUI的命令行编译](compiler/gui.md)（张琳苹）、[LKP的编译框架](compiler/lkp.md)（薛海龙、毛英明））

## 自动部署：
1. [QEMU环境](deploy/qemu.md)（LKP的部署框架：QEMU的加载参数）（曹睿东）
1. 真实环境（[双系统部署](deploy/pair.md)、[chroot](deploy/chroot.md)）（毛英明）

## 自动测试：
1. [并发测试](test/concurrent.md)（敖权）
1. [GUI的自动测试用例](test/gui.md)（张琳苹）
1. [LKP测试](test/lkp.md)（毛英明、薛海龙）
1. [YAML文件到sh脚本的转换过程](yaml_to_job.sh.md)（毛英明）
1. [CTS测试](test/cts.md)（敖权）
1. 测试错误恢复（[重启](test/reboot.md)、[发邮件](test/email.md)）（敖权）

## 测试结果分析：
1. [原始输出信息展现](analysis/reveal.md)（测试结果过滤）（毛英明、张燕妮）
1. [JSON结果抽取](analysis/json.md)（张琳苹）
1. [图表展现](analysis/chart.md)（毛英明、雷蕾）
