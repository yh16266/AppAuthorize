package com.jf.haozi.appauthorize.common.utils;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HashUtil {

	/**
	 * 字符串MD5加密
	 * */
	public static String md5(String string) {
		byte[] hash;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			hash = messageDigest.digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * 字符串MD5加密
	 * */
	public static String md5_16(String string) {
		return md5(string).substring(8,24);
	}

	/**
	 * 字符串MD5加密
	 * */
	public static String md5_8(String string) {
		return md5(string).substring(8,16);
	}

	public static String getCode(Context mContext){
		final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		Log.d("HashUtil","deviceId:"+deviceId);
		String deviceIdMd5 = md5(deviceId);
		Log.d("HashUtil","deviceIdMd5:"+deviceIdMd5);
		deviceIdMd5 = md5_16(deviceId);
		Log.d("HashUtil","deviceId md5_16:"+deviceIdMd5);
		deviceIdMd5 = md5_8(deviceId);
		Log.d("HashUtil","deviceId md5_8:"+deviceIdMd5);
		return deviceIdMd5;
	}

	public static String getKey(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String timeNow = df.format(new Date());

		return "";
	}

	private static String tenTohex2(int tenValue){
		String digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer rst = new StringBuffer();
		int tempValue = tenValue;
		if(tempValue < digit.length()){
			rst.append(digit.charAt(tempValue));
			return rst.toString();
		}
		while(tempValue >= digit.length()){
			rst.insert(0,digit.charAt(tempValue%digit.length()));
			tempValue = tempValue/digit.length();
		}
		rst.insert(0,digit.charAt(tempValue));

		return rst.toString();
	}

	private static int hex2ToTen(String hex2Value){
		String digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int rst = 0;
		if(TextUtils.isEmpty(hex2Value)){
			return rst;
		}
		for (int i=0;i<hex2Value.length();i++) {
			char item = hex2Value.charAt(i);
			int value = digit.indexOf(item);
			if(i == 0){
				rst = value;
			}else{
				rst = rst*value;
			}
		}
		return rst;
	}
}
