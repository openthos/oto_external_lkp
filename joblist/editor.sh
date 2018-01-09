lkp install $LKP_SRC/jobs/editor.yaml
lkp split $LKP_SRC/jobs/editor.yaml
lkp run $LKP_SRC/editor-1x.yaml
lkp collect -c testcase=editor -o /result/editor/editor_`hostname`.csv
