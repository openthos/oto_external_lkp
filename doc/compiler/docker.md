# 基于docker的ISO的编译（王建兴）
## 为什么使用docker
优点:  
docker是一个容器,我们可以搭建一个可用的环境之后方便地移动到不同的机器上就可以重新编译环境,不受host的影响  
缺点:  
编译出的docker img比只下载编译工具的size要大很多,需要较长时间的下载  
## docker编译环境的搭建(参考):
docker搭建:  
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
导入docker:可以直接使用[这个docker](https://github.com/openthos/tools_analysis/README.md)。这是仓库中保存的最新的已安装好所有需要的软件。
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
## docker的其他用途:
启动docker时相当于启动一台虚拟的机器,我们知道在内核启动后会执行一系列用户活动的初始化,因此可以定制在启动docker时启动编译任务;
```
/root/.bashrc
添加编译的动作,可以起到启动docker时自动编译的效果
```
