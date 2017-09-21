lkp install $LKP_SRC/jobs/bilibili.yaml
lkp split $LKP_SRC/jobs/bilibili.yaml
lkp run $LKP_SRC/bilibili-1x.yaml
lkp collect -c testcase=bilibili -o /result/bilibili/bilibili_`hostname`.csv
