lkp install $LKP_SRC/jobs/launcher.yaml
lkp split $LKP_SRC/jobs/launcher.yaml
lkp run $LKP_SRC/launcher-1x.yaml
lkp collect -c testcase=launcher -o /result/launcher/launcher_`hostname`.csv
