lkp install $LKP_SRC/jobs/jishigou.yaml
lkp split $LKP_SRC/jobs/jishigou.yaml
lkp run $LKP_SRC/jishigou-1x.yaml
lkp collect -c testcase=jishigou -o /result/jishigou/jishigou.csv
