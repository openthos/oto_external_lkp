lkp install $LKP_SRC/jobs/gugeinput.yaml
lkp split $LKP_SRC/jobs/gugeinput.yaml
lkp run $LKP_SRC/gugeinput-1x.yaml
lkp collect -c testcase=gugeinput -o /result/gugeinput/gugeinput_`hostname`.csv
