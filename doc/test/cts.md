# 自动测试-CTS测试（敖权）
## CTS测试
* CTS测试首先需要下载CTS测试包，该测试包并不包含在整个测试框架之中
* CTS测试采用网络连接openthos，在局域网中网速可以保证测试正常进行，CTS测试过程中不允许重启
* 由于CTS测试中有一些测试包并不是openthos开发所关心的，为了节约测试时间，选出了一些开发关心的测试包，并写入了一个plan文件中[TS-filtered.xml](http://os.cs.tsinghua.edu.cn/research/kernel/OpenthosCtsTesting2016?action=AttachFile&do=view&target=CTS-filtered.xml)
* 在CTS测试结果中加入commit号，以方便查看，修改脚本为[addCommitId.sh](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/addCommitId.sh) 
* CTS测试在整个测试框架中被当成一个测试用例，对应web展示中的一个文件夹，测试结果位于testResult.xml中，点击可以打开该链接，CTS测试的结果中，最前面是摘要信息，可以看到哪些测试包失败了，并且点击可以看到详细的信息
