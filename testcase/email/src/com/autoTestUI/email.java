package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class email extends UiAutomatorTestCase {
	
	public static String apppackage = "com.android.email";
	public static String appactivity = "com.android.email.activity.Welcome";
	public static String appName = "com.android.email/com.android.email.activity.Welcome";
	public static String port = "5555";
	
	static String objStr[][] = {
		{"Personal (POP3)", "个人(POP3)"},
		{"Next", "下一步"}
	};
	
	public void testemail() throws UiObjectNotFoundException, RemoteException,
			IOException, InterruptedException {
		otoDisplayRun otoTest;
		otoTest = new otoDisplayRun(getUiDevice());
		otoTest.mydevice.wakeUp();
		assertTrue("screen on :can't wakeup", otoTest.mydevice.isScreenOn());
		//启动时间
		Date starttime;
		Date endtime;
		long launchTime;	
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		
		starttime = new Date();
		System.out.println("----------Start time： " +  format.format(starttime));
		System.out.println("starttime:" +  System.currentTimeMillis());
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		
		endtime = new Date();
		System.out.println("----------结束时间： " +  format.format(endtime));
		System.out.println("endtime:" +  System.currentTimeMillis());
		
		launchTime = endtime.getTime() - starttime.getTime();
		System.out.println("----------APP launch 时间： " + launchTime +"ms");
		sleep(2000);

		window_lib.windowtest(otoTest.mydevice,appName);
		
		otoDisplayRun.execCmdNoSave(" am start -n " + appName);

		//获取语言
		UiObject title = new UiObject(new UiSelector().resourceId("android:id/mwTitle"));
		
		int language = 0;//0: en  1:cn
		
		String langStr = title.getText();
		System.out.println(langStr);
		if (langStr.equals("Email")) {
			language = 0;
		} else {
			language = 1;
		}
		System.out.println(langStr + language);
		
		// input  email accout
		otoTest.SetTextByClassname("android.widget.EditText", "asptest@126.com");
		sleep(500);
		otoTest.ClickById("com.android.email:id/next");
		sleep(500);
		UiObject button2 = new UiObject(new UiSelector().text(objStr[0][language]));
		button2.click();
		sleep(500);
		//input passwd and click Next
		otoTest.SetTextById("com.android.email:id/setup_fragment_content", "abc123");
		sleep(500);
		UiObject button3 = new UiObject(new UiSelector().text(objStr[1][language]));
		button3.click();
		sleep(500);
		
		//modify pop server
		UiObject popserver = new UiObject(new UiSelector().text("126.com"));
		//if (popserver.exists() && popserver.isEnabled())
		//{
		//String text = popserver.getText();
		sleep(500);
		popserver.longClick();
		sleep(500);
		popserver.setText("pop.126.com");
	//	}
		sleep(1000);
		otoTest.ClickById("com.android.email:id/next");
		sleep(5000);
		//modify smtp server
		UiObject smtpserver = new UiObject(new UiSelector().resourceId("com.android.email:id/account_server"));
		if (smtpserver.exists() && smtpserver.isEnabled())
		{
			popserver.longClick();
			sleep(500);
			smtpserver.setText("smtp.126.com");
			sleep(500);
		}
		UiObject smtpport = new UiObject(new UiSelector().resourceId("com.android.email:id/account_port"));
		smtpport.longClick();
		smtpport.setText("25");
		sleep(500);
		UiObject button5 = new UiObject(new UiSelector().text(objStr[1][language]));
		button5.click();
		sleep(1000);
		
		//click Next to well done
		UiObject button6 = new UiObject(new UiSelector().text(objStr[1][language]));
		button6.click();
		
		sleep(1000);
		UiObject button7 = new UiObject(new UiSelector().text(objStr[1][language]));
		button7.click();
		
		otoTest.ClickById("android:id/mwCloseBtn");
		sleep(1000);
		
		otoDisplayRun.execCmdNoSave(" am start -n " + appName);
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.ClickById("android:id/mwMinimizeBtn");
		// 强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));
	}
}
