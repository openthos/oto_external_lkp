lkp install $LKP_SRC/jobs/printerhp.yaml
lkp split $LKP_SRC/jobs/printerhp.yaml
lkp run $LKP_SRC/printerhp-1x.yaml
lkp collect -c testcase=printerhp -o /result/printerhp/printerhp_`hostname`.csv
