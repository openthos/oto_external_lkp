#!/bin/bash -x
#需要根据环境修改-t参数(现在是1)，查看5.1版本对应android stdio的id（ android list target )

android create uitest-project -n fotoFinder -t 1 -p .
ant build
