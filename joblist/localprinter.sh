lkp install $LKP_SRC/jobs/localprinter.yaml
lkp split $LKP_SRC/jobs/localprinter.yaml
lkp run $LKP_SRC/localprinter-1x.yaml
lkp collect -c testcase=localprinter -o /result/localprinter/localprinter_`hostname`.csv
