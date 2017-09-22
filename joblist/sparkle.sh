lkp install $LKP_SRC/jobs/sparkle.yaml
lkp split $LKP_SRC/jobs/sparkle.yaml
lkp run $LKP_SRC/sparkle-1x.yaml
lkp collect -c testcase=sparkle -o /result/sparkle/sparkle_`hostname`.csv
