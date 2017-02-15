lkp install $LKP_SRC/jobs/outlook.yaml
lkp split $LKP_SRC/jobs/outlook.yaml
lkp run $LKP_SRC/outlook-1x.yaml
lkp collect -c testcase=outlook -o /result/outlook/outlook_`hostname`.csv
