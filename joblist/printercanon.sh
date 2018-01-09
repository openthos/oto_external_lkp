lkp install $LKP_SRC/jobs/printercanon.yaml
lkp split $LKP_SRC/jobs/printercanon.yaml
lkp run $LKP_SRC/printercanon-1x.yaml
lkp collect -c testcase=printercanon -o /result/printercanon/printercanon_`hostname`.csv
