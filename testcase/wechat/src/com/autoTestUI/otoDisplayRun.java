package com.autoTestUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class otoDisplayRun {
	
	public static String apppackage;
	public static String appactivity;
	public static String appName;
	public static String port;

	/**
	 * 执行cmd命令，等待进程结束
	 * @throws InterruptedException 
	 */
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
				System.out.println("<ERROR>" + eline);
			}

			ret = p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
