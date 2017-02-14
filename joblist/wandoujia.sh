lkp install $LKP_SRC/jobs/wandoujia.yaml
lkp split $LKP_SRC/jobs/wandoujia.yaml
lkp run $LKP_SRC/wandoujia-1x.yaml
lkp collect -c testcase=wandoujia -o /result/wandoujia/wandoujia_`hostname`.csv
