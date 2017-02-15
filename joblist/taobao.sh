lkp install $LKP_SRC/jobs/taobao.yaml
lkp split $LKP_SRC/jobs/taobao.yaml
lkp run $LKP_SRC/taobao-1x.yaml
lkp collect -c testcase=taobao -o /result/taobao/taobao_`hostname`.csv
