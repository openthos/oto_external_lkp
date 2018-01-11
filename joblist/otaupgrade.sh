lkp install $LKP_SRC/jobs/otaupgrade.yaml
lkp split $LKP_SRC/jobs/otaupgrade.yaml
lkp run $LKP_SRC/otaupgrade-1x.yaml
lkp collect -c testcase=otaupgrade -o /result/otaupgrade/otaupgrade_`hostname`.csv
