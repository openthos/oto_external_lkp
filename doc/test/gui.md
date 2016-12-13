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
