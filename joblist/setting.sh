lkp install $LKP_SRC/jobs/setting.yaml
lkp split $LKP_SRC/jobs/setting.yaml
lkp run $LKP_SRC/setting-1x.yaml
lkp collect -c testcase=setting -o /result/setting/setting_`hostname`.csv
