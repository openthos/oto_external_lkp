#!/system/xbin/sh
am start -n net.wechat.t/net.wechat.t.StartActivity
for i in `seq 1 100`
do
echo $i
date
sleep 1
done
