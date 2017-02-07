lkp install $LKP_SRC/jobs/QQ.yaml
lkp split $LKP_SRC/jobs/QQ.yaml
lkp run $LKP_SRC/QQ-1x.yaml
lkp collect -c testcase=QQ -o /result/QQ/QQ_`hostname`.csv
