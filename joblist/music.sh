lkp install $LKP_SRC/jobs/music.yaml
lkp split $LKP_SRC/jobs/music.yaml
lkp run $LKP_SRC/music-1x.yaml
lkp collect -c testcase=music -o /result/music/music_`hostname`.csv
