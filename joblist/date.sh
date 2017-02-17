lkp install $LKP_SRC/jobs/date.yaml
lkp split $LKP_SRC/jobs/date.yaml
lkp run $LKP_SRC/date-1x.yaml
lkp collect -c testcase=date -o /result/date/date_`hostname`.csv
