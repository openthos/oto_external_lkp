lkp install $LKP_SRC/jobs/wpspro.yaml
lkp split $LKP_SRC/jobs/wpspro.yaml
lkp run $LKP_SRC/wpspro-1x.yaml
lkp collect -c testcase=wpspro -o /result/wpspro/wpspro_`hostname`.csv
