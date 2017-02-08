package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class qq extends UiAutomatorTestCase {
	
	public static String apppackage = "com.tencent.mobileqq";
	public static String appactivity = "com.tencent.mobileqq.activity.RegisterGuideActivity";
	public static String appName = "com.tencent.mobileqq/com.tencent.mobileqq.activity.RegisterGuideActivity";
	public static String port = "5555";
	
	public void testqq() throws UiObjectNotFoundException, RemoteException,
			IOException, InterruptedException {
		otoDisplayRun otoTest;
		otoTest = new otoDisplayRun(getUiDevice());
		otoTest.mydevice.wakeUp();
		assertTrue("screen on :can't wakeup", otoTest.mydevice.isScreenOn());
		//启动时间
		Date starttime;
		Date endtime;
		long launchTime;	
		int runCount = 0;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		
		starttime = new Date();
		System.out.println("----------Start time： " +  format.format(starttime));
		System.out.println("starttime:" +  System.currentTimeMillis());
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		
		String processCmd = "ps" + "|" + "grep" + apppackage;
		System.out.println("----Process judgment:" + processCmd);
		int found = 0;
		while(runCount < 10){
			if(otoDisplayRun.execCmdNoSave(processCmd) == 0){
				found = 1;
				break;
			}
			Thread.sleep(500);
			runCount++;
		}
		
		endtime = new Date();
		if(found == 0){
			System.out.println("app not running");
		}
		else{
			System.out.println("----------结束时间： " +  format.format(endtime));
			System.out.println("endtime:" +  System.currentTimeMillis());
		}
		
		launchTime = endtime.getTime() - starttime.getTime();
		System.out.println("----------APP launch 时间： " + launchTime +"ms");
		Thread.sleep(3000);
		
		//关闭温馨提示弹窗
		UiObject mySide = new UiObject(new UiSelector().resourceId("android:id/content"));
		int x = mySide.getBounds().left;
		int y = mySide.getBounds().top;
		otoTest.mydevice.click(x+100,y-100);
		Thread.sleep(3000);
		window_lib.windowtest(otoTest.mydevice, appName);
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		sleep(1000);
		// 最大化
		otoTest.ClickById("android:id/mwMaximizeBtn");
		otoTest.ClickById("android:id/mwMaximizeBtn");
		// 关闭程序
		otoTest.ClickById("android:id/mwCloseBtn");
		// 重新启动程序
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		//执行其他操作，如：登录等
		otoTest.ClickById("com.tencent.mobileqq:id/btn_login");
		
		UiObject username=new UiObject(new UiSelector().className("android.widget.EditText"));
		otoTest.ClickByClassname("android.widget.EditText");
		username.setText("3050840977");
		
		UiObject passwd=new UiObject(new UiSelector().resourceId("com.tencent.mobileqq:id/password"));
		otoTest.ClickById("com.tencent.mobileqq:id/password");
		passwd.setText("abc123");
		
		otoTest.ClickById("com.tencent.mobileqq:id/btn_login");
		//最小化
		otoTest.ClickById("android:id/mwMinimizeBtn");
		//强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));
	}
	
}