lkp install $LKP_SRC/jobs/printermopria.yaml
lkp split $LKP_SRC/jobs/printermopria.yaml
lkp run $LKP_SRC/printermopria-1x.yaml
lkp collect -c testcase=printermopria -o /result/printermopria/printermopria_`hostname`.csv
