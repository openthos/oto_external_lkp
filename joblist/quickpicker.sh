lkp install $LKP_SRC/jobs/quickpicker.yaml
lkp split $LKP_SRC/jobs/quickpicker.yaml
lkp run $LKP_SRC/quickpicker-1x.yaml
lkp collect -c testcase=quickpicker -o /result/quickpicker/quickpicker_`hostname`.csv
