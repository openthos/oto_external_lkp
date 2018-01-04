package com.autoTestUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class calc extends UiAutomatorTestCase {

	public static String apppackage = "com.android.calculator2";
	public static String appactivity = "com.android.calculator2.Calculator";
	public static String appName = "com.android.calculator2/com.android.calculator2.Calculator";
	public static String port = "5555";

	public void testcalc() throws UiObjectNotFoundException, RemoteException,
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

		window_lib.windowtest(otoTest.mydevice,appName );
		// 测试(1 + 1) x 5 x 5 =  50
		otoDisplayRun.execCmdNoSave("am start -n " + appName);

		otoTest.ClickById("com.android.calculator2:id/lparen");
		otoTest.ClickById("com.android.calculator2:id/digit_1");
		otoTest.ClickById("com.android.calculator2:id/op_add");
		otoTest.ClickById("com.android.calculator2:id/digit_1");
		otoTest.ClickById("com.android.calculator2:id/rparen");
		otoTest.ClickById("com.android.calculator2:id/op_mul");
		otoTest.ClickById("com.android.calculator2:id/digit_5");
		otoTest.ClickById("com.android.calculator2:id/op_mul");
		otoTest.ClickById("com.android.calculator2:id/digit_5");

		UiObject resultWindow = new UiObject(
				new UiSelector().resourceId("com.android.calculator2:id/result"));
		String result = resultWindow.getText();
		if (result.compareTo("50") == 0) {
			System.out.println("testing calc pass!!!!" + result);
		} else {
			System.out.println("testing calc fail!!!!" + result);
		}
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwMaximizeBtn");
		sleep(1000);
		otoTest.ClickById("android:id/mwCloseBtn");
		otoDisplayRun.execCmdNoSave("am start -n " + appName);
		otoTest.ClickById("android:id/mwMinimizeBtn");
		// 强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));

	}

}
