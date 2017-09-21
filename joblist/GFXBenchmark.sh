lkp install $LKP_SRC/jobs/GFXBenchmark.yaml
lkp split $LKP_SRC/jobs/GFXBenchmark.yaml
lkp run $LKP_SRC/GFXBenchmark-1x.yaml
lkp collect -c testcase=GFXBenchmark -o /result/GFXBenchmark/GFXBenchmark_`hostname`.csv
