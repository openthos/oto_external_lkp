lkp install $LKP_SRC/jobs/seafile.yaml
lkp split $LKP_SRC/jobs/seafile.yaml
lkp run $LKP_SRC/seafile-1x.yaml
lkp collect -c testcase=seafile -o /result/seafile/seafile_`hostname`.csv
