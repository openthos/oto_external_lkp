lkp install $LKP_SRC/jobs/cntvhd.yaml
lkp split $LKP_SRC/jobs/cntvhd.yaml
lkp run $LKP_SRC/cntvhd-1x.yaml
lkp collect -c testcase=cntvhd -o /result/cntvhd/cntvhd_`hostname`.csv
