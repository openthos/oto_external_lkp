lkp install $LKP_SRC/jobs/YinxiangNote.yaml
lkp split $LKP_SRC/jobs/YinxiangNote.yaml
lkp run $LKP_SRC/YinxiangNote-1x.yaml
lkp collect -c testcase=YinxiangNote -o /result/YinxiangNote/YinxiangNote_`hostname`.csv
