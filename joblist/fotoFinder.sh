lkp install $LKP_SRC/jobs/fotoFinder.yaml
lkp split $LKP_SRC/jobs/fotoFinder.yaml
lkp run $LKP_SRC/fotoFinder-1x.yaml
lkp collect -c testcase=fotoFinder -o /result/fotoFinder/fotoFinder_`hostname`.csv
