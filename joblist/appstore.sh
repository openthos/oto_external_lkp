lkp install $LKP_SRC/jobs/appstore.yaml
lkp split $LKP_SRC/jobs/appstore.yaml
lkp run $LKP_SRC/appstore-1x.yaml
lkp collect -c testcase=appstore -o /result/appstore/appstore_`hostname`.csv
