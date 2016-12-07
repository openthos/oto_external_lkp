# QEMU环境下的自动部署-LKP的部署框架：QEMU的加载参数（曹睿东）
## android-x86 通过qemu内核传参启动

1. 编译生成android_x86_64.iso系统镜像，获取启动内核"kernel"和初始化ram disk "initrd.img";
2. 运行：
```
qemu-system-x86_64 -enable-kvm -kernel ./kernel -initrd ./initrd.img -append "initrd=/initrd.img root=/dev/ram0 androidboot.hardware=android_x86_64 text SRC= DATA= BOOT_IMAGE=/kernel" -cdrom android_x86_64.iso
```
-append 后接内核启动参数

## LKP初步运行在Android-x86

https://github.com/elliott10/lkp-tests/tree/run

正常运行LKP： `run-qemu ./jobs/ebizzy.yaml x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b vmlinuz-4.2.0-rc8`
最终通过qemu及其参数运行： `qemu-system-x86_64 -enable-kvm xxxxxx`

综上可实现 LKP初步运行在Android-x86：
```
qemu-system-x86_64 -enable-kvm -fsdev local,id=test_dev,path=/result/ebizzy/200%-4x-10s/chy-KVM/debian-x86_64.cgz/x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b/5,security_model=none -device virtio-9p-pci,fsdev=test_dev,mount_tag=9p/virtfs_mount -kernel ./kernel-android -initrd ./initrd-android.img -append "initrd=/initrd.img root=/dev/ram0 androidboot.hardware=android_x86_64 text SRC= DATA= BOOT_IMAGE=/kernel user=lkp job=/lkp/scheduled/kvm/ebizzy.yaml ARCH=x86_64 kconfig=x86_64-rhel branch=master commit=c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b max_uptime=3300 RESULT_ROOT=/result/ebizzy/200%-4x-10s/chy-KVM/debian-x86_64.cgz/x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b/5 LKP_SERVER=192.168.0.119 earlyprintk=ttyS0,115200 systemd.log_level=err debug apic=debug sysrq_always_enabled rcupdate.rcu_cpu_stall_timeout=100 panic=-1 softlockup_panic=1 nmi_watchdog=panic oops=panic load_ramdisk=2 prompt_ramdisk=0 console=ttyS0,115200 vga=normal rw ip=dhcp result_service=9p/virtfs_mount" -smp 2 -m 2048M -no-reboot -watchdog i6300esb -rtc base=localtime -device e1000,netdev=net0 -netdev user,id=net0 -serial stdio -drive file=/tmp/vdisk-root/disk0-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk1-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk2-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk3-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk4-chy-KVM,media=disk,if=virtio -drive file=/tmp/vdisk-root/disk5-chy-KVM,media=disk,if=virtio -cdrom android_x86_64.iso
```
如果需要在android-x86内核中完全运行起LKP jobs，则需要把LKP测试使用的文件系统整合到android-x86默认文件系统中
## LKP qemu测试系统初始化流程
```
init -->/lib/systemd/systemd --> /etc/init.d --> /etc/init.d/lkp-bootstrap --> run jobs
```
## LKP测试文件系统

1. QEMU虚拟文件IO：virtio-9p，把host中的某文件夹虚拟挂载进guest测试系统中，LKP用于测试结果的保存。参数：`-fsdev local,id=test_dev,path=/result/ebizzy/200%-4x-10s/chy-KVM/debian-x86_64.cgz/x86_64-rhel/gcc-4.9/c13dcf9f2d6f5f06ef1bf79ec456df614c5e058b/5,security_model=none -device virtio-9p-pci,fsdev=test_dev,mount_tag=9p/virtfs_mount`
1. QEMU通过initrd参数作为启动RamDisk，LKP用于基于Debian的文件系统，再加上打包的LKP源程序、jobs执行脚本/lkp等。参数：`-initrd /tmp/initrd-14065`
