#!/bin/bash -x
#需要根据环境修改-t参数(现在是1)，查看5.1版本对应android stdio的id（ android list target )

testcase=$1
android create uitest-project -n $testcase -t 1 -p .
ant build
