#!/system/xbin/sh
am start -n  com.tencent.mm/com.tencent.mm.ui.LauncherUI
for i in `seq 1 100`
do
mkdir aaa
java -jar testuiauto.jar 192.168.0.55 5555  otoAutoTest.jar aaa
echo $i
date
sleep 1
done
