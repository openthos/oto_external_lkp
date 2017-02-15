package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class seafile extends UiAutomatorTestCase {
	
	public static String apppackage = "com.seafile.seadroid2";
	public static String appactivity = "com.seafile.seadroid2.ui.activity.BrowserActivity";
	public static String appName = "com.seafile.seadroid2/com.seafile.seadroid2.ui.activity.BrowserActivity";
	public static String port = "5555";
	
	public void testseafile() throws UiObjectNotFoundException, RemoteException,
			IOException, InterruptedException{
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
		sleep(3000);
		
		otoTest.ClickById("android:id/mwMaximizeBtn");
		window_lib.windowtest(otoTest.mydevice,appName );
		
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		otoTest.ClickById("com.seafile.seadroid2:id/account_footer_btn");
		sleep(1000);
		otoTest.ClickById("android:id/text1");

		otoTest.SetTextById("com.seafile.seadroid2:id/server_url", "https://dev.openthos.org/");
		otoTest.SetTextById("com.seafile.seadroid2:id/email_address", "asptest@126.com");
		otoTest.SetTextById("com.seafile.seadroid2:id/password", "abc123");
		//目前无法登录
		//otoTest.ClickById("com.seafile.seadroid2:id/login_button");
		
		// 关闭程序
		otoTest.ClickById("android:id/mwCloseBtn");
		
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		sleep(1000);
		otoTest.ClickById("android:id/mwMinimizeBtn");
		// 强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));
		
	}
}
