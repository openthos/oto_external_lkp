lkp install $LKP_SRC/jobs/IThome.yaml
lkp split $LKP_SRC/jobs/IThome.yaml
lkp run $LKP_SRC/IThome-1x.yaml
lkp collect -c testcase=IThome -o /result/IThome/IThome_`hostname`.csv
