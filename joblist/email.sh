lkp install $LKP_SRC/jobs/email.yaml
lkp split $LKP_SRC/jobs/email.yaml
lkp run $LKP_SRC/email-1x.yaml
lkp collect -c testcase=email -o /result/email/email_`hostname`.csv
