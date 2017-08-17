lkp install $LKP_SRC/jobs/appStore.yaml
lkp split $LKP_SRC/jobs/appStore.yaml
lkp run $LKP_SRC/appStore-1x.yaml
lkp collect -c testcase=appStore -o /result/appStore/appStore_`hostname`.csv
