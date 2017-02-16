lkp install $LKP_SRC/jobs/jd.yaml
lkp split $LKP_SRC/jobs/jd.yaml
lkp run $LKP_SRC/jd-1x.yaml
lkp collect -c testcase=jd -o /result/jd/jd_`hostname`.csv
