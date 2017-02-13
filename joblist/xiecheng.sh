lkp install $LKP_SRC/jobs/xiecheng.yaml
lkp split $LKP_SRC/jobs/xiecheng.yaml
lkp run $LKP_SRC/xiecheng-1x.yaml
lkp collect -c testcase=xiecheng -o /result/xiecheng/xiecheng_`hostname`.csv
