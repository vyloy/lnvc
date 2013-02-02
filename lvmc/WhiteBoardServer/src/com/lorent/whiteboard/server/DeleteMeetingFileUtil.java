package com.lorent.whiteboard.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.commonconfig.ConfigUtil;
import com.lorent.util.FileListConfigUtilFactory;
import com.lorent.whiteboard.server.trigger.QuartzTrigger;

public class DeleteMeetingFileUtil {

	private static final Logger logger = LoggerFactory
	.getLogger(DeleteMeetingFileUtil.class);
	
	long saveTime ;//Integer.parseInt(QuartzTrigger.config.getProperty("saveTime"));//1000 * 60 * 3 * 1;//毫秒     3小时
	/*public static void execute1() {
		deleteTmpFile();
		Properties properties = new Properties();
		String rootPath = ServerConfig.AppPath + "/files/";
		File rootDir = new File(rootPath);
		if(!rootDir.exists()||!rootDir.isDirectory()){
			return;
		}
		File[] meetingDirs = rootDir.listFiles();
		if(meetingDirs.length>0){
			for(File meetingDir:meetingDirs){
				File confFile = new File(meetingDir,"/conf/"+meetingDir.getName() + ".conf");
				if(confFile.exists()){
					InputStreamReader inputStreamReader = null;
					properties.clear();
					try{
						
						inputStreamReader = new InputStreamReader(new FileInputStream(confFile), "UTF-8");
						properties.load(inputStreamReader);
					}catch(Exception ex){
						
					}finally{
						if(inputStreamReader!=null){
							try {
								inputStreamReader.close();
							} catch (IOException e) {
								inputStreamReader = null;
							}
						}
					}
					File[] meetingFiles = meetingDir.listFiles();
					if(meetingFiles.length>0){
						for(File meetingFile:meetingFiles){
							if(meetingFile.isFile()){
								int idx = meetingFile.getName().indexOf("_");
								String key = null;
								if(idx>-1){
									key = meetingFile.getName().substring(idx + "_".length());
								}else{
									key = meetingFile.getName();
								}
								String realFileName = properties.getProperty(key);
								if(!meetingFile.getName().equals(realFileName)){
									deleteFileByTime(meetingFile,saveTime);
								}
							}
						}
						
					}
				}
			}
		}
		
	}*/
	
	public void execute() {
		
		saveTime = Integer.parseInt(QuartzTrigger.config.getProperty("saveTime"));
//		logger.error("saveTime=============================" + saveTime);
//		logger.info("DeleteMeetingFileUtil.execute ");
		deleteTmpFile();
		deleteFilesDirFile();
		
	}
	
