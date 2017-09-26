package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class meitu extends UiAutomatorTestCase {

	public static String apppackage = "com.mt.mtxx.mtxx";
	public static String appactivity = "com.mt.mtxx.mtxx.TopViewActivity";
	public static String appName = "com.mt.mtxx.mtxx/com.mt.mtxx.mtxx.TopViewActivity";
	public static String port = "5555";

	public void testmeitu() throws UiObjectNotFoundException, RemoteException,
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
		sleep(5000);

		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMaximizeBtn");
		window_lib.windowtest(otoTest.mydevice,appName );

		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		sleep(3000);
		// 最大化
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.ClickById("android:id/mwMaximizeBtn");
		// 关闭程序
		sleep(1000);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwCloseBtn");

		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMinimizeBtn");
		// 强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));

	}
}
