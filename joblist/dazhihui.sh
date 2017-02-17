lkp install $LKP_SRC/jobs/dazhihui.yaml
lkp split $LKP_SRC/jobs/dazhihui.yaml
lkp run $LKP_SRC/dazhihui-1x.yaml
lkp collect -c testcase=dazhihui -o /result/dazhihui/dazhihui_`hostname`.csv
