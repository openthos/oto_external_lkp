lkp install $LKP_SRC/jobs/flashmaster.yaml
lkp split $LKP_SRC/jobs/flashmaster.yaml
lkp run $LKP_SRC/flashmaster-1x.yaml
lkp collect -c testcase=flashmaster -o /result/flashmaster/flashmaster_`hostname`.csv
