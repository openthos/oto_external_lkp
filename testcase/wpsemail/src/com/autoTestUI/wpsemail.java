package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class wpsemail extends UiAutomatorTestCase {

	public static String apppackage = "com.kingsoft.email";
	public static String appactivity = "com.kingsoft.email.activity.setup.AccountSetupBasics";
	public static String appName = "com.kingsoft.email/com.kingsoft.email.activity.setup.AccountSetupBasics";
	public static String port = "5555";

	public void testwpsemail() throws UiObjectNotFoundException, RemoteException,
			IOException, InterruptedException {
		otoDisplayRun otoTest;
		otoTest = new otoDisplayRun(getUiDevice());
		otoTest.mydevice.wakeUp();
		otoTest.mydevice.pressEnter();
		otoTest.mydevice.pressKeyCode(111);
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

		window_lib.windowtest(otoTest.mydevice,appName );

		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		otoTest.SetTextById("com.kingsoft.email:id/account_email", "asptest@126.com");
		otoTest.SetTextById("com.kingsoft.email:id/account_password", "abc123");
		otoTest.ClickById("com.kingsoft.email:id/next");
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMaximizeBtn");
		otoTest.ClickById("android:id/mwMinimizeBtn");
		// 强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));

	}
}
