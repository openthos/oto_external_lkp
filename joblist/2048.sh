lkp install $LKP_SRC/jobs/2048.yaml
lkp split $LKP_SRC/jobs/2048.yaml
lkp run $LKP_SRC/2048-1x.yaml
lkp collect -c testcase=2048 -o /result/2048/2048_`hostname`.csv
