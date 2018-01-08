package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class system_ui extends UiAutomatorTestCase {

	public static String port = "5555";

	public void testsystem_ui() throws UiObjectNotFoundException, RemoteException,
			IOException, InterruptedException {
		otoDisplayRun otoTest;
		otoTest = new otoDisplayRun(getUiDevice());
		otoTest.mydevice.wakeUp();
		assertTrue("screen on :can't wakeup", otoTest.mydevice.isScreenOn());

		Date starttime;
		Date endtime;
		long launchTime;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		sleep(1000);
		
		starttime = new Date();
		System.out.println("----------Start time： " +  format.format(starttime));
		System.out.println("starttime:" +  System.currentTimeMillis());
		//启动通知中心

		sleep(1000);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ESCAPE);
		otoTest.mydevice.openNotification();
		
		endtime = new Date();
		System.out.println("----------结束时间： " +  format.format(endtime));
		System.out.println("endtime:" +  System.currentTimeMillis());

		launchTime = endtime.getTime() - starttime.getTime();
		System.out.println("----------APP launch 时间： " + launchTime +"ms");
		sleep(1000);
		
		//音量调节
		System.out.println("音量调节");
		otoTest.ClickById("com.android.systemui:id/status_bar_sound");
		otoTest.ClickById("com.android.systemui:id/media_volume_btn");
		otoTest.ClickById("com.android.systemui:id/media_volume_btn");
		sleep(1000);
		//点击清除所有,（bug2145）在通知中心有通知消息的情况下，点击通知管理，通知中心不会关闭
		otoTest.ClickById("com.android.systemui:id/clearAll");
		//点击通知管理
		System.out.println("通知管理");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		sleep(500);
		otoTest.ClickById("com.android.systemui:id/notificationManager");
		sleep(1000);
		assertEquals("notificationManager open success","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		//点击截屏
		System.out.println("截屏");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickByText("截屏");
		sleep(6000);
		assertTrue("截屏成功", new UiObject(new UiSelector().resourceId("android:id/title")).getText().equals("已抓取屏幕截图。"));
		//点击清除所有
		System.out.println("清除所有");
		otoTest.ClickById("com.android.systemui:id/clearAll");
		//点击打印任务管理器
		System.out.println("打印任务管理器");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/printManager");
		sleep(1000);
		assertEquals("printManager open success","com.github.openthos.printer.localprint",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		//隔离模式导致网络断开，暂时不测；投影按钮暂无功能
		//点击设置
		System.out.println("设置");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickByText("设置");
		assertEquals("notificationManager open success","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		//点击Startmenu时会关闭通知中心，然后焦点消失，无法点击
		//otoTest.ClickById("com.android.systemui:id/status_bar_startup_menu");
		//打开文件管理器
		System.out.println("打开文件管理器");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		UiObject act = new UiObject(new UiSelector().resourceId("com.android.systemui:id/status_bar_activity_contents"));
		UiObject act1 = act.getChild(new UiSelector().className("android.widget.FrameLayout").instance(0));
		act1.click();
		assertEquals("filemanager open success","com.openthos.filemanager",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		//打开Internet浏览器(由于appStore测试时，卸载更新导致任务栏图标消失，所以目前不进行测试）
//		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
//		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
//		otoTest.mydevice.openNotification();
//		UiObject act2 = act.getChild(new UiSelector().className("android.widget.FrameLayout").instance(1));
//		act2.click();
//		assertEquals("Internet浏览器 open success","org.mozilla.fennec_root",otoTest.mydevice.getCurrentPackageName());
//		otoTest.MoveToTop();
//		otoTest.ClickById("android:id/mwCloseBtn");
		//输入法设置
		System.out.println("输入法设置");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/status_bar_input_method");
		otoTest.ClickByText("Android 键盘 (AOSP)");
		sleep(1000);
		assertTrue("打开切换输入法界面",new UiObject(new UiSelector().resourceId("android:id/alertTitle")).getText().equals("更改键盘"));
		otoTest.ClickByText("Android 键盘 (AOSP)");
		
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/status_bar_input_method");
		UiObject input = new UiObject(new UiSelector().text("Android 键盘 (AOSP)"));
		UiObject inputcheck1 = input.getFromParent(new UiSelector().resourceId("com.android.systemui:id/input_method_checkbox"));
		assertTrue("切换输入法成功",inputcheck1.isChecked());
		
		otoTest.ClickByText("谷歌拼音输入法");
		otoTest.ClickByText("选择键盘");
		assertEquals("打开输入法设置","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/status_bar_input_method");
		otoTest.ClickByText("谷歌拼音输入法");
		sleep(1000);
		otoTest.ClickByText("谷歌拼音输入法");
		//电池
		System.out.println("电池");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/status_bar_battery");
		assertTrue("电池按钮正常",new UiObject(new UiSelector().resourceId("com.android.systemui:id/battery_time_enter")).getText().equals("设置节能模式"));
		otoTest.ClickById("com.android.systemui:id/battery_time_enter");
		assertEquals("打开节能设置","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		//网络设置
		System.out.println("网络设置");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/status_bar_wifi");
		UiObject wifibutton = new UiObject(new UiSelector().resourceId("com.android.systemui:id/wifi_enable_btn"));
		if(wifibutton.getText().equals("关闭")){            //台式机
			wifibutton.click();
			assertTrue("wifi已开启",wifibutton.getText().equals("开启"));
		}else{             //笔记本
			wifibutton.click();
			assertTrue("wifi已关闭",wifibutton.getText().equals("关闭"));
			wifibutton.click();
			sleep(1000);
			assertTrue("wifi已开启",wifibutton.getText().equals("开启"));
			sleep(1000);
			otoTest.ClickById("com.android.systemui:id/wifi_content_name");
			sleep(1000);
			assertEquals("打开WLAN设置","com.android.settings",otoTest.mydevice.getCurrentPackageName());
			otoTest.ClickById("android:id/mwCloseBtn");
			otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
			otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
			otoTest.mydevice.openNotification();
			otoTest.ClickById("com.android.systemui:id/status_bar_wifi");
		}
		//目前点击配置以太网存在bug，dialog不消失
		//otoTest.ClickById("com.android.systemui:id/ethernet_configure");
		//assertEquals("打开以太网设置","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		//otoTest.ClickById("android:id/mwCloseBtn");
		//日历
		System.out.println("日历");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/clock");
		UiObject datebtn = new UiObject(new UiSelector().resourceId("com.android.systemui:id/popupwindow_calendar_bt_enter"));
		if(datebtn.exists()){
			datebtn.click();
			sleep(1000);
		}else{
			System.out.println("打开时间日历失败");
		}
		assertEquals("设置日期与时间","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		//返回桌面按钮
		System.out.println("返回桌面按钮");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/status_bar_home");
		assertEquals("返回桌面","com.android.launcher3",otoTest.mydevice.getCurrentPackageName());
		sleep(1000);
		//键盘映射测试(与bug 873相同）通知中心打开后，点击键盘映射会关闭通知中心，此时无法识别任务栏
		System.out.println("键盘映射");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_TAB,2);
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
		otoTest.mydevice.openNotification();
		otoTest.ClickById("com.android.systemui:id/status_bar_keyboard");
		
		otoTest.ClickById("com.openthos.keyboardmap:id/add_button");
		sleep(500);
		otoTest.ClickById("com.openthos.keyboardmap:id/reset");
		sleep(500);
		otoTest.ClickById("com.openthos.keyboardmap:id/exit");
	}
}
