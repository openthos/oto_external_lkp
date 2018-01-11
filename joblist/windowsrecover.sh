lkp install $LKP_SRC/jobs/windowsrecover.yaml
lkp split $LKP_SRC/jobs/windowsrecover.yaml
lkp run $LKP_SRC/windowsrecover-1x.yaml
lkp collect -c testcase=windowsrecover -o /result/windowsrecover/windowsrecover_`hostname`.csv
