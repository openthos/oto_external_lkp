lkp install $LKP_SRC/jobs/kindle.yaml
lkp split $LKP_SRC/jobs/kindle.yaml
lkp run $LKP_SRC/kindle-1x.yaml
lkp collect -c testcase=kindle -o /result/kindle/kindle_`hostname`.csv
