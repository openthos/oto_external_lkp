# 测试结果分析-JSON结果抽取（张琳苹）

##GUI测试结果抽取
  
  - 在执行测试用例时，自动生成tmpResultToJson文件
    - 该文件与testResult原始结果同时生成，可查看[otoDisplayRun.java](https://github.com/openthos/oto_Uitest/blob/master/calc/src/com/autoTestUI/otoDisplayRun.java)
    - 格式为用例名称.元素名称:结果
    - 例如：
    ```
    testcalc.starttime:2016-12-07 22:11:585555
    testcalc.endtime:2016-12-07 22:11:58
    testcalc.launchtime:279
    testcalc.runtime:35.762
    testcalc.result:1
    testcalc.url:testResult
    ```
  
  - 在测试脚本[calc.sh](https://github.com/openthos/oto_Uitest/blob/master/calc/calc.sh)中执行[TmpTojson.py](https://github.com/openthos/oto_Uitest/blob/master/calc/TmpTojson.py)生成json结果文件
  ```
  python TmpTojson.py $foldName/tmpResultToJson $foldName
  ```

##CTS测试结果抽取
  
  - [ctsResultTojson.py](https://github.com/openthos/testing-analysis/blob/master/auto-testing-script/cts-autotest/ctsResultTojson.py)将CTS测试生成的xml文件生成JSON
  
  ```
  python ctsResultTojson.py 参数1(xml文件) 参数2(结果存放目录)
  ```
  - 命名方式为包名#测试用例名称.元素名称：结果
  - 部分结果如下：
  
  ```
  "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.result": 0, 
 "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.starttime": "Wed Dec 07 22:59:06 CST 2016", 
 "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.endtime": "Wed Dec 07 22:59:06 CST 2016", 
 "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.errorMessage": "junit.framework.AssertionFailedError: New hash should be faster\r\nat junit.framework.Assert.fail(Assert.java:50)\r",
 ```
 
##LKP测试结果抽取
  
  - stats中添加文件，将benchmark的原始输出，转变为key:value中间结果
  - 例如：[wechat](https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/stats/wechat)
    - 生成key:value格式的中间结果后，LKP会自动转为json格式的文件
    - 由于value必须为数字格式，所以经过筛选结果中只包含launchtime、runtime、result这三种元素
