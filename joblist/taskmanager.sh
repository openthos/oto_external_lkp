lkp install $LKP_SRC/jobs/taskmanager.yaml
lkp split $LKP_SRC/jobs/taskmanager.yaml
lkp run $LKP_SRC/taskmanager-1x.yaml
lkp collect -c testcase=taskmanager -o /result/taskmanager/taskmanager_`hostname`.csv
