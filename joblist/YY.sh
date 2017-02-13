lkp install $LKP_SRC/jobs/YY.yaml
lkp split $LKP_SRC/jobs/YY.yaml
lkp run $LKP_SRC/YY-1x.yaml
lkp collect -c testcase=YY -o /result/YY/YY_`hostname`.csv
