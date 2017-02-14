lkp install $LKP_SRC/jobs/wpsemail.yaml
lkp split $LKP_SRC/jobs/wpsemail.yaml
lkp run $LKP_SRC/wpsemail-1x.yaml
lkp collect -c testcase=wpsemail -o /result/wpsemail/wpsemail_`hostname`.csv
