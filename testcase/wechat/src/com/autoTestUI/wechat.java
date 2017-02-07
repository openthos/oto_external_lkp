package com.autoTestUI;

import java.io.IOException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class wechat extends UiAutomatorTestCase {
	
	public static String apppackage = "com.tencent.mm";
	public static String appactivity = "com.tencent.mm.ui.LauncherUI";
	public static String appName = "com.tencent.mm/com.tencent.mm.ui.LauncherUI";
	public static String port = "5555";
	
	public void testwechat() throws UiObjectNotFoundException, RemoteException,
			IOException, InterruptedException {
		otoDisplayRun otoTest;
		otoTest = new otoDisplayRun(getUiDevice());
		otoTest.mydevice.wakeUp();
		assertTrue("screen on :can't wakeup", otoTest.mydevice.isScreenOn());

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
		//最小化
		otoTest.ClickById("android:id/mwMinimizeBtn");
		//强制关闭程序
		otoDisplayRun.execCmdNoSave("am force-stop " + appName.substring(0, appName.indexOf("/")));
	}
	
}
