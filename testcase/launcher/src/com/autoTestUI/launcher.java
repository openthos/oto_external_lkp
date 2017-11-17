package com.autoTestUI;

import java.io.IOException;
import android.os.RemoteException;
import android.view.KeyEvent;
import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class launcher extends UiAutomatorTestCase {

	public void testsetting() throws UiObjectNotFoundException, RemoteException,
			IOException, InterruptedException {
		otoDisplayRun otoTest;
		otoTest = new otoDisplayRun(getUiDevice());
		otoTest.mydevice.wakeUp();
		otoTest.mydevice.pressEnter();
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ESCAPE);
		assertTrue("screen on :can't wakeup", otoTest.mydevice.isScreenOn());
	
		UiObject lch = new UiObject(new UiSelector().resourceId("com.android.launcher3:id/launcher"));
    	//新建文件夹
		lch.longClick();
		otoTest.ClickByText("新建文件夹");
		UiObject newfolder = new UiObject(new UiSelector().text("新建文件夹1"));
		assertTrue("新建文件夹",newfolder.exists());
		//新建文件
		lch.longClick();
		otoTest.ClickByText("新建文件");
		otoTest.ClickByText("TXT文本文档");
		otoTest.ClickById("com.android.launcher3:id/confirm");
		sleep(1000);
		UiObject newtxt = new UiObject(new UiSelector().text("新建文件1.txt"));
		assertTrue("新建TXT文本文档",newtxt.exists());
		
		lch.click();
		lch.longClick();
		otoTest.ClickByText("新建文件");
		otoTest.ClickByText("Word文本文档");
		otoTest.ClickById("com.android.launcher3:id/confirm");
		sleep(1000);
		UiObject newdoc = new UiObject(new UiSelector().text("新建文件1.doc"));
		assertTrue("新建Word文本文档",newdoc.exists());	
		
		lch.longClick();
		otoTest.ClickByText("新建文件");
		otoTest.ClickByText("PowerPoint幻灯片文档");
		otoTest.ClickById("com.android.launcher3:id/confirm");
		sleep(1000);
		UiObject newppt = new UiObject(new UiSelector().text("新建文件1.ppt"));
		assertTrue("新建PowerPoint幻灯片文档",newppt.exists());
		
		lch.longClick();
		otoTest.ClickByText("新建文件");
		otoTest.ClickByText("Excel表格文档");
		otoTest.ClickById("com.android.launcher3:id/confirm");
		sleep(1000);
		UiObject newxls = new UiObject(new UiSelector().text("新建文件1.xls"));
		assertTrue("新建Excel表格文档",newxls.exists());
		
		lch.click();
		sleep(500);
		lch.longClick();
		otoTest.ClickByText("新建文件");
		otoTest.ClickById("com.android.launcher3:id/cancel");
		assertFalse("取消新建文件",new UiObject(new UiSelector().text("请选择要创建的文件的格式")).exists());
		//排序
		lch.longClick();
		otoTest.ClickByText("排序");
		//更改壁纸
		lch.longClick();
		otoTest.ClickByText("更改壁纸");
		sleep(1000);
		assertEquals("FileManager open success","com.openthos.filemanager",otoTest.mydevice.getCurrentPackageName());
		UiObject wallpaper = new UiObject(new UiSelector().resourceId("com.openthos.filemanager:id/file_image"));
		long actiontime = Configurator.getInstance().getActionAcknowledgmentTimeout();
		Configurator.getInstance().setActionAcknowledgmentTimeout(0);
		wallpaper.click();
		wallpaper.click();
		sleep(1000);
		wallpaper.click();
		wallpaper.click();
		otoTest.ClickById("com.android.launcher3:id/set_wallpaper_button");
		sleep(3000);
		assertEquals("set wallpaper success","com.android.launcher3",otoTest.mydevice.getCurrentPackageName());
		Configurator.getInstance().setActionAcknowledgmentTimeout(actiontime);
		//显示设置
		lch.longClick();
		otoTest.ClickByText("显示设置");
		sleep(1000);
		assertEquals("settings open success","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickByText("亮度");
		sleep(1000);
		assertTrue("调节亮度",new UiObject(new UiSelector().resourceId("com.android.systemui:id/slider")).exists());
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ESCAPE);
		sleep(1000);
		otoTest.ClickByText("壁纸");
		sleep(500);
		otoTest.ClickByText("图库");
		sleep(500);
		otoTest.ClickByText("取消");
		sleep(500);
		otoTest.ClickByText("设置壁纸");
		sleep(500);
		otoTest.ClickById("com.android.launcher3:id/wallpaper_list");
		otoTest.ClickById("com.android.launcher3:id/set_wallpaper_button");
		sleep(500);
		otoTest.ClickById("android:id/mwLaunchBtn");
		otoTest.ClickByText("字体大小");
		assertEquals("字体大小窗口","字体大小",new UiObject(new UiSelector().resourceId("android:id/alertTitle")).getText());
		otoTest.ClickByText("超大");
		sleep(1000);
		assertEquals("验证字体大小","超大",new UiObject(new UiSelector().resourceId("android:id/summary")).getText());
		otoTest.ClickByText("字体大小");
		otoTest.ClickByText("普通");
		sleep(1000);
		assertEquals("验证字体大小","普通",new UiObject(new UiSelector().resourceId("android:id/summary")).getText());
		otoTest.ClickById("android:id/mwCloseBtn");
		//我的电脑
		//双击打开
		lch.click();
		UiObject mc = new UiObject(new UiSelector().text("我的电脑"));
		Configurator.getInstance().setActionAcknowledgmentTimeout(0);
		mc.click();	
		mc.click();
		sleep(1000);
		assertEquals("打开计算机目录","com.openthos.filemanager",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		Configurator.getInstance().setActionAcknowledgmentTimeout(actiontime);
		//右键菜单
		mc.longClick();
		otoTest.ClickByText("打开");
		sleep(1000);
		assertEquals("打开计算机目录","com.openthos.filemanager",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		mc.longClick();
		otoTest.ClickByText("关于本机");
		sleep(1000);
		assertEquals("设备信息","com.android.settings",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		//UserGuide.html
		UiObject user = new UiObject(new UiSelector().text("UserGuide.html"));
		user.longClick();
		otoTest.ClickByText("打开");
		otoTest.ClickByText("HTML 查看程序");
		sleep(500);
		otoTest.ClickById("android:id/button_once");
		sleep(500);
		otoTest.ClickById("android:id/mwCloseBtn");
		//打开方式
		user.longClick();
		otoTest.ClickByText("打开方式");
		otoTest.ClickByText("Internet 浏览器");
		sleep(1000);
		otoTest.MoveToTop();
		otoTest.ClickById("android:id/mwCloseBtn");
		//压缩UserGuide.html为zip
		user.longClick();
		otoTest.ClickByText("压缩");
		UiObject co_name = new UiObject(new UiSelector().resourceId("com.openthos.compress:id/et_co_name"));
		co_name.setText("usercomp");
		otoTest.mydevice.pressEnter();
		sleep(500);
		otoTest.ClickById("com.openthos.compress:id/bt_co_compress");
		sleep(3000);
		UiObject Uzip =new UiObject(new UiSelector().text("usercomp.zip"));
		assertTrue("压缩zip",Uzip.exists());
		//压缩新建文件夹为.7z,并设置密码123
		lch.click();
		sleep(500);
		newfolder.longClick();
		otoTest.ClickByText("压缩");
		otoTest.ClickById("com.openthos.compress:id/bt_co_destination");
		sleep(500);
		UiScrollable deslist = new UiScrollable(new UiSelector().resourceId("com.openthos.compress:id/lv_file_list"));
		UiObject comfile = deslist.getChildByText(new UiSelector().className("android.widget.TextView"), "Desktop",true);
		comfile.click();
		sleep(500);
		otoTest.ClickByText("新建文件夹1");
		sleep(500);
		otoTest.ClickById("com.openthos.compress:id/bt_bottom_ok");
		co_name.setText("foldercomp");
		otoTest.mydevice.pressEnter();
		otoTest.ClickById("com.openthos.compress:id/sp_co_type");
		otoTest.ClickByText(".7z");
		otoTest.ClickById("com.openthos.compress:id/cb_co_passwd");
		otoTest.ClickById("com.openthos.compress:id/co_passwd_visible");
		UiObject co_passwd = new UiObject(new UiSelector().resourceId("com.openthos.compress:id/et_co_passwd"));
		co_passwd.setText("123");
		otoTest.ClickById("com.openthos.compress:id/bt_co_compress");
		sleep(3000);
		//压缩新建文件
		newtxt.longClick();
		otoTest.ClickByText("压缩");
		co_name.setText("txtcomp");
		otoTest.mydevice.pressEnter();
		otoTest.ClickById("com.openthos.compress:id/sp_co_type");
		otoTest.ClickByText(".tar");
		otoTest.ClickById("com.openthos.compress:id/bt_co_compress");
		sleep(3000);
		UiObject txttar = new UiObject(new UiSelector().text("txtcomp.tar"));
		assertTrue("压缩txt",txttar.exists());
		//剪切txt
		newtxt.longClick();
		otoTest.ClickByText("剪切");
		lch.longClick();
		otoTest.ClickByText("粘贴");
		sleep(2000);
		assertTrue("剪切txt文件",newtxt.exists());
		//复制doc
		lch.click();
		newdoc.longClick();
		otoTest.ClickByText("复制");
		lch.longClick();
		otoTest.ClickByText("粘贴");
		sleep(2000);
		UiObject newdoc2 = new UiObject(new UiSelector().text("新建文件1.2.doc"));
		assertTrue("复制word文件",newdoc2.exists());
		//重命名ppt
		lch.click();
		newppt.longClick();
		sleep(500);
		otoTest.ClickByText("重命名");
		sleep(500);
		newppt.setText("ReName");
		sleep(500);
		otoTest.mydevice.pressEnter();
		sleep(2000);
		UiObject newppt2 =new UiObject(new UiSelector().text("ReName.ppt"));
		assertTrue("重命名ppt",newppt2.exists());
		//xls属性
		lch.click();
		sleep(500);
		newxls.longClick();
		sleep(500);
		otoTest.ClickByText("属性");
		sleep(1000);
		otoTest.ClickById("com.android.launcher3:id/confirm");
		//解压缩
		txttar.longClick();
		otoTest.ClickByText("解压");
		sleep(500);
		otoTest.ClickById("com.openthos.compress:id/bt_decompress");
		sleep(500);
		otoTest.ClickById("android:id/button1");
		sleep(500);
		newfolder.click();
		otoTest.mydevice.pressEnter();
		sleep(500);
		UiObject fold7z = new UiObject(new UiSelector().text("foldercomp.7z"));
		Configurator.getInstance().setActionAcknowledgmentTimeout(0);
		fold7z.click();
		fold7z.click();
		otoTest.ClickById("com.openthos.compress:id/bt_extract_all");
		otoTest.ClickById("com.openthos.compress:id/bt_decompress");
		sleep(2000);
		assertTrue("解压fold7z",newfolder.exists());
		Configurator.getInstance().setActionAcknowledgmentTimeout(actiontime);
		otoTest.ClickById("android:id/mwCloseBtn");
		sleep(1000);
		//删除
		lch.click();
		txttar.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(1000);		
		newtxt.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(1000);
		newppt2.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(1000);	
		Uzip.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(1000);
		lch.click();
		newfolder.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(1000);
		newdoc.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(1000);
		newdoc2.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(1000);
		newxls.longClick();
		otoTest.ClickByText("删除");
		otoTest.ClickById("android:id/button1");
		sleep(2000);
		//回收站
		otoTest.mydevice.pressBack();
		Configurator.getInstance().setActionAcknowledgmentTimeout(0);
		UiObject rb = new UiObject(new UiSelector().text("回收站"));
		rb.click();
		rb.click();
		sleep(1000);
		assertEquals("打开回收站","com.openthos.filemanager",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		Configurator.getInstance().setActionAcknowledgmentTimeout(actiontime);
		sleep(1000);
		//右键菜单
		rb.longClick();
		otoTest.ClickByText("清空回收站");
		assertTrue("清空回收站",new UiObject(new UiSelector().text("确认清空回收站?")).exists());
		otoTest.ClickById("android:id/button1");
		sleep(1000);
		assertFalse("是",new UiObject(new UiSelector().text("确认清空回收站?")).exists());
		rb.longClick();
		otoTest.ClickByText("打开");
		sleep(1000);
		assertEquals("打开回收站","com.openthos.filemanager",otoTest.mydevice.getCurrentPackageName());
		otoTest.ClickById("android:id/mwCloseBtn");
		otoTest.mydevice.pressKeyCode(KeyEvent.KEYCODE_ESCAPE);
		rb.longClick();
		otoTest.ClickByText("清空回收站");
		assertTrue("清空回收站",new UiObject(new UiSelector().text("确认清空回收站?")).exists());
		otoTest.ClickById("android:id/button2");
		sleep(1000);
		assertFalse("否",new UiObject(new UiSelector().text("确认清空回收站?")).exists());
	}
}
