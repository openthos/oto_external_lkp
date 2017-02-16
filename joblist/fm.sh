lkp install $LKP_SRC/jobs/fm.yaml
lkp split $LKP_SRC/jobs/fm.yaml
lkp run $LKP_SRC/fm-1x.yaml
lkp collect -c testcase=fm -o /result/fm/fm_`hostname`.csv
