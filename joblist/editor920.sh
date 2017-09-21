lkp install $LKP_SRC/jobs/editor920.yaml
lkp split $LKP_SRC/jobs/editor920.yaml
lkp run $LKP_SRC/editor920-1x.yaml
lkp collect -c testcase=editor920 -o /result/editor920/editor920_`hostname`.csv
