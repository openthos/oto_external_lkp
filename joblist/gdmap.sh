lkp install $LKP_SRC/jobs/gdmap.yaml
lkp split $LKP_SRC/jobs/gdmap.yaml
lkp run $LKP_SRC/gdmap-1x.yaml
lkp collect -c testcase=gdmap -o /result/gdmap/gdmap_`hostname`.csv
