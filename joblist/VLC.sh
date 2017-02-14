lkp install $LKP_SRC/jobs/VLC.yaml
lkp split $LKP_SRC/jobs/VLC.yaml
lkp run $LKP_SRC/VLC-1x.yaml
lkp collect -c testcase=VLC -o /result/VLC/VLC_`hostname`.csv
