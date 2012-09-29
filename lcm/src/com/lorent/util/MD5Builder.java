package com.lorent.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MD5Builder {
	static Logger logger = Logger.getLogger(MD5Builder.class);

	static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String getMD5(File file) {
		FileInputStream fis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			fis = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}

			byte[] b = md.digest();
			return byteToHexString(b);
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getMD5(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] b = md.digest(message.getBytes());
			return byteToHexString(b);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getMD5(String message,String salt) {
		message += "{"+salt+"}";
		return getMD5(message);
	}

	private static String byteToHexString(byte[] tmp) {
		char[] str = new char[32];

		int k = 0;
		for (int i = 0; i < 16; ++i) {
			byte byte0 = tmp[i];
			str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

			str[(k++)] = hexDigits[(byte0 & 0xF)];
		}
		String s = new String(str);
		return s;
	}
	
	public static void main(String[] args) {
		String encode = MD5Builder.getMD5("1234","admin");
		
//		encode = MD5Builder.getMD5(encode,"814815");
		System.out.println(encode);
		System.out.println(MD5Builder.getMD5("123456789012345","admin"));
	}
}