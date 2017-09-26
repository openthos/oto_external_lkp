package com.appStore;

import android.util.Log;
import android.widget.LinearLayout;
import java.io.IOException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


/**
 * Created by root on 8/16/17.
 */

public class appStoreTest1 extends UiAutomatorTestCase {
    String[] appList = {"PowerPoint","Internet 浏览器"/*,"Word","Excel","OneNote","Outlook","WPS邮箱","WPS Office","IT之家","Flash Master","Quick Picker","OtoVirtualGUI","模拟炒股","QQ","微信","搜狗输入法",
            "OS Monitor","绿色守护","泰捷视频","网易云音乐","央视影音","哔哩哔哩", "VLC","图片管理器"*/};

    String[] appList3 = {"PowerPoint","泰捷视频",/*"Word","Excel","OneNote","Outlook","WPS邮箱","WPS Office","IT之家","Flash Master","快图浏览","OtoVirtualGUI","模拟炒股","QQ","微信","搜狗输入法",
            "OS Monitor","绿色守护","泰捷视频","网易云音乐","央视影音","哔哩哔哩", "VLC","图片管理器","2048",*/"Angry Birds"};

    String[] appList2 = {"WPS邮箱","IT之家","Flash Master","Quick Picker","OtoVirtualGUI","模拟炒股","搜狗输入法","OS Monitor","绿色守护","央视影音","哔哩哔哩","Internet 浏览器","网易云音乐"};

    public void testDemo0() throws UiObjectNotFoundException,IOException, InterruptedException {
        UiDevice uiDevice =getUiDevice();
	uiDevice.pressEnter();
	uiDevice.pressKeyCode(111);
        otoDisplayRun.execCmdNoSave("am start -n com.android.settings/.Settings");
        UiScrollable  setList = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard"));
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
    }

