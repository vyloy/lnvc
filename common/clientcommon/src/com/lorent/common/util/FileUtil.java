/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

/**
 * 
 * @author jack
 */
public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class);

	
	//bt字节参考量
	public static final long SIZE_BT=1024L;
	//KB字节参考量
	public static final long SIZE_KB=SIZE_BT*1024L;
	//MB字节参考量
	public static final long SIZE_MB=SIZE_KB*1024L;
	//GB字节参考量
	public static final long SIZE_GB=SIZE_MB*1024L;
	//TB字节参考量
	public static final long SIZE_TB=SIZE_GB*1024L;
	
	public static final int SACLE=2;
	/**
	 * 从JAR中复制文件到磁盘
	 * 
	 * @param srcFilePath
	 *            ：源路径，既JAR包中的资源文件，路径相对于CLASSPATH
	 * @param destFilePath
	 *            ：目标路径，磁盘上的任意路径，绝对路径（一般为用户选择的文件夹路径）
	 * @return int：返回执行后的状态；0：失败；1：成功；（可以扩充其它状态）
	 */
	public static int fileCopy(String srcFilePath, String destFilePath) {
		int flag = 0;
		try {
			BufferedInputStream fis = new BufferedInputStream(FileUtil.class
					.getResourceAsStream(srcFilePath));
			BufferedOutputStream fos = new BufferedOutputStream(
					new FileOutputStream(destFilePath));
			byte[] buf = new byte[1024];
			int c = 0;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fis.close();
			fos.close();
			flag = 1;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("fileCopy", e);
		}
		return flag;
	}

	public static int fileCopy(InputStream inputStream, String destFilePath) {
		int flag = 0;
		try {
			BufferedInputStream fis = new BufferedInputStream(inputStream);
			BufferedOutputStream fos = new BufferedOutputStream(
					new FileOutputStream(destFilePath));
			byte[] buf = new byte[1024];
			int c = 0;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fis.close();
			fos.close();
			flag = 1;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("fileCopy", e);
		}
		return flag;
	}

	public static int localFileCopy(String srcFilePath, String destFilePath) {
		int flag = 0;
		try {
			FileInputStream fis = new FileInputStream(srcFilePath);
			FileOutputStream fos = new FileOutputStream(destFilePath);
			byte[] buf = new byte[1024];
			int c = 0;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fis.close();
			fos.close();
			flag = 1;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("localFileCopy", e);
		}
		return flag;
	}

	public static void extraJarFile(String jarFilePath, String destDirPath)
			throws Exception {
		String temp = System.getProperty("java.io.tmpdir");
		String tempJarFile = System.currentTimeMillis() + ".jar";
		FileUtil.fileCopy(jarFilePath, temp + tempJarFile);
		JarFile jarFile = new JarFile(temp + tempJarFile);
		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			JarEntry nextElement = entries.nextElement();
			log.info("copy file:" + nextElement.getName());
			FileUtil.fileCopy(jarFile.getInputStream(nextElement), destDirPath
					+ nextElement.getName());
		}
	}

	public static String fileSizeToUserString(long filesize) throws Exception{
		if (filesize >= 0 && filesize < SIZE_BT) {
			return filesize +"B";
		}else if(filesize >= SIZE_BT && filesize < SIZE_KB){
			return filesize/SIZE_BT+"KB";
		}else if(filesize >= SIZE_KB && filesize < SIZE_MB){
			return filesize/SIZE_KB+"MB";
		}else if(filesize >= SIZE_MB && filesize < SIZE_GB){
			BigDecimal longs=new BigDecimal(Double.valueOf(filesize+"").toString());
		    BigDecimal sizeMB=new BigDecimal(Double.valueOf(SIZE_MB+"").toString());
		    String result=longs.divide(sizeMB, SACLE,BigDecimal.ROUND_HALF_UP).toString();
		    return result+"GB";
		}else{
			BigDecimal longs=new BigDecimal(Double.valueOf(filesize+"").toString());
		    BigDecimal sizeMB=new BigDecimal(Double.valueOf(SIZE_GB+"").toString());
		    String result=longs.divide(sizeMB, SACLE,BigDecimal.ROUND_HALF_UP).toString();
		    return result+"TB";
		}
	}
	
	//获得所有目录下的所有文件,包括子文件夹里的文件
    public static List<File> getAllFiles(File directory){
    	ArrayList<File> resultList = new ArrayList<File>();
    	if (directory.isDirectory()) {
    		LinkedList<File> synchronizedList = new LinkedList<File>();
			synchronizedList.add(directory);
    		do {
    			File poll = synchronizedList.poll();
    			if (poll.isDirectory()) {
					File[] listFiles = poll.listFiles();
					for (File file : listFiles) {
						synchronizedList.add(file);
					}
				}
    			else{
    				resultList.add(poll);
    			}
			} while (!synchronizedList.isEmpty());
		}
    	return resultList;
    }
    
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		List<File> allFiles = FileUtil.getAllFiles(new File("D:\\Users\\panda\\Desktop\\V Vo"));
		for (File file : allFiles) {
			System.out.println(file);
		}
	}
}
