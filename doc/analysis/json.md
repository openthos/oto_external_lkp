# 测试结果分析-JSON结果抽取（张琳苹）

##GUI测试结果抽取
  
  - 由原始输出获得中间结果文件，形式符合LKP规范
    - 用例名称.元素名称:结果
    - 例如：
    ```
    testcalc.starttime:2016-12-07 22:11:585555
    testcalc.endtime:2016-12-07 22:11:58
    testcalc.launchtime:279
    testcalc.runtime:35.762
    testcalc.result:1
    testcalc.url:testResult
    ```
  
  - 脚本中执行
  ```
  python resultTotmp.py $foldName/testResult > $foldName/tmpResultToJson
  ```
  
  - 由中间文件生成JSON文件
  ```
  python TmpTojson.py $foldName/tmpResultToJson $foldName
  ```

##CTS测试结果抽取
  
  - 根据CTS测试生成的xml文件生成JSON
  
  ```
  python ctsResultTojson.py 参数1(xml文件) 参数2(结果存放目录)
  ```
  - 例如
  
  ```
  "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.result": 0, 
 "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.starttime": "Wed Dec 07 22:59:06 CST 2016", 
 "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.endtime": "Wed Dec 07 22:59:06 CST 2016", 
 "x86.android.core.tests.libcore.package.com#com.android.org.bouncycastle.crypto.digests.DigestTest.testSHA256.errorMessage": "junit.framework.AssertionFailedError: New hash should be faster\r\nat junit.framework.Assert.fail(Assert.java:50)\r",
 ```
 命名方式为包名#测试用例名称.元素名称：结果
 
##LKP测试结果抽取

    - stats中添加文件，输出中间格式文件
