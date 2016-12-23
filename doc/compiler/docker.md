# 基于docker的ISO的编译（王建兴）
## 为什么使用docker
我们采用ubuntu OS作为编译环境,但是不同版本的ubuntu上需要的编译工具可能不同:  
- 1.ubuntu有多个:12,14,16,不同版本的ubuntu上相同的工具可能具有不同的名称;  
高版本上可能就不支持低版本的工具:16.04上不支持openjdk-7.0;  
ubuntu版本可能不同:桌面版,服务器版等;  
- 2.不同机器上安装相同版本的ubuntu可能都不一致,底层hardware不同导致安装的依赖硬件的工具不同;  
所以我们采用了docker:Docker是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 Linux 机器上。  
我们可以将编译环境打包然后部署到任何一台安装了docker的机器上,隔离了这些系统和硬件的差异.  

采用docker的缺点:  
编译出的docker img比只下载编译工具的size要大很多,需要较长时间的下载  
## docker编译环境的搭建(参考):
### 打包编译环境:
docker环境搭建:  
```
安装docker: apt-get install docker docker.io
启动docker daemon:sudo service docker start
查看可用的ubuntu img:sudo docker search ubuntu
下载ubuntu docker img:sudo docker pull ubuntu:latest
启动进入docker:docker run -ti ubuntu:latest
进入之后需要apt-get update,然后安装必要的工具
```
编译工具安装:  
https://github.com/openthos/openthos/wiki/Establishing-a-Build-Environment%28lollipop-x86%29  

将自己安装的软件打包到镜像中:
```
docker save IMAGE-ID -o containter.tar
```
导出docker:
```
docker export <CONTAINER ID>  container.tar  
```
### 部署编译环境:
安装docker:   
apt-get install docker docker.io  
启动docker daemon:  
sudo service docker start  
导入docker:
可以直接使用[这个docker](https://github.com/openthos/tools_analysis/tree/master/openthos_compile_env)。这是仓库中保存的最新的已安装好所有需要的软件，这个docker的使用方法参见[帮助](https://github.com/openthos/tools_analysis/blob/master/README.md)。
```
sudo docker load < container.tar
```
创建容器:
```
sudo docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             VIRTUAL SIZE
<none>              <none>              8a5f82246607        7 weeks ago         951.8 MB
sudo docker run -it -v /home/oto/largeHD/work:/root/oto --name oto-5.1 8a5f82246607 /bin/bash
```
启动容器:
```
sudo docker start -ai oto-5.1
```
### 编译生成安装镜像
下载openthos:

编译:
```
source build/envsetup.sh
lunch 你的编译目标
make iso_img
```
生成iso  
## docker的其他用途:
启动docker时相当于启动一台虚拟的机器,我们知道在内核启动后会执行一系列用户活动的初始化,因此可以定制在启动docker时启动编译任务;
```
/root/.bashrc
添加编译的动作,可以起到启动docker时自动编译的效果
```
