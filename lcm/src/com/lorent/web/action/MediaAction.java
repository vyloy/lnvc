package com.lorent.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lorent.model.MediaBean;
import com.lorent.model.UserBean;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.ThreadLocaleUtil;

public class MediaAction extends BaseAction<MediaBean, Integer> {

	private static final long serialVersionUID = 1L;
	private String title;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private MediaBean addMedia;
	private MediaBean searchMedia;
	private UserBean user = ThreadLocaleUtil.getUser();

	public String toMediaList() throws Exception{
		searchMode = false;
		searchMedia = new MediaBean();
		searchMedia.setStatus(Constant.RECORD_STATUS_VALID);
		searchMedia.setCustomer(user.getCustomer());
		setSubPageMap(serviceFacade.getMediaService(), orderString, searchMedia);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/mediaAction_toMediaList_media_list.action");
		return SUCCESS;
	}
	
	public String searchMedia() throws Exception{
		searchMode = true;
		setSubPageMap(serviceFacade.getMediaService(), orderString, searchMedia);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/mediaAction_searchMedia_media_list.action");
		return SUCCESS;
	}
	
	public String sortMedia() throws Exception{
		setSortField();
		if(searchMode){
			return searchMedia();
		}else{
			return toMediaList();			
		}
	}
	
	public String toEditMedia() throws Exception{
		addMedia = serviceFacade.getMediaService().get(getSelectedIds()[0]);
		upload = new File(addMedia.getMediaPath());
		return SUCCESS;
	}
	
	public String toAddMedia() throws Exception{
		addMedia = new MediaBean();
		return SUCCESS;
	}
	
	public String doEditMedia() throws Exception{
		String path = ServletActionContext.getServletContext().getRealPath("/") + "file/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String suffix = this.uploadFileName.substring(this.uploadFileName.lastIndexOf("."));
		String fileName = sdf.format(new Date()) + suffix;
		addMedia.setMediaPath(path + fileName);
		addMedia.setOriginalName(this.uploadFileName);
		addMedia.setFile(upload);
		addMedia.setFileName(fileName);
		if(addMedia.getId()==null){//add
			addMedia.setCustomer(user.getCustomer());
			addMedia.setStatus(Constant.RECORD_STATUS_VALID);
			serviceFacade.getMediaService().createMedia(addMedia);
		}else{//update
			serviceFacade.getMediaService().renewMedia(addMedia);
		}
		callBackUrl = "app/mediaAction_toMediaList_media_list.action";
		return SUCCESS;
	}
	
	public String deleteMedia() throws Exception{
		List<MediaBean> list = serviceFacade.getMediaService().get(getSelectedIds());
		serviceFacade.getMediaService().removeMedia(list);
		if(searchMode){
			return searchMedia();
		}else{
			return toMediaList();
		}
	}
	
	//属性
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	//ModelDriven.getModel()
	public MediaBean getModel() {
		return addMedia;
	}
	public MediaBean getSearchMedia() {
		return searchMedia;
	}
	public void setSearchMedia(MediaBean searchMedia) {
		this.searchMedia = searchMedia;
	}

	public MediaBean getAddMedia() {
		return addMedia;
	}

	public void setAddMedia(MediaBean addMedia) {
		this.addMedia = addMedia;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
}
