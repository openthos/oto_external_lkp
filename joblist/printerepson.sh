lkp install $LKP_SRC/jobs/printerepson.yaml
lkp split $LKP_SRC/jobs/printerepson.yaml
lkp run $LKP_SRC/printerepson-1x.yaml
lkp collect -c testcase=printerepson -o /result/printerepson/printerepson_`hostname`.csv
