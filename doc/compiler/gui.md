# 测试用例的编译-GUI的命令行编译（张琳苹）

##编译环境搭建（参考）：
https://github.com/openthos/testing-analysis/blob/master/Auto-test/uiautomator/%E7%BC%96%E8%AF%91%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA.md

##1、查看5.1版本对应SDK的ID值
```
android list target
```
##2、创建build文件
```
android create uitest-project -n <name> -t <android-sdk-ID> -p <path>
```
（1）name为生成的jar包，可以由自己定义

（2）path为Eclipse创建的工程的路径
  - 例如：
```
android create uitest-project -n mytest -t 1 -p workspace/mytest
```
该命令会在workspace/mytest目录下生成build.xml文件 

##3、ant编译生成jar文件

进入工程目录执行
```
ant build
```

在bin目录下会生成jar文件，编译完成
