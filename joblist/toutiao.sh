lkp install $LKP_SRC/jobs/toutiao.yaml
lkp split $LKP_SRC/jobs/toutiao.yaml
lkp run $LKP_SRC/toutiao-1x.yaml
lkp collect -c testcase=toutiao -o /result/toutiao/toutiao_`hostname`.csv
