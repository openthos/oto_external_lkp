lkp install $LKP_SRC/jobs/xiuxiu.yaml
lkp split $LKP_SRC/jobs/xiuxiu.yaml
lkp run $LKP_SRC/xiuxiu-1x.yaml
lkp collect -c testcase=xiuxiu -o /result/xiuxiu/xiuxiu_`hostname`.csv
