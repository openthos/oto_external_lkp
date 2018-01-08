package com.autoTestUI;

import android.os.RemoteException;
import android.util.Log;
import android.widget.LinearLayout;
import java.io.IOException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.autoTestUI.otoDisplayRun;

/**
 * Created by qin on 17-6-19.
 */

public class appstoreTest1 extends UiAutomatorTestCase {
	int max_count = 90; //下载应用最长时间，超过会算作超时并停止下载，单位（秒）
	
	otoDisplayRun otoTest;
	
	//测试应用列表：列表的第一个为游戏用例，并且用于检测下载暂停功能，因此用例最好选择apk稍微大一些的（此应用仅下载、安装与卸载）
	//列表的第二个为首页的应用，随着首页的更新而更新（此应用仅下载、安装与卸载）
	//列表的第三个为普通应用（此应用会下载、安装、打开与卸载，由于有打开操作，建议选择打开速度快一些且没有广告的应用）
	//列表的第四个为有更新的系统应用
    String[] appList4 = {"Angry Birds","学堂在线","OS Monitor","Internet 浏览器"};

    //测试用例0：作用是准备后续测试
    public void test0preparation() throws UiObjectNotFoundException,IOException,InterruptedException,RemoteException {
		otoTest=new otoDisplayRun(getUiDevice());
		otoTest.mydevice.wakeUp();
		otoTest.mydevice.pressEnter();
		otoTest.mydevice.pressKeyCode(111);

		//关闭设置-安全中的未知来源选项
		otoDisplayRun.execCmdNoSave("am start -n com.android.settings/.Settings");
        UiScrollable setList = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard"));
        UiObject securityItem = setList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), "安全", true);
        securityItem.getChild(new UiSelector().text("安全"));
        new UiObject(new UiSelector().text("安全")).click();
        UiScrollable  securityList = new UiScrollable(new UiSelector().resourceId("android:id/list"));
        UiObject btItem = securityList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), "未知来源", true);
        UiObject btSwitch = btItem.getChild(new UiSelector().resourceId("android:id/switchWidget"));
        if(btSwitch.isChecked()==false) {
            btSwitch.click();
            new UiObject(new UiSelector().text("确定")).click();
        }
        sleep(1000);
        new UiObject(new UiSelector().resourceId("android:id/mwCloseBtn")).click();

        //启动应用商店并全屏
        otoDisplayRun.execCmdNoSave("am start -n com.openthos.appstore/.MainActivity");
        sleep(5000);
        UiObject mwMaximizeBtn = new UiObject(new UiSelector().resourceId("android:id/mwMaximizeBtn"));
        if (mwMaximizeBtn.exists()) {
            new UiObject(new UiSelector().resourceId("android:id/mwMaximizeBtn")).click();
        }

        //确认测试用例相关的几个应用都处于未安装且未下载状态，否则卸载并移除应用
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_game")).click();
        new UiObject(new UiSelector().text("游戏").fromParent(new UiSelector().text("所有"))).click();
        new UiObject(new UiSelector().text(appList4[0])).click();
        String buttonText = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/fragment_detail_download")).getText();
        if(buttonText.equals("安装")){
            removeApp(appList4[0]);
        }
        else if(buttonText.equals("打开")){
            uninstall(appList4[0],"卸载");
            removeApp(appList4[0]);
        }
        for (int i=1;i<appList4.length;i++) {
            new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_software")).click();
            new UiObject(new UiSelector().text("全部").fromParent(new UiSelector().text("所有"))).click();
            new UiObject(new UiSelector().text(appList4[i])).click();
            String buttonText1 = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/fragment_detail_download")).getText();
            if (buttonText1.equals("安装")) {
                removeApp(appList4[i]);
            } else if (buttonText1.equals("打开")) {
                if(appList4[i]=="Internet 浏览器"){
                    uninstall(appList4[i], "卸载更新");
                }else {
                    uninstall(appList4[i], "卸载");
                }
                removeApp(appList4[i]);
            }
        }
    }

    //测试用例1：测试下载暂停和继续
    public void testDemo1() throws UiObjectNotFoundException,IOException,InterruptedException {
        //测试下载暂停功能
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_game")).click();
        new UiObject(new UiSelector().text("所有")).click();
        UiSelector textApp = new UiSelector().text(appList4[0]);
        new UiObject(textApp.fromParent(new UiSelector().resourceId("com.openthos.appstore:id/app_item_install"))).click();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager")).click();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/download")).click();
        UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), appList4[0], true);

        //在管理-下载页面中点击暂停按钮，10秒后对比暂停前后的下载进度
        btItem.getChild(new UiSelector().text("暂停")).click();
        String state = btItem.getChild(new UiSelector().resourceId("com.openthos.appstore:id/item_download_downloadState")).getText();
        Log.d("AAAA",state);
        sleep(10000);
        String state1 = btItem.getChild(new UiSelector().resourceId("com.openthos.appstore:id/item_download_downloadState")).getText();
        Log.d("BBBB",state1);
        assertEquals(state,state1);

        //继续完成下载任务并安装应用1
        btItem.getChild(new UiSelector().text("继续")).click();
        installInSetting(appList4[0]);
    }

    //测试用例2：测试在应用商店首页下载安装应用2、测试在所有软件页面下载安装应用3、测试在所有软件页面更新应用4
    public void testDemo2() throws UiObjectNotFoundException,InterruptedException {
        getUiDevice();
        //测试在应用商店首页下载应用2
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_home")).click();
        UiSelector textApp = new UiSelector().text(appList4[1]);
        UiObject downloadApp = new UiObject(textApp.fromParent(new UiSelector().resourceId("com.openthos.appstore:id/app_item_install")));
        downloadApp.click();
        installInSetting(appList4[1]);

        //测试在所有软件页面下载安装应用3和更新应用4
        for (int i=2;i<appList4.length;i++) {
            installByText(appList4[i], "com.openthos.appstore:id/rb_software","com.openthos.appstore:id/app_item_install");
        }
        restartStore(); //TEMP！ 由于存在管理页面不更新的bug，故加入重启应用商店的操作
    }

    //测试用例3：测试卸载和移除应用1、2、3、4
    public void testDemo3() throws UiObjectNotFoundException,InterruptedException {
        getUiDevice();
        //卸载4个应用
        for (int i=0;i<appList4.length-1;i++) {
            uninstall(appList4[i],"卸载");
        }
        //uninstall("Internet 浏览器","卸载更新");
        unistallFennecForTemp(); //TEMP！ 由于存在fennec不显示在已安装列表的bug，故加入在设置中卸载fennec的操作
        
        //移除4个应用
        for (int i=0;i<appList4.length;i++) {
            removeApp(appList4[i]);
        }
        restartStore(); //TEMP！ 由于存在管理页面不更新的bug，故加入重启应用商店的操作
    }

    //测试用例4：测试进入应用详情页面下载安装应用3和4，测试进入管理页面在更新列表中更新应用4
    public void testDemo4() throws UiObjectNotFoundException,InterruptedException {
        getUiDevice();
    	//进入应用详情页面下载安装应用3和4
        installByButton(appList4[2],"com.openthos.appstore:id/rb_software","下载");
        installByButton(appList4[3],"com.openthos.appstore:id/rb_software","更新");
        restartStore(); //TEMP！ 由于存在管理页面不更新的bug，故加入重启应用商店的操作
        
        //卸载并移除应用4
        //uninstall(appList4[3],"卸载更新");
        unistallFennecForTemp(); //TEMP！ 由于存在fennec不显示在已安装列表的bug，故加入在设置中卸载fennec的操作
        removeApp(appList4[3]);
        restartStore(); //TEMP！ 由于存在管理页面不更新的bug，故加入重启应用商店的操作
        
        //进入管理页面在更新列表中更新应用4
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager")).click();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/update")).click();
        UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), appList4[3], true);
        btItem.getChild(new UiSelector().text("更新")).click();
        installInSetting(appList4[3]);
        restartStore(); //TEMP！ 由于存在管理页面不更新的bug，故加入重启应用商店的操作
    }

	//测试用例5：测试首页栏目，测试页面返回按钮
    public void testDemo5() throws UiObjectNotFoundException,InterruptedException{
        getUiDevice();
        //测试首页栏目能否进入特定应用
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_home")).click();
        new UiObject((new UiSelector().text("最受欢迎")).fromParent(new UiSelector().text("所有"))).click();
        new UiObject(new UiSelector().text("微博")).click();
        assertTrue(new UiObject(new UiSelector().text("一款社交软件")).exists());
        
        //测试页面返回按钮
        getUiDevice().setCompressedLayoutHeirarchy(false);
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/activity_title_back")).click();
        assertFalse(new UiObject(new UiSelector().text("最新推荐")).exists());
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/activity_title_back")).click();
        assertTrue(new UiObject(new UiSelector().text("最新推荐")).exists());
    }
    
	//测试用例6：测试右上角搜索应用，测试打开应用，卸载测试相关应用并关闭应用商店
    public void testDemo6() throws UiObjectNotFoundException,IOException,InterruptedException {
        UiDevice uiDevice = getUiDevice();
        //测试右上角搜索应用
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_home")).click();
        new UiObject((new UiSelector().resourceId("com.openthos.appstore:id/activity_title_search_text"))).setText("monitor");
        uiDevice.pressEnter();
        uiDevice.pressEnter();

        //测试打开应用
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/app_item_install")).clickAndWaitForNewWindow(10000);
        assertTrue(new UiObject(new UiSelector().text("进程")).exists());
        otoDisplayRun.execCmdNoSave("am force-stop com.eolwral.osmonitor");
        
        //卸载测试相关应用
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager")).click();
        uninstall(appList4[2],"卸载");
        removeApp(appList4[2]);
        unistallFennecForTemp(); //TEMP！ 由于存在fennec不显示在已安装列表的bug，故加入在设置中卸载fennec的操作
        //uninstall(appList4[3],"卸载更新");
        removeApp(appList4[3]);
        
        //关闭应用商店
        otoDisplayRun.execCmdNoSave("am force-stop com.openthos.appstore");
    }
    /*测试结束*/
    
    //进入应用列表页面下载安装应用
    public void installByText(String appName,String labelName,String option) throws UiObjectNotFoundException, InterruptedException {
        new UiObject(new UiSelector().resourceId(labelName)).click();
        UiSelector textAll;
        if(labelName=="com.openthos.appstore:id/rb_game"){
            textAll = new UiSelector().text("游戏");
        }else {
            textAll = new UiSelector().text("全部");
        }
        new UiObject(textAll.fromParent(new UiSelector().text("所有"))).click();
        sleep(2000); 
        otoDisplayRun.execCmdNoSave("input swipe 500 700 500 200 2000"); //翻到列表最下面，因为测试的应用都靠后
        UiSelector textApp = new UiSelector().text(appName);
        new UiObject(textApp.fromParent(new UiSelector().resourceId(option))).click();
        installInSetting(appName);
    }

    //进入应用详情页面下载安装应用
    public void installByButton(String appName,String labelName,String option) throws UiObjectNotFoundException {
        new UiObject(new UiSelector().resourceId(labelName)).click();
        UiSelector textAll;
        if(labelName=="com.openthos.appstore:id/rb_game"){
            textAll = new UiSelector().text("游戏");
        }else {
            textAll = new UiSelector().text("全部");
        }
        new UiObject(textAll.fromParent(new UiSelector().text("所有"))).click();
        new UiObject(new UiSelector().text(appName)).click();
        new UiObject(new UiSelector().text(option)).click();
        installInSetting(appName);
    }

    //等待安装窗口弹出并安装应用
    public void installInSetting(String appname) throws UiObjectNotFoundException {
    	//等待安装窗口弹出，每隔两秒检测一次安装窗口的ok_button
        UiObject install_install = new UiObject(new UiSelector().resourceId("com.android.packageinstaller:id/ok_button"));
        int count=0;
        while (!install_install.exists()) {
            if (!install_install.exists()) {
                sleep(2000);
                count++;
            }
            if(count>max_count){
                assertTrue(false);
            }
        }
        count=0;
        
        //安装应用，一直点ok_button直到安装完成，最后点完成按钮
        while (install_install.exists()) {
                if (install_install.exists()) {
                    install_install.click();
                }
            if(count>max_count){
                assertTrue(false);
            }
        }
        count=0;
        UiObject install_finish = new UiObject(new UiSelector().resourceId("com.android.packageinstaller:id/done_button"));
        while (!install_finish.exists() ) {
            if (!install_finish.exists()) {
                sleep(1000);
            }
            if(count>max_count){
                assertTrue(false);
            }
        }
        install_finish.click();
    }

    //进入已安装页面并卸载app
    public void uninstall(String appName,String option) throws UiObjectNotFoundException {
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager")).click();
        new UiObject(new UiSelector().text("已安装")).click();
        UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), appName, true);
        btItem.getChild(new UiSelector().text("卸载")).click();
        //系统类应用卸载更新时的特殊处理
        if (option == "卸载更新"){
            new UiObject(new UiSelector().text("卸载更新")).click();
            sleep(1000);
            //TEMP！ 方向键选中对话框的确认按钮，以应对鼠标点击按钮中间会退出窗口的bug
            UiDevice.getInstance().pressEnter();
            UiDevice.getInstance().pressDPadRight();
            UiDevice.getInstance().pressEnter();
            sleep(2000);
            UiDevice.getInstance().pressDPadRight();
            UiDevice.getInstance().pressEnter();
            sleep(2000);
            new UiObject(new UiSelector().resourceId("android:id/mwCloseBtn")).click();
        }
        //普通应用卸载
        else {
            UiSelector textStop = new UiSelector().text("强行停止");
            new UiObject(textStop.fromParent(new UiSelector().text("卸载"))).click();
            new UiObject(new UiSelector().text("确定")).click();
        }
    }

    //进入下载页面并移除app
    public void removeApp(String appName) throws UiObjectNotFoundException {
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager")).click();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/download")).click();
        UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), appName, true);
        btItem.getChild(new UiSelector().text("移除")).click();
    }
    
    //TEMP！ 设置中卸载fennec（以应对fennec不显示在已安装列表的bug）
	public void unistallFennecForTemp() throws UiObjectNotFoundException, InterruptedException  {
		new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager")).click();
		new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/update")).click();
		UiObject fennec = new UiObject(new UiSelector().text("Internet 浏览器"));
		if (! fennec.exists()){
			otoDisplayRun.execCmdNoSave("am start -n com.android.settings/.Settings");
			sleep(5000);
        		new UiObject(new UiSelector().text("应用")).click();
        		new UiObject(new UiSelector().text("Internet 浏览器")).click();
        		new UiObject(new UiSelector().text("卸载更新")).click();
            		sleep(1000);
            		UiDevice.getInstance().pressEnter();
            		UiDevice.getInstance().pressDPadRight();
            		UiDevice.getInstance().pressEnter();
            		sleep(2000);
            		UiDevice.getInstance().pressDPadRight();
            		UiDevice.getInstance().pressEnter();
            		sleep(2000);
            		new UiObject(new UiSelector().resourceId("android:id/mwCloseBtn")).click();
		}
	}
	
	//TEMP！ 重启应用商店（以应对目前管理页面不更新的bug）
	public void restartStore() throws UiObjectNotFoundException, InterruptedException {
		otoTest=new otoDisplayRun(getUiDevice());
        otoTest.MoveToTop();
        sleep(1000);
        new UiObject(new UiSelector().resourceId("android:id/mwCloseBtn")).click();
        sleep(2000);
        otoDisplayRun.execCmdNoSave("am start -n com.openthos.appstore/.MainActivity");
	}
}
