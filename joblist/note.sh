lkp install $LKP_SRC/jobs/note.yaml
lkp split $LKP_SRC/jobs/note.yaml
lkp run $LKP_SRC/note-1x.yaml
lkp collect -c testcase=note -o /result/note/note_`hostname`.csv
