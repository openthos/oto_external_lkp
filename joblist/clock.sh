lkp install $LKP_SRC/jobs/clock.yaml
lkp split $LKP_SRC/jobs/clock.yaml
lkp run $LKP_SRC/clock-1x.yaml
lkp collect -c testcase=clock -o /result/clock/clock_`hostname`.csv
