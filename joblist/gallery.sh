lkp install $LKP_SRC/jobs/gallery.yaml
lkp split $LKP_SRC/jobs/gallery.yaml
lkp run $LKP_SRC/gallery-1x.yaml
lkp collect -c testcase=gallery -o /result/gallery/gallery_`hostname`.csv
