lkp install $LKP_SRC/jobs/system_ui.yaml
lkp split $LKP_SRC/jobs/system_ui.yaml
lkp run $LKP_SRC/system_ui-1x.yaml
lkp collect -c testcase=system_ui -o /result/system_ui/system_ui_`hostname`.csv
