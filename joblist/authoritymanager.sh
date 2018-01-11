lkp install $LKP_SRC/jobs/authoritymanager.yaml
lkp split $LKP_SRC/jobs/authoritymanager.yaml
lkp run $LKP_SRC/authoritymanager-1x.yaml
lkp collect -c testcase=authoritymanager -o /result/authoritymanager/authoritymanager_`hostname`.csv
