lkp install $LKP_SRC/jobs/yingsuo.yaml
lkp split $LKP_SRC/jobs/yingsuo.yaml
lkp run $LKP_SRC/yingsuo-1x.yaml
lkp collect -c testcase=yingsuo -o /result/yingsuo/yingsuo_`hostname`.csv
