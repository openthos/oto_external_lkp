#!/system/xbin/sh
cp /data/ubuntu/lkp/benchmarks/wechat/wechat.jar /data/local/tmp
uiautomator runtest wechat.jar -c com.autoTestUI.wechat
