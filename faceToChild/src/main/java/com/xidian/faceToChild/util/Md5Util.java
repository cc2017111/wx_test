package com.xidian.faceToChild.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	/**
	 * md5º”√‹À„∑®
	 */
	public static String encode(String key) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] btInput = key.getBytes();
		MessageDigest mdInst = null;
		try {
			mdInst = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		mdInst.update(btInput);
		
		byte[] md = mdInst.digest();
		
		int j = md.length;
		char str[] = new char[j * 2];
		int k=0;
		for (int i=0; i<j; i++){
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 &0xf];
		}
		return new String(str);
	}
	
	public static void main(String args[]){
		String d = Md5Util.encode("123456");
		System.out.println(d);
	}
}
