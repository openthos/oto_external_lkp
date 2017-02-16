lkp install $LKP_SRC/jobs/fennec.yaml
lkp split $LKP_SRC/jobs/fennec.yaml
lkp run $LKP_SRC/fennec-1x.yaml
lkp collect -c testcase=fennec -o /result/fennec/fennec_`hostname`.csv
