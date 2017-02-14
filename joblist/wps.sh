lkp install $LKP_SRC/jobs/wps.yaml
lkp split $LKP_SRC/jobs/wps.yaml
lkp run $LKP_SRC/wps-1x.yaml
lkp collect -c testcase=wps -o /result/wps/wps_`hostname`.csv
