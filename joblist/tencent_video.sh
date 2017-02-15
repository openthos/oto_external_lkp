lkp install $LKP_SRC/jobs/tencent_video.yaml
lkp split $LKP_SRC/jobs/tencent_video.yaml
lkp run $LKP_SRC/tencent_video-1x.yaml
lkp collect -c testcase=tencent_video -o /result/tencent_video/tencent_video_`hostname`.csv
