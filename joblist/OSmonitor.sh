lkp install $LKP_SRC/jobs/OSmonitor.yaml
lkp split $LKP_SRC/jobs/OSmonitor.yaml
lkp run $LKP_SRC/OSmonitor-1x.yaml
lkp collect -c testcase=OSmonitor -o /result/OSmonitor/OSmonitor_`hostname`.csv
