lkp install $LKP_SRC/jobs/powerpoint.yaml
lkp split $LKP_SRC/jobs/powerpoint.yaml
lkp run $LKP_SRC/powerpoint-1x.yaml
lkp collect -c testcase=powerpoint -o /result/powerpoint/powerpoint_`hostname`.csv
