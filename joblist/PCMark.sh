lkp install $LKP_SRC/jobs/PCMark.yaml
lkp split $LKP_SRC/jobs/PCMark.yaml
lkp run $LKP_SRC/PCMark-1x.yaml
lkp collect -c testcase=PCMark -o /result/PCMark/PCMark_`hostname`.csv
