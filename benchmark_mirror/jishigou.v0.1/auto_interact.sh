#!/system/xbin/sh
am start -n net.jishigou.t/net.jishigou.t.StartActivity
for i in `seq 1 100`
do
java -jar testuiauto.jar  otoAutoTest.jar 
echo $i
date
sleep 1
done
