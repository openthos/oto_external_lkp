lkp install $LKP_SRC/jobs/weibo.yaml
lkp split $LKP_SRC/jobs/weibo.yaml
lkp run $LKP_SRC/weibo-1x.yaml
lkp collect -c testcase=weibo -o /result/weibo/weibo_`hostname`.csv
