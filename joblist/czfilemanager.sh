lkp install $LKP_SRC/jobs/czfilemanager.yaml
lkp split $LKP_SRC/jobs/czfilemanager.yaml
lkp run $LKP_SRC/czfilemanager-1x.yaml
lkp collect -c testcase=czfilemanager -o /result/czfilemanager/czfilemanager_`hostname`.csv
