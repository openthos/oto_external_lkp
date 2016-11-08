lkp install lkp install $LKP_SRC/jobs/nbench.yaml
lkp split $LKP_SRC/jobs/nbench.yaml
lkp run $LKP_SRC/nbench-1x.yaml
lkp collect -c testcase=nbench  -o /result/nbench.csv
