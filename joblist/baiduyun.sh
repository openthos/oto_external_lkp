lkp install $LKP_SRC/jobs/baiduyun.yaml
lkp split $LKP_SRC/jobs/baiduyun.yaml
lkp run $LKP_SRC/baiduyun-1x.yaml
lkp collect -c testcase=baiduyun -o /result/baiduyun/baiduyun_`hostname`.csv
