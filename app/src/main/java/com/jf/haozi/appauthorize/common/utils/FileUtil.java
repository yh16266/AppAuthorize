package com.jf.haozi.appauthorize.common.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.jf.haozi.appauthorize.common.base.BaseObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil extends BaseObject {

	private static String PROJECT_DIR;
	private static String REGISGE_FILE;

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
		REGISGE_FILE = PROJECT_DIR + File.separator + "registe.key";
		File file = new File(REGISGE_FILE);
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

	/**
	 * 读取文本文件中的内容
	 * */
	public static String ReadTxtFile(String strFilePath){
		String path = strFilePath;
		//文件内容字符串
		String content = "";
		//打开文件
		File file = new File(path);
		//如果path是传递过来的参数，可以做一个非目录的判断
		if (file.isDirectory()){
			Log.d("TestFile", "The File doesn't not exist.");
		}else{
			try {
				InputStream instream = new FileInputStream(file);
				if (instream != null){
					InputStreamReader inputreader = new InputStreamReader(instream);
					BufferedReader buffreader = new BufferedReader(inputreader);
					String line;
					//分行读取
					while (( line = buffreader.readLine()) != null) {
						content += line + "\n";
					}
					instream.close();
				}
			}catch (java.io.FileNotFoundException e){
				Log.d("TestFile", "The File doesn't not exist.");
			}catch (IOException e){
				Log.d("TestFile", e.getMessage());
			}
		}
		return content;
	}

	public static boolean isRegisteFileExist(){
		if(TextUtils.isEmpty(REGISGE_FILE)){
			return false;
		}
		File file = new File(REGISGE_FILE);
		if(file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 读取文本文件中的内容
	 * */
	public static String ReadRegisteFile(){
		if(TextUtils.isEmpty(REGISGE_FILE)){
			return "";
		}
		File file = new File(REGISGE_FILE);
		if(!file.exists()) {
			return "";
		}
		return ReadTxtFile(REGISGE_FILE);
	}
}
