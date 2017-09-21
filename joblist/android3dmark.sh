lkp install $LKP_SRC/jobs/android3dmark.yaml
lkp split $LKP_SRC/jobs/android3dmark.yaml
lkp run $LKP_SRC/android3dmark-1x.yaml
lkp collect -c testcase=android3dmark -o /result/android3dmark/android3dmark_`hostname`.csv
