lkp install $LKP_SRC/jobs/pod.yaml
lkp split $LKP_SRC/jobs/pod.yaml
lkp run $LKP_SRC/pod-1x.yaml
lkp collect -c testcase=pod -o /result/pod/pod_`hostname`.csv
