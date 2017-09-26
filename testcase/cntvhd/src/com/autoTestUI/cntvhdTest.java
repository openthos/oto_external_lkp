package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class cntvhdTest extends UiAutomatorTestCase {

	public static String apppackage = "cn.cntvhd";
	public static String appactivity = "wd.android.app.ui.activity.MainActivity";
	public static String appName = "cn.cntvhd/.StartActivity";
	public static String port = "5555";

	public void testtaijievideo() throws UiObjectNotFoundException, RemoteException,
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
		sleep(6000);

		otoTest.ClickById("cn.cntvhd:id/guid_jump");
		sleep(2000);
		otoTest.ClickById("cn.cntvhd:id/right_button");
		sleep(2000);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMaximizeBtn");
		window_lib.windowtest(otoTest.mydevice,appName );

		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		sleep(6000);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMinimizeBtn");
		// 强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));
	}
}
