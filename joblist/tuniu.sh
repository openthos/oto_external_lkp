lkp install $LKP_SRC/jobs/tuniu.yaml
lkp split $LKP_SRC/jobs/tuniu.yaml
lkp run $LKP_SRC/tuniu-1x.yaml
lkp collect -c testcase=tuniu -o /result/tuniu/tuniu_`hostname`.csv
