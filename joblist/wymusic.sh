lkp install $LKP_SRC/jobs/wymusic.yaml
lkp split $LKP_SRC/jobs/wymusic.yaml
lkp run $LKP_SRC/wymusic-1x.yaml
lkp collect -c testcase=wymusic -o /result/wymusic/wymusic_`hostname`.csv
