/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.log4j.Logger;

/**
 *
 * @author jack
 */
public class FileUtil {
    	private static Logger log = Logger.getLogger(FileUtil.class);
	/**
	 * 从JAR中复制文件到磁盘
	 * @param srcFilePath：源路径，既JAR包中的资源文件，路径相对于CLASSPATH
	 * @param destFilePath：目标路径，磁盘上的任意路径，绝对路径（一般为用户选择的文件夹路径）
	 * @return int：返回执行后的状态；0：失败；1：成功；（可以扩充其它状态）
	 */
	public static int fileCopy(String srcFilePath, String destFilePath){
		int flag = 0;
		try {
			BufferedInputStream fis = new BufferedInputStream(FileUtil.class.getResourceAsStream(srcFilePath));
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(destFilePath));
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
        
        public static int fileCopy(InputStream inputStream, String destFilePath){
		int flag = 0;
		try {
			BufferedInputStream fis = new BufferedInputStream(inputStream);
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(destFilePath));
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
        
        public static int localFileCopy(String srcFilePath, String destFilePath){
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
        
        public static void extraJarFile(String jarFilePath, String destDirPath)throws Exception{
            String temp = System.getProperty("java.io.tmpdir");
            String tempJarFile = System.currentTimeMillis() + ".jar";
            FileUtil.fileCopy(jarFilePath, temp + tempJarFile);
            JarFile jarFile = new JarFile(temp + tempJarFile);
            Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements()){
                JarEntry nextElement = entries.nextElement();
                log.info("copy file:" + nextElement.getName());
                FileUtil.fileCopy(jarFile.getInputStream(nextElement), destDirPath + nextElement.getName());
            }
        }
	
        public static void main(String[] args) {
            FileUtil.fileCopy("com/lorent/lvmc/config/default.obj", Constants.LayoutDataPath + "/default.obj");//("/com/lorent/lvmc/config/default.obj"));
        }
}
