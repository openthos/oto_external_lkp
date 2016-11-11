## 安装nbench
- apt-get update
- lkp install ./jobs/nbench.yaml【如果wget下载失败，修改pack/nbench的地址为本地，如：http://192.168.x.x/nbench-byte-2.2.3.tar.gz】
- ls /lkp/benchmarks/nbench/ -lh【查看是否安装成功】
## split job
- lkp split ./jobs/nbench.yaml
## lkp run
- lkp run ./nbench-1x.yaml 
## result
在result/nbench/ 文件夹之下
