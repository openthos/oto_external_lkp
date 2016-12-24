# QEMU环境下的自动部署-LKP的部署框架：QEMU的加载参数（曹睿东）
## android-x86 通过qemu传参启动
### 使用LiveCD模式
1. 编译生成android_x86_64.iso系统镜像，获取启动内核"kernel"和初始化ram disk "initrd.img";
2. 运行：
```
qemu-system-x86_64 -enable-kvm -kernel ./kernel -initrd ./initrd.img -append "initrd=/initrd.img root=/dev/ram0 androidboot.hardware=android_x86_64 text SRC= DATA= BOOT_IMAGE=/kernel" -cdrom android_x86_64.iso
```
-append 后接内核启动参数
### 使用安装模式
参考[在QEMU上运行](http://www.android-x86.org/documents/qemuhowto)和[在virtualbox上运行](http://www.android-x86.org/documents/virtualboxhowto#Advanced)的官方文档，创建一个磁盘镜像
```
qemu-img create -f raw android.raw 8G
qemu-system-x86_64 -enable-kvm -m 4G -cdrom android_x86.iso -vga std -serial stdio -hda android.raw -boot d
```
进入debug mode  

1. "fdisk /dev/sda", then type:
 1. "n" (new partition)  
 2. "p" (primary partition)  
 3. "1" (1st partition)  
 4. "1" (first cylinder)  
 5. "xx" (choose the last cylinder, leaving room for a 2nd partition)  
 6. "w" (write the partition)  
2. Repeat #1, but call it partition 2, and use the remaining cylinders.
3. "mdev -s"  
4. "mke2fs -j -L DATA /dev/sda1"  
5. "mke2fs -j -L SDCARD /dev/sda2"  
6. Reboot ("reboot -f")  
7. 进入安装模式，安装在第一个分区，安装grub，但不要选择安装uefi  

安装完成后，关闭qemu，重新运行
```
qemu-system-x86_64 -enable-kvm -m 4G -vga std -serial stdio -drive file=android-x86-6.0.raw,format=raw,index=0,media=disk
```
## LKP初步运行在Android-x86

正常运行LKP： `run-qemu ./jobs/ebizzy.yaml x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b vmlinuz-4.2.0-rc8`
最终通过qemu及其参数运行： `qemu-system-x86_64 -enable-kvm xxxxxx`

综上可实现 LKP初步运行在Android-x86：
```
qemu-system-x86_64 -enable-kvm -fsdev local,id=test_dev,path=/result/ebizzy/200%-4x-10s/chy-KVM/debian-x86_64.cgz/x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b/5,security_model=none -device virtio-9p-pci,fsdev=test_dev,mount_tag=9p/virtfs_mount -kernel ./kernel-android -initrd ./initrd-android.img -append "initrd=/initrd.img root=/dev/ram0 androidboot.hardware=android_x86_64 text SRC= DATA= BOOT_IMAGE=/kernel user=lkp job=/lkp/scheduled/kvm/ebizzy.yaml ARCH=x86_64 kconfig=x86_64-rhel branch=master commit=c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b max_uptime=3300 RESULT_ROOT=/result/ebizzy/200%-4x-10s/chy-KVM/debian-x86_64.cgz/x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b/5 LKP_SERVER=192.168.0.119 earlyprintk=ttyS0,115200 systemd.log_level=err debug apic=debug sysrq_always_enabled rcupdate.rcu_cpu_stall_timeout=100 panic=-1 softlockup_panic=1 nmi_watchdog=panic oops=panic load_ramdisk=2 prompt_ramdisk=0 console=ttyS0,115200 vga=normal rw ip=dhcp result_service=9p/virtfs_mount" -smp 2 -m 2048M -no-reboot -watchdog i6300esb -rtc base=localtime -device e1000,netdev=net0 -netdev user,id=net0 -serial stdio -drive file=/tmp/vdisk-root/disk0-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk1-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk2-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk3-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk4-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk5-chy-KVM,media=disk,if=virtio -cdrom android_x86_64.iso
```

其中qemu自带的参数是以-开头，包括：  
* -enable-kvm 开启虚拟化  
* -fsdev 指定文件系统
* -device 指定设备  
* -kernel 指定内核  
* -initrd 指定初始的ramdisk
* -smp 指定smp数量
* -append 自定义添加参数  
* ......  

测试用例相关的参数都在-append的字符串中，包括：
* root 指定根目录  
* BOOT_IMAGE 指定启动镜像目录
* user 指定登录用户 
* job 指定开机启动的任务 
* ARCH 测试的平台
* kconfig 内核对应的配置
* RESULT_ROOT 指定测试用例的结果存放目录  
* ......

## LKP qemu测试系统初始化流程
```
init -->/lib/systemd/systemd --> /etc/init.d --> /etc/init.d/lkp-bootstrap --> run jobs
```
## LKP测试文件系统

1. QEMU虚拟文件IO：virtio-9p，把host中的某文件夹虚拟挂载进guest测试系统中，LKP用于测试结果的保存。参数：`-fsdev local,id=test_dev,path=/result/ebizzy/200%-4x-10s/chy-KVM/debian-x86_64.cgz/x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b/5,security_model=none -device virtio-9p-pci,fsdev=test_dev,mount_tag=9p/virtfs_mount`
1. QEMU通过initrd参数作为启动RamDisk，LKP用于基于Debian的文件系统，再加上打包的LKP源程序、jobs执行脚本/lkp等。参数：`-initrd /tmp/initrd-14065`
