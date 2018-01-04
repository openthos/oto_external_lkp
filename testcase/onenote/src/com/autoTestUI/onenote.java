package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class onenote extends UiAutomatorTestCase {

	public static String apppackage = "com.microsoft.office.onenote";
	public static String appactivity = "com.microsoft.office.onenote.ui.ONMSplashActivity";
	public static String appName = "com.microsoft.office.onenote/com.microsoft.office.onenote.ui.ONMSplashActivity";
	public static String port = "5555";

	public void testmicrosoft_onenote() throws UiObjectNotFoundException, RemoteException,
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
		sleep(9000);

		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(500);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMaximizeBtn");
		otoTest.ClickById("android:id/mwCloseBtn");
		
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		sleep(3000);
		otoTest.ClickById("com.microsoft.office.onenote:id/negativeButton");
		window_lib.windowtest(otoTest.mydevice,appName);
	}
}
