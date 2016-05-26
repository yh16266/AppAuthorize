package com.jf.haozi.appauthorize.common.utils;

import android.content.Context;
import android.os.Environment;

import com.jf.haozi.appauthorize.common.base.BaseObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil extends BaseObject {

	private static String PROJECT_DIR;

	/**
	 * 检查存储卡状态
	 * @param isCreateDirs 是否需要创建文件路径
	 * @return 存储卡是否正常
	 */
	public static boolean checkSDCard(Context mContext,boolean isCreateDirs) {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
			if (isCreateDirs) {
				initPackgeDir(mContext);
			}
			return true;
		}
		return false;
	}

	/**
	 * 创建文件路径
	 */
	public synchronized static void initPackgeDir(Context mContext) {
		/**系统级app缓存目录:存储于SD卡中程序包名下(此路径依然为手机内存卡0的路劲)*/
		String cachePath = Environment.getExternalStorageDirectory().getPath() + File.separator + mContext.getPackageName();
		PROJECT_DIR = cachePath;// + File.separator + PROJECT_RELATIVE_PROJECT_DIR;
		File dir = new File(PROJECT_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	/**
	 * 将注册码写入文件
	 * */
	public static void writeToRegisteFile(String str) {
		File file = new File(PROJECT_DIR + File.separator + "registe.key");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		try {
			FileWriter fw = new FileWriter(file,false);
			fw.write(str);
			fw.write("\n\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
