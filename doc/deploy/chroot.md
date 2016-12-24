# 真实环境下的自动部署-chroot（毛英明）
##为什么需要chroot？
lkp运行需要apt-get,ruby,git，make,glibc动态链接库等环境支持。而这些环境在androidx86下面不具备。如果强行将lkp移植到androidx86环境下面，需要将lkp的运行流程拆分为两个阶段：第一阶段：monitor和benchmark部分（大部分是c程序和shell脚本）在androidx86上面运行并获取原始数据，然后传回到linux系统上面。 第二阶段：
在linux上面使用ruby程序对第一阶段获取的原始数据进行离线处理（算平均值，方差，整合）为json加csv格式。

其中第一阶段：shell程序由于shell版本的不使兼容，需要进行适当修改。c程序用glibc等动态链接库的，需要静态编译，对于androidx86上面不兼容或者没有的应用程序(stdbuf,vmstat,ps,nproc,truncat等)需要静态编译。有些程序可以静态编译，但是有的无法静态编译(例如ps,vmstat)。而且lkp需要在/lkp和/result目录下面分别存放benchmark可执行文件和结果数据，而androidx86的/分区是一个ramdisk，重启以后数据就会消失，还有cat,gzip等程序存放路径和linux路径不同，这些都需要通过symbol link或者修改PATH环境变量来解决。总之困难和工作量都比较大。


由于androidx86底层也是基于linux kernel,而且是运行在x86指令集上，因此android x86的kernel可以很好的通过chroot进行环境变量和根文件系统的隔离，使得x86_64版本的ubuntu根文件系统运行起来，使得lkp不需要任何修改即可在chroot ubuntu下面运行，并且采集android kernel信息。

##chroot后带来的问题以及解决方法
chroot带来不需要修改lkp代码即可运行lkp好处的同时，也带来一些负面效应。 

由于chroot根文件系统的隔离，使得在chroot ubuntu里面没法直接执行androidx86里面的命令，例如pm,am等等。因此在chroot前，在androidx86上面开启了telnet服务，使得chroot ubuntu可以通过expect+telnet客户端，向androidx86发送命令，执行命令，达到协作目的。

## 基于chroot的LKP测试
 * [通过chroot运行lkp测试](https://github.com/openthos/oto_lkp/blob/master/doc/test/lkp.md#为了通过chroot运行lkp并且测试androidx86额外增加的文件)
