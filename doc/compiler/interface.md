# 测试用例接口规范
目前openthos的所有测试用例大致分成LKP、GUI和CTS这3类。
1. 测试用例的二进制代码和测试脚本目录： https://github.com/openthos/oto_Uitest 其中每个目录对应一个测试用例，目录中有一个与目录同名的sh文件，这是测试脚本。测试框架通过调用测试脚本来执行测试用例。
1. 测试用例的源代码目录：https://github.com/openthos/oto_lkp/tree/master/testcase 其中每个上当对应一个测试用例的源代码。
1. 接口规范：测试测试脚本编写需要符合“[测试用例接口规范](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/kernelci-analysis/testcasereadme.md)”，以方便统一测试框架中对测试用例的调用执行和测试结果收集。
