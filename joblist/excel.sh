lkp install $LKP_SRC/jobs/excel.yaml
lkp split $LKP_SRC/jobs/excel.yaml
lkp run $LKP_SRC/excel-1x.yaml
lkp collect -c testcase=excel -o /result/excel/excel_`hostname`.csv
