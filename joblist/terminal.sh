lkp install $LKP_SRC/jobs/terminal.yaml
lkp split $LKP_SRC/jobs/terminal.yaml
lkp run $LKP_SRC/terminal-1x.yaml
lkp collect -c testcase=terminal -o /result/terminal/terminal_`hostname`.csv
