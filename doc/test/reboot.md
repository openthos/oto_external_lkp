# 自动测试-基于重启的测试错误恢复（敖权）
* 测试过程中，可能发生adb连接不上openthos或者是测试过程中由于某些测试操作导致网络不通的情况，目前对于这些问题，采用监控程序来监控测试服务器与被测试机之间的adb连通性
* adb断开连接常常表现为在测试过程中，测试脚本卡死，既不测试，也不结束，导致后面的测试无法正常进行，而且也没法正常结束
* 测试过程中，测试服务器通过adb shell启动openthos本地的一个脚本[testAliveReceive.sh](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/testAliveReceive.sh)，该脚本每隔100秒检测一次[alive.txt](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/alive.txt) 文件最后的写入时间，超过300秒没有检测到更新，则认为已经与测试服务器断开了连接，这时openthos自动重启。而测试服务器会运行[testAliveSend.sh](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/testAliveSend.sh) 每隔100秒像openthos发送alive.txt文件，如果网络断开或者adb断开会导致该发送过程失败，从而使得openthos自动重启。
* 为了防止测试卡死，在测试过程中，加入超时机制，将测试程序放在后台执行，测试框架监控测试测试脚本，一般GUI测试最长为15分钟，而LKP测试最长为30分钟，目前为止，该时间能够保证一个测试结束，截止最长时间后，如果发现某一个测试程序未结束，则杀掉该进程，并尝试adb从新连接，如果连接不上，则等待600秒后再次连接，之所以等待600秒，是为了等待openthos重启，如果600秒后仍然无法连接，则说明机器出了严重故障——adb无法连接而且自身无法重启，很有可能是机器全部死掉（这种情况出现的可能性比较小），则email通知。
* 出现测试框架无法自动修复的错误时，则结束与某一机器相关的全部测试内容，其他机器不受影响。
