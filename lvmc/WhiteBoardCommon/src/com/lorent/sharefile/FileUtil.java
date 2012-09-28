package com.lorent.sharefile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class FileUtil {
	public static void copyFile(String oldFileNamePath, String newFileNamePath) throws Exception {
		int byteread = 0;
		File oldfile = new File(oldFileNamePath);
		if (oldfile.exists()) { // 文件存在时
			InputStream inStream = new FileInputStream(oldFileNamePath); // 读入原文件
			FileOutputStream fs = new FileOutputStream(newFileNamePath);
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);//写入
			}
			inStream.close();
		}
	}
	
	public static void deleteFile(String filePath) throws Exception {
		java.io.File myDelFile = new java.io.File(filePath);
		myDelFile.delete();
	}
	
	
	//递归删除
	private static void deletePath(File path) throws Exception{
		if (!path.exists()) {
			return;
		}
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] listFiles = path.listFiles();
		for (File file : listFiles) {
			deletePath(file);
		}
		path.delete();
	}
	
	public static void deletePath(String filePath) throws Exception{
		File file = new File(filePath);
		deletePath(file);
		
	}

}
