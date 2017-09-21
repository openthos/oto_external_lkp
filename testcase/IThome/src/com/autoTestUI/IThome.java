package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class IThome extends UiAutomatorTestCase {

	public static String apppackage = "com.ruanmei.ithome";
	public static String appactivity = "com.ruanmei.ithome.ui.MainActivity";
	public static String appName = "com.ruanmei.ithome/com.ruanmei.ithome.ui.MainActivity";
	public static String port = "5555";

	public void testIThome() throws UiObjectNotFoundException, RemoteException,
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
		sleep(4000);

		otoTest.ClickById("com.ruanmei.ithome:id/btn_dialog_upgrade_ignore");
		sleep(1000);
		window_lib.windowtest(otoTest.mydevice,appName );

		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.ClickById("android:id/mwMinimizeBtn");
		// 强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));
	}
}
