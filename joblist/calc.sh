lkp install $LKP_SRC/jobs/calc.yaml
lkp split $LKP_SRC/jobs/calc.yaml
lkp run $LKP_SRC/calc-1x.yaml
lkp collect -c testcase=calc -o /result/calc/calc_`hostname`.csv
