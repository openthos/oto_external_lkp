# 真实环境下的自动部署-双系统部署（毛英明）
##测试对象，以及设计原理说明
* PC2  
* PC2为测试对象,上面安装了linux和android_x86双系统  
   linux系统的作用跟android手机的fastboot功能是一样的，用来刷系统，或者更新系统中的一个分区。

 PC2上面的linuxOS安装到了/dev/sda2,android安装到了/dev/sda40【可以通过配置文件指定】,ESP分区为/dev/sda1,测试程序中的参数也是这么设定的(见原理图)。如果想变换分区，需要修改*.sh中对应的参数，程序注释中也给出了提示。   
* PC1  
* PC1为是存放程序和数据的地方，用于远程控制PC2,PC3，PC4...  
而且可以通过修改autoN.sh配置文件中的以下两个参数达到控制多台设备的效果。不同的设备对应不同的IP地址。  
ip_linux="192.168.2.16"【PCN的IP地址】  
ip_android="192.168.2.58"【PCN上面的android IP地址】  

* 特点：
 1. 【fastboot for androidx86】模拟了android 中的fastboot功能，参数也很相似，通用于测试android手机设备。  
 2. 【kickstart for androidx86】利用kickstart的无人值守安装机制，结合【fastboot for androidx86】实现了androidx86在baremetal下面的无人值守安装。
 2. 使用rsync可以方便的同步脚本和数据，而且是增量的，而且可执行权限也不需要加，由于是镜像的，所以排错很容易。本地测试远程测试都很方便 
 3. 一份代码，只需要修改配置文件，即可同时应用于远程控制多台设备。  


* 工作原理图  
![Aaron Swartz](https://raw.githubusercontent.com/xyongcn/openthos-testing/master/bare_metal_autotest/android_auto/android_x86%E7%9C%9F%E5%AE%9E%E6%9C%BA%E5%99%A8%E8%87%AA%E5%8A%A8%E6%B5%8B%E8%AF%95%E6%A1%86%E6%9E%B6.JPG)

##详细部署过程见如下链接：
https://github.com/xyongcn/openthos-testing/blob/master/bare_metal_autotest/android_auto/README.md
