lkp install $LKP_SRC/jobs/ebizzy.yaml
lkp split $LKP_SRC/jobs/ebizzy.yaml
lkp run $LKP_SRC/ebizzy-200%-5x-5s.yaml
lkp collect -c testcase=ebizzy  -o /result/ebizzy/ebizzy_`hostname`.csv
