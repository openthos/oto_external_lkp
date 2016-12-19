#!/bin/bash -x
cd "$(dirname "$0")" 
./fortest.sh 2>&1 | tee ./test_log.txt
