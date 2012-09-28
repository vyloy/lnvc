package com.lorent.model;

import java.io.File;

/**
 * 点播媒体
 * @author jack
 *
 */
public class MediaBean extends BaseModel{

	private static final long serialVersionUID = 1L;
	//媒体名字
	private String mediaName;
	//媒体路径()
	private String mediaPath;
	//上传文件自动生成的名字
	private String fileName;
	//上传文件的原始名字
	private String originalName;
	//客户ID
	private CustomerBean customer;
	//描述
	private String mediaDesc;
	//暂存file文件
	private File file;


	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getMediaDesc() {
		return mediaDesc;
	}

	public void setMediaDesc(String mediaDesc) {
		this.mediaDesc = mediaDesc;
	}
	
	
}
