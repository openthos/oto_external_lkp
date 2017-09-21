lkp install $LKP_SRC/jobs/greenify.yaml
lkp split $LKP_SRC/jobs/greenify.yaml
lkp run $LKP_SRC/greenify-1x.yaml
lkp collect -c testcase=greenify -o /result/greenify/greenify_`hostname`.csv
