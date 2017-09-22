lkp install $LKP_SRC/jobs/candycrush.yaml
lkp split $LKP_SRC/jobs/candycrush.yaml
lkp run $LKP_SRC/candycrush-1x.yaml
lkp collect -c testcase=candycrush -o /result/candycrush/candycrush_`hostname`.csv
