lkp install $LKP_SRC/jobs/monichaogu.yaml
lkp split $LKP_SRC/jobs/monichaogu.yaml
lkp run $LKP_SRC/monichaogu-1x.yaml
lkp collect -c testcase=monichaogu -o /result/monichaogu/monichaogu_`hostname`.csv
