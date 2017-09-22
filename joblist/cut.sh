lkp install $LKP_SRC/jobs/cut.yaml
lkp split $LKP_SRC/jobs/cut.yaml
lkp run $LKP_SRC/cut-1x.yaml
lkp collect -c testcase=cut -o /result/cut/cut_`hostname`.csv
