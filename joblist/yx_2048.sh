lkp install $LKP_SRC/jobs/yx_2048.yaml
lkp split $LKP_SRC/jobs/yx_2048.yaml
lkp run $LKP_SRC/yx_2048-1x.yaml
lkp collect -c testcase=yx_2048 -o /result/yx_2048/yx_2048_`hostname`.csv
