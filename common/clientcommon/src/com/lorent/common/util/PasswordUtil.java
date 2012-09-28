package com.lorent.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class PasswordUtil {

	  /**
	   * 加密String明文输入,String密文输出
	   */
	  public static String getEncString(String strMing) {
	      byte[] byteMing = null;
	      String strMi = "";
	      BASE64Encoder base64en = new BASE64Encoder();
	      try {
	        byteMing = strMing.getBytes("UTF8");
	        strMi = base64en.encode(byteMing);
	      } catch (Exception e) {
	        throw new RuntimeException(
	            "Error initializing SqlMap class. Cause: " + e);
	      } finally {
	        base64en = null;
	        byteMing = null;
	      }
	      return strMi;
	  }

	  /**
	   * 解密 以String密文输入,String明文输出
	   * @param strMi
	   * @return
	   */
	  public static String getDesString(String strMi) {
		  if(strMi == null){
			  return "";
		  }
	      BASE64Decoder base64De = new BASE64Decoder();
	      byte[] byteMi = null;
	      String strMing = "";
	      try {
	        byteMi = base64De.decodeBuffer(strMi);
	        strMing = new String(byteMi, "UTF8");
	      } catch (Exception e) {
	        throw new RuntimeException(
	            "Error initializing SqlMap class. Cause: " + e);
	      } finally {
	        base64De = null;
	        byteMi = null;
	      }
	      return strMing;
	  }

	  
	  public static void main(String args[]) {
	      String str1 = "123456789012345678901234567890";
	      //DES加密
	      String str2 = getEncString(str1);
	      String deStr = getDesString(str2);
	      System.out.println("密文:" + str2);
	      //DES解密
	      System.out.println("明文:" + deStr);
	  }


	}

