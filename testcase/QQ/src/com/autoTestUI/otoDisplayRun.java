package com.autoTestUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class otoDisplayRun extends UiAutomatorTestCase{
	
	UiDevice mydevice;
	otoDisplayRun(UiDevice device){
		mydevice = device;
	}

	final int CLICK_ID = 1111;
	final int CLICK_TEXT = 2222;
	final int CLICK_CLASSNAME = 3333;
	public boolean ClickById(String id) throws UiObjectNotFoundException{
		return ClickByInfo(CLICK_ID,id,null);
	}
	
	public boolean ClickByText(String text) throws UiObjectNotFoundException{
		return ClickByInfo(CLICK_TEXT,text,null);
	}
	
	public boolean SetTextByClassname(String cname,String text) throws UiObjectNotFoundException{
		return ClickByInfo(CLICK_CLASSNAME,cname,text);
	}
	
	public boolean SetTextById(String id,String text) throws UiObjectNotFoundException{
		return ClickByInfo(CLICK_ID,id,text);
	}

	private boolean ClickByInfo(int CLICK,String str,String text) throws UiObjectNotFoundException{
		UiSelector uiselector = null;
		switch(CLICK)
		{
		case CLICK_ID:
			uiselector = new UiSelector().resourceId(str);
			break;
		case CLICK_TEXT:	
			uiselector = new UiSelector().text(str);
			break;
		case CLICK_CLASSNAME:	
			uiselector = new UiSelector().className(str);
			break;
		default:
			return false;
		}
		UiObject myobject = new UiObject(uiselector);
		int i = 0;
		while(!myobject.exists() && i < 5){
			SolveProblems();
			sleep(2000);
			if(i == 4){
				TakeScreen(str.substring(str.indexOf('/')+1)+"----not find");
				System.out.println("----------[failed]:" + str + " not find !!!测试未通过");
				return false;
			}
			i++;
		}
		if(text == null){
			myobject.click();
		}
		else{
			myobject.click();
			myobject.setText(text);
		}
		return true;
	}

	private void SolveProblems(){
		sleep(1000);
		boolean dumpFirstStart = new UiObject(
				new UiSelector().text("温馨提示")).exists();
		if (dumpFirstStart == true) {
			UiObject skipButton = new UiObject(
					new UiSelector().resourceId("android:id/button1"));
			try {
				skipButton.click();
			} catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void TakeScreen(String descript){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Date time = new Date();
		String timestr = format.format(time);
		File files = new File("/storage/sdcard0/Pictures/"+timestr+"_"+descript+".jpg");
		mydevice.takeScreenshot(files);
	}
	
	public static int execCmdNoSave(String cmd) throws InterruptedException {
		int ret = 0;
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			//错误输出流,将标准错误转为标准输出（防止子进程运行阻塞）
			InputStream errorInput = p.getErrorStream();
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(
					errorInput));
			String eline = null;
			while ((eline = errorReader.readLine()) != null) {
				System.out.println(eline);
			}
			ret = p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
