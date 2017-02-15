lkp install $LKP_SRC/jobs/onenote.yaml
lkp split $LKP_SRC/jobs/onenote.yaml
lkp run $LKP_SRC/onenote-1x.yaml
lkp collect -c testcase=onenote -o /result/onenote/onenote_`hostname`.csv