    public void testDemo1() throws UiObjectNotFoundException,IOException, InterruptedException {

        otoDisplayRun.execCmdNoSave("am start -n com.openthos.appstore/.MainActivity");
        sleep(5000);
        UiObject mwMaximizeBtn = new UiObject(new UiSelector().resourceId("android:id/mwMaximizeBtn"));
        if (mwMaximizeBtn.exists()) {
            new UiObject(new UiSelector().resourceId("android:id/mwMaximizeBtn")).click();
        }

        //暂停下载
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_game")).click();
        //UiSelector textAll = new UiSelector().text("全部");
        //new UiObject(textAll.fromParent(new UiSelector().text("所有"))).click();
        UiSelector textApp = new UiSelector().text("Angry Birds");
        new UiObject(textApp.fromParent(new UiSelector().resourceId("com.openthos.appstore:id/app_item_install"))).click();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager")).click();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/download")).click();

        UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), "Angry Birds", true);

        btItem.getChild(new UiSelector().text("暂停")).click();

        String state = btItem.getChild(new UiSelector().resourceId("com.openthos.appstore:id/item_download_downloadState")).getText();
        Log.d("AAAAAAAAAAAAAA",state);

        sleep(10000);
        String state1 = btItem.getChild(new UiSelector().resourceId("com.openthos.appstore:id/item_download_downloadState")).getText();
        Log.d("BBBBBBBBBBBBBB",state1);

        assertEquals(state,state1);

        btItem.getChild(new UiSelector().text("继续")).click();
        installInSetting("Angry Birds");
    }

    public void testDemo2() throws UiObjectNotFoundException {
        getUiDevice();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_home")).click();
        UiSelector textApp = new UiSelector().text("泰捷视频");
        UiObject downloadApp = new UiObject(textApp.fromParent(new UiSelector().resourceId("com.openthos.appstore:id/app_item_install")));
        downloadApp.click();

        installInSetting("泰捷视频");

        /*通过点击软件和游戏页面的下载文字下载安装应用*/
        for (int i=0;i<appList.length;i++) {
            installByText(appList[i], "com.openthos.appstore:id/rb_software","com.openthos.appstore:id/app_item_install");
        }

        //installByText("2048","com.openthos.appstore:id/rb_game","com.openthos.appstore:id/app_item_install");
        //installByText("Angry Birds","com.openthos.appstore:id/rb_game","下载");


    }

    public void testDemo3() throws UiObjectNotFoundException {
        getUiDevice();
        /*通过管理界面的已安装列表卸载应用*/
        for (int i=0;i<appList3.length;i++) {
            uninstall(appList3[i],"卸载");
        }
        uninstall("Internet 浏览器","卸载更新");

        /*通过下载界面的列表移除应用*/
        for (int i=0;i<appList3.length;i++) {
            removeApp(appList3[i]);
        }
        removeApp("Internet 浏览器");
        //removeApp("2048");
    }

    public void testDemo4() throws UiObjectNotFoundException {
/*通过进入应用详情界面点击按钮下载安装应用*/
        getUiDevice();
        /*for (int i=0;i<appList.length;i++) {
            installByButton(appList[i],"com.openthos.appstore:id/rb_software","下载");
        }*/
        installByButton("Flash Master","com.openthos.appstore:id/rb_software","下载");
        //installByButton("2048","com.openthos.appstore:id/rb_game","下载");
        //installByButton("Angry Birds","com.openthos.appstore:id/rb_game","下载");
        installByButton("Internet 浏览器","com.openthos.appstore:id/rb_software","更新");

        /*通过管理更新页面的更新按钮更新应用*/
        uninstall("Internet 浏览器","卸载更新");
        removeApp("Internet 浏览器");
        UiObject label_manage = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager"));
        label_manage.click();

        UiObject label_update = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/update"));
        label_update.click();

       /*UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), "Internet 浏览器", true);

        UiObject btSwitch = btItem.getChild(new UiSelector().text("更新"));
        btSwitch.click();

        installInSetting("Internet 浏览器");*/
    }

    public void testDemo5() throws UiObjectNotFoundException{
        getUiDevice();

        //测试首页栏目
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_home")).click();
        new UiObject((new UiSelector().text("最受欢迎")).fromParent(new UiSelector().text("所有"))).click();
        new UiObject(new UiSelector().text("泰捷视频")).click();
        //测试返回按钮
        assertTrue(new UiObject(new UiSelector().text("一款视频软件")).exists());
        getUiDevice().setCompressedLayoutHeirarchy(false);
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/activity_title_back")).click();
        assertFalse(new UiObject(new UiSelector().text("最新推荐")).exists());
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/activity_title_back")).click();
        //assertTrue(new UiObject(new UiSelector().text("最新推荐")).exists());
    }

    public void testDemo6() throws UiObjectNotFoundException,IOException, InterruptedException {
        /*搜索应用并下载安装*/
        UiDevice uiDevice = getUiDevice();
        new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_home")).click();

        UiObject search= new UiObject((new UiSelector().resourceId("com.openthos.appstore:id/activity_title_search_text")));
        search.setText("flash");
	uiDevice.pressEnter();

        UiObject open = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/app_item_install"));
        open.clickAndWaitForNewWindow(20000);
	sleep(10000);
        assertTrue(new UiObject(new UiSelector().text("Install Dolphin for FREE")).exists());
        otoDisplayRun.execCmdNoSave("am force-stop com.tako.flash.master");

        UiObject label_manage = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager"));
        label_manage.click();

        //uninstall("Internet 浏览器","卸载更新");
        //removeApp("Internet 浏览器");
        uninstall("Flash Master","卸载");
        removeApp("Flash Master");


        otoDisplayRun.execCmdNoSave("am force-stop com.openthos.appstore");
    }

    /*public void testDemo100() throws UiObjectNotFoundException {
        getUiDevice();

        UiObject nametext=new UiObject(new UiSelector().text("卸载更新"));
        nametext.click();

        UiDevice.getInstance().pressEnter();
        UiDevice.getInstance().pressDPadRight();
        UiDevice.getInstance().pressEnter();
        UiDevice.getInstance().pressDPadRight();
        UiDevice.getInstance().pressEnter();
    }*/

    public void installByText(String appName,String labelName,String option) throws UiObjectNotFoundException {
        UiObject label_name = new UiObject(new UiSelector().resourceId(labelName));
        label_name.click();

        UiSelector textAll;
        if(labelName=="com.openthos.appstore:id/rb_game"){
            textAll = new UiSelector().text("益智游戏");
        }else {
            textAll = new UiSelector().text("全部");
        }
        UiObject allSoftware = new UiObject(textAll.fromParent(new UiSelector().text("所有")));
        allSoftware.click();
        UiSelector textApp = new UiSelector().text(appName);
        UiObject downloadApp = new UiObject(textApp.fromParent(new UiSelector().resourceId(option)));
        downloadApp.click();

        installInSetting(appName);
    }

    public void installByButton(String appName,String labelName,String option) throws UiObjectNotFoundException {
        UiObject label_name = new UiObject(new UiSelector().resourceId(labelName));
        label_name.click();

        UiSelector textAll;
        if(labelName=="com.openthos.appstore:id/rb_game"){
            textAll = new UiSelector().text("益智游戏");
        }else {
            textAll = new UiSelector().text("全部");
        }
        UiObject allSoftware = new UiObject(textAll.fromParent(new UiSelector().text("所有")));
        allSoftware.click();

        UiObject textApp = new UiObject(new UiSelector().text(appName));
        textApp.click();

        UiObject appDownloadButton = new UiObject(new UiSelector().text(option));
        appDownloadButton.click();

        installInSetting(appName);
    }

    public void installInSetting(String appname) throws UiObjectNotFoundException {
        /*if (appname!="快图浏览" && appname!="Quick Picker" && appname != "OtoVirtualGUI" && appname != "招商智远Pad" && appname !="影梭" && appname !="2048"&& appname !="Flash Master") {
            UiObject label_manager = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager"));
            label_manager.click();
            UiObject label_manager_download = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/download"));
            label_manager_download.click();
        }*/
        UiObject install_install = new UiObject(new UiSelector().resourceId("com.android.packageinstaller:id/ok_button"));

        while (!install_install.exists() ) {
            if (!install_install.exists()) {
                sleep(2000);
            }
        }
        while (install_install.exists()) {
                if (install_install.exists()) {
                    install_install.click();
                }
        }
        UiObject install_finish = new UiObject(new UiSelector().resourceId("com.android.packageinstaller:id/done_button"));
        while (!install_finish.exists() ) {
            if (!install_finish.exists()) {
                sleep(1000);
            }
        }
        install_finish.click();
    }

    public void uninstall(String appName,String option) throws UiObjectNotFoundException {
        UiObject label_name = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager"));
        label_name.click();

        UiObject label_name2 = new UiObject(new UiSelector().text("已安装"));
        label_name2.click();


        UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), appName, true);

            UiObject btSwitch = btItem.getChild(new UiSelector().text("卸载"));
            btSwitch.click();

        if (option == "卸载更新"){
            UiObject uninstallButton = new UiObject(new UiSelector().text("卸载更新"));
            uninstallButton.click();
            UiDevice.getInstance().pressEnter();
            UiDevice.getInstance().pressDPadRight();
            UiDevice.getInstance().pressEnter();
		sleep(2000);
            UiDevice.getInstance().pressDPadRight();
            UiDevice.getInstance().pressEnter();
            UiObject setting_close = new UiObject(new UiSelector().resourceId("android:id/mwCloseBtn"));
            setting_close.click();
        }else {
            UiSelector textStop = new UiSelector().text("强行停止");
            UiObject uninstallButton = new UiObject(textStop.fromParent(new UiSelector().text("卸载")));
            uninstallButton.click();

            UiObject setting_ok = new UiObject(new UiSelector().text("确定"));
            setting_ok.click();
        }
    }

    public void removeApp(String appName) throws UiObjectNotFoundException {
        UiObject label_name = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/rb_manager"));
        label_name.click();

        UiObject label_name2 = new UiObject(new UiSelector().resourceId("com.openthos.appstore:id/download"));
        label_name2.click();

        UiScrollable settingsList = new UiScrollable(new UiSelector().resourceId("com.openthos.appstore:id/customlistView"));
        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()), appName, true);
        UiObject btSwitch = btItem.getChild(new UiSelector().text("移除"));
        btSwitch.click();
    }
}

