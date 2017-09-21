lkp install $LKP_SRC/jobs/xuetang.yaml
lkp split $LKP_SRC/jobs/xuetang.yaml
lkp run $LKP_SRC/xuetang-1x.yaml
lkp collect -c testcase=xuetang -o /result/xuetang/xuetang_`hostname`.csv
