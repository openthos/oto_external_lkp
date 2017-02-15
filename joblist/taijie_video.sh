lkp install $LKP_SRC/jobs/taijie_video.yaml
lkp split $LKP_SRC/jobs/taijie_video.yaml
lkp run $LKP_SRC/taijie_video-1x.yaml
lkp collect -c testcase=taijie_video -o /result/taijie_video/taijie_video_`hostname`.csv
