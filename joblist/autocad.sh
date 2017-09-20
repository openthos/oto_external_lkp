lkp install $LKP_SRC/jobs/autocad.yaml
lkp split $LKP_SRC/jobs/autocad.yaml
lkp run $LKP_SRC/autocad-1x.yaml
lkp collect -c testcase=autocad -o /result/autocad/autocad_`hostname`.csv
