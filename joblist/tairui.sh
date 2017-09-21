lkp install $LKP_SRC/jobs/tairui.yaml
lkp split $LKP_SRC/jobs/tairui.yaml
lkp run $LKP_SRC/tairui-1x.yaml
lkp collect -c testcase=tairui -o /result/tairui/tairui_`hostname`.csv
