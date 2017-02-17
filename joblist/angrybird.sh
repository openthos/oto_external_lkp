lkp install $LKP_SRC/jobs/angrybird.yaml
lkp split $LKP_SRC/jobs/angrybird.yaml
lkp run $LKP_SRC/angrybird-1x.yaml
lkp collect -c testcase=angrybird -o /result/angrybird/angrybird_`hostname`.csv
