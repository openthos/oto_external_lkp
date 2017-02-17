lkp install $LKP_SRC/jobs/esfilemanager.yaml
lkp split $LKP_SRC/jobs/esfilemanager.yaml
lkp run $LKP_SRC/esfilemanager-1x.yaml
lkp collect -c testcase=esfilemanager -o /result/esfilemanager/esfilemanager_`hostname`.csv
