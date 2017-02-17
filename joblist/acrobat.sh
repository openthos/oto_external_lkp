lkp install $LKP_SRC/jobs/acrobat.yaml
lkp split $LKP_SRC/jobs/acrobat.yaml
lkp run $LKP_SRC/acrobat-1x.yaml
lkp collect -c testcase=acrobat -o /result/acrobat/acrobat_`hostname`.csv
