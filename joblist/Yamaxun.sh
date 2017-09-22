lkp install $LKP_SRC/jobs/Yamaxun.yaml
lkp split $LKP_SRC/jobs/Yamaxun.yaml
lkp run $LKP_SRC/Yamaxun-1x.yaml
lkp collect -c testcase=Yamaxun -o /result/Yamaxun/Yamaxun_`hostname`.csv
