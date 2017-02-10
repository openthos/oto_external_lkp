lkp install $LKP_SRC/jobs/word.yaml
lkp split $LKP_SRC/jobs/word.yaml
lkp run $LKP_SRC/word-1x.yaml
lkp collect -c testcase=word -o /result/word/word_`hostname`.csv
