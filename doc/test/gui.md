# 自动测试-GUI的自动测试用例（张琳苹）

##使用uiautomator进行UI测试
  - 基于java，一次编译可以运行于所有设备
  - 接口丰富，覆盖所有android操作

##uiautomatorviewer分析UI控件的属性
  - 在sdk/tools下启动uiautomatorviewer
  - 获取运行截图
  - 在截图中选择控件，右侧可以看到相对应的属性
 
##新建测试用例
  - 用Eclipse创建Java Project
  - 添加JUnit库
  - 添加android-sdk-platforms下对应android版本的android.jar和uiautomator.jar
  - 在src中添加包和class文件，开始编写测试用例

##所有应用测试用例
  - 源码位置：https://github.com/openthos/oto_Uitest/tree/master/Auto-test/uiautomator
    - 重命名[env.java.sample](https://github.com/openthos/oto_Uitest/blob/master/Auto-test/uiautomator/src/com/autoTestUI/env.java.sample)文件为env.java，根据环境修改其中的androidTargetId（`android list target`）选择5.1的版本，以及修改
    - [AutoTest.java](https://github.com/openthos/oto_Uitest/blob/master/Auto-test/uiautomator/src/com/autoTestUI/AutoTest.java)为程序的入口
    - [otoDisplayRun.java](https://github.com/openthos/oto_Uitest/blob/master/Auto-test/uiautomator/src/com/autoTestUI/otoDisplayRun.java)包含测试用例从编译、运行到结果输出的整个过程
    - [window_lib.java](https://github.com/openthos/oto_Uitest/blob/master/Auto-test/uiautomator/src/com/autoTestUI/window_lib.java)为每个应用将进行的测试操作，比如窗口的放大、缩小以及拖动等
    - 应用.java是针对每个应用进行的测试操作，测试各个应用不同的功能
  - 可以通过注释env.java文件中的以下代码进行选择性测试
  
  `static String[][] testClassFuncName = 
  { {"com.autoTestUI.calc", "testcalc"}, { "com.autoTestUI.clock", "testclock" }, { "com.autoTestUI.date", "testdate" }}`
  
  - 测试脚本
    - 在Eclipse中导出Runnable Jar包，命名为autotest
    - 将autotest.jar放在测试目录下，otoAutoTest.jar为Eclipse运行测试用例时自动生成的jar包，也需要放在测试目录下
    ```
    #!/bin/bash -x
    androidIP=$1
    port=$2
    foldName=$3
    cd "$(dirname "$0")"
    ./install_apk.sh $androidIP $port
    mkdir $foldName
    java -jar autotest.jar $androidIP $port otoAutoTest.jar $foldName/testResult
    ```
  - 测试会生成testResult和apkresult两个文件
    - testResult文件中为测试用例的原始输出
    - apkresult文件中为筛选之后的结果，例如：calc.result:1(1表示测试通过，0表示测试失败)

##单独应用测试用例
  - 源码位置：https://github.com/openthos/oto_Uitest
    - 包含44个测试用例，基于上述总测试用例进行了拆分，可以单独针对某一个应用进行测试
    - 在每一个测试用例中，包含测试源码、应用安装包、测试脚本
  - 测试脚本
    - 主脚本以测试应用命名，例如[2048.sh](https://github.com/openthos/oto_Uitest/blob/master/2048/2048.sh)
    - [install_apk.sh](https://github.com/openthos/oto_Uitest/blob/master/2048/install_apk.sh)为安装应用的脚本
    - [TmpTojson.py](https://github.com/openthos/oto_Uitest/blob/master/2048/TmpTojson.py)将中间文件处理成json文件
    - 脚本实现自动打包生成testuiauto.jar包进行测试
    ```
    jar cvfm ../testuiauto.jar ../../MANIFEST.MF -C ../../libs ./ ./
    ```
  - 测试结果
    - 在$foldName目录下生成原始结果testResult和中间结果tmpResultToJson
    ```
    java -jar testuiauto.jar $androidIP $port otoAutoTest.jar $foldName
    ```
    - 将中间结果处理生成testResult.json文件
    ```
    python TmpTojson.py $foldName/tmpResultToJson $foldName
    ```

##基于LKP框架的测试用例(以微信为例)
  - 源码位置：https://github.com/openthos/oto_lkp/tree/master/testcase/wechat
    - 将编译过程从上述测试用例中拆分出来
    - 将原始结果的保存和后续处理剔除，按照LKP框架添加相关文件
  - 编译源码
    - [wechatMake.sh](https://github.com/openthos/oto_lkp/blob/master/testcase/wechat/wechatMake.sh)将源码编译为jar包
  - 处理原始结果
    - [wechat](https://github.com/openthos/oto_lkp/blob/master/lkp-tests-master/stats/wechat)将原始结果处理为wechat.result:1的形式
  - 可参考[LKP官方需要添加的文件（以下均以wechat为例）](https://github.com/openthos/oto_lkp/blob/master/testcase/AddTestcase.md)
