lkp install $LKP_SRC/jobs/wechat.yaml
lkp split $LKP_SRC/jobs/wechat.yaml
lkp run $LKP_SRC/wechat-1x.yaml
lkp collect -c testcase=wechat -o /result/wechat/wechat_`hostname`.csv
