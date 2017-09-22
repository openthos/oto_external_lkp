lkp install $LKP_SRC/jobs/Taiwan.yaml
lkp split $LKP_SRC/jobs/Taiwan.yaml
lkp run $LKP_SRC/Taiwan-1x.yaml
lkp collect -c testcase=Taiwan -o /result/Taiwan/Taiwan_`hostname`.csv
