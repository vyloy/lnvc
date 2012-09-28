package com.lorent.web.action;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private final String RESOURCE_PATH = "/com/lorent/resource/";
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public InputStream getInputStream(){
		return ServletActionContext.getServletContext().getResourceAsStream(RESOURCE_PATH+fileName);
	}
	
}
