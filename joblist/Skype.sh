lkp install $LKP_SRC/jobs/Skype.yaml
lkp split $LKP_SRC/jobs/Skype.yaml
lkp run $LKP_SRC/Skype-1x.yaml
lkp collect -c testcase=Skype -o /result/Skype/Skype_`hostname`.csv