	public void deleteFilesDirFile() {
		final Properties properties = new Properties();
		final Properties properties1 = new Properties();
		String rootPath = ServerConfig.AppPath + "/files/";
		File rootDir = new File(rootPath);
		if (!rootDir.exists() || !rootDir.isDirectory()) {
			return;
		}
		File[] meetingDirs = rootDir.listFiles();
		if (meetingDirs.length > 0) {

			for (File meetingDir : meetingDirs) {
				File confFile = new File(meetingDir, "/conf/"
						+ meetingDir.getName() + ".conf");
				File deleteConfFile = new File(meetingDir, "/conf/"
						+ meetingDir.getName()
						+ FileListConfigUtilFactory.DELETE_CONF_NAME + ".conf");
				// if(confFile.exists()){
				InputStreamReader inputStreamReader = null;
				properties.clear();
				properties1.clear();
				try {
					if (confFile.exists()) {
						inputStreamReader = new InputStreamReader(
								new FileInputStream(confFile), "UTF-8");
						properties.load(inputStreamReader);
						inputStreamReader.close();
					}
					if (deleteConfFile.exists()) {
						inputStreamReader = new InputStreamReader(
								new FileInputStream(deleteConfFile), "UTF-8");
						properties1.load(inputStreamReader);
					}
				} catch (Exception ex) {
					logger.error("deleteFilesDirFile",ex);
				} finally {
					if (inputStreamReader != null) {
						try {
							inputStreamReader.close();
						} catch (IOException e) {
							logger.error("deleteFilesDirFile",e);
							inputStreamReader = null;
						}
					}
				}
				delete1stLevelFilesInDir(meetingDir, new DeleteStrategy() {

					@Override
					void delete(File f) {
						int idx = f.getName().indexOf("_");
						String key = null;
						if (idx > -1) {
							key = f.getName().substring(idx + "_".length());
						} else {
							key = f.getName();
						}
						String realFileName = properties.getProperty(key);
						if (realFileName == null
								|| !f.getName().equals(realFileName)) {
							String modifyTimeStr = properties1.getProperty(f
									.getName());
							if (modifyTimeStr != null) {
								long modifyTime = Long.parseLong(modifyTimeStr);
								long currentTime = System.currentTimeMillis();
								if (currentTime - modifyTime >= saveTime) {
									String filename = f.getName();
									System.out.println("delete "+filename);
									logger.info("delete1stLevelFilesInDir: delete file "+filename);
									f.delete();
									ConfigUtil deleteConfigUtil = FileListConfigUtilFactory
											.getDeleteFileListConfUtilByMeetingID(f
													.getParentFile().getName());
									try {
										deleteConfigUtil.removeProperty(filename);
									} catch (Exception e) {
										logger.error("delete1stLevelFilesInDir", e);
										e.printStackTrace();
									}
								}
							} else {
//								deleteFileByTime(f, saveTime);
							}
						}

						// if(realFileName==null){
						//								
						// deleteFileByTime(f,saveTime);
						// }else{
						// if(!f.getName().equals(realFileName)){
						// File realFile = new File(f.getParentFile(),"/" +
						// realFileName);
						// if(realFile.exists()){
						// long realFileTime = realFile.lastModified();
						// long modifyTime = f.lastModified();
						// if(realFileTime-modifyTime>saveTime){
						// f.delete();
						// }
						// }
						// }
						// }
					}

				});
				// }
			}
		}
	}
	
	public void deleteTmpFile(){
		
//		long currentTime = System.currentTimeMillis();
		String rootPath = ServerConfig.AppPath + "/tmp/";
//		System.out.println("deleting tmp dir...............");
//		File rootDir = new File(rootPath);
//		if(!rootDir.exists()||!rootDir.isDirectory()){
//			return;
//		}
//		File[] tmpFiles = rootDir.listFiles();
//		if(tmpFiles.length>0){
//			for(File tmpFile:tmpFiles){
//				deleteFileByTime(tmpFile,saveTime,currentTime);
//			}
//		}
		
		delete1stLevelFilesInDir(rootPath,new DeleteStrategy(){
			@Override
			void delete(File f) {
//				System.out.println(f.getAbsolutePath());
				deleteFileByTime(f,saveTime);
			}
			
		});
	}
	
	public static void delete1stLevelFilesInDir(final String dirPath,DeleteStrategy deleteStrategy){
		File rootDir = new File(dirPath);
		delete1stLevelFilesInDir(rootDir,deleteStrategy);
	}
	
	public static void delete1stLevelFilesInDir(final File rootDir,DeleteStrategy deleteStrategy){
		
		if(!rootDir.exists()||!rootDir.isDirectory()){
			return;
		}
		File[] tmpFiles = rootDir.listFiles();
		if(tmpFiles.length>0){
			for(File tmpFile:tmpFiles){
				if(tmpFile.isFile()&&tmpFile.exists()){
					deleteStrategy.delete(tmpFile);
				}
			}
		}
	}
	
	public static void deleteFileByTime(File f,long time){
		long currentTime = System.currentTimeMillis();
		long modifyTime = f.lastModified();
		if(currentTime-modifyTime>=time){
			logger.info("deleteFileByTime: delete file "+f.getName());
			f.delete();
		}
	}
	
	public static void main(String args[]){
		File f = new File("e:/soft");
		System.out.println(f.getName());
		
		File f1 = new File(f,"/android/" + "window系统下eclipse安装android开发工具.txt");
		System.out.println("修改前时间：" + f1.lastModified());
		long time = System.currentTimeMillis();
		f1.setLastModified(time);
		System.out.println(time + "修改后时间：" + f1.lastModified());
	}
	
	public static abstract class DeleteStrategy{
		abstract void delete(File f);
//		private static File rootDir;
//		public static void setRootDir(File dir){
//			rootDir = dir;
//		}
//		public static File getRootDir(){
//			return rootDir;
//		}
	}
}
