package com.lorent.model;

public class VideoClipBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String videoClipUrlHigh;//高清视频存放url地址
	private String videoClipUrlStandard;//标清视频存放url地址
	private String thumbnailUrl;//视频缩略图url地址
	private String title;//标题
	private String description;//描述
	private String category;//类别
	private String createrName;
	private Boolean ismonitor;
	public Boolean getIsmonitor() {
		return ismonitor;
	}
	public void setIsmonitor(Boolean ismonitor) {
		this.ismonitor = ismonitor;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getCreaterNo() {
		return createrNo;
	}
	public void setCreaterNo(String createrNo) {
		this.createrNo = createrNo;
	}
	private String createrNo;
	public String getVideoClipUrlHigh() {
		return videoClipUrlHigh;
	}
	public void setVideoClipUrlHigh(String videoClipUrlHigh) {
		this.videoClipUrlHigh = videoClipUrlHigh;
	}
	public String getVideoClipUrlStandard() {
		return videoClipUrlStandard;
	}
	public void setVideoClipUrlStandard(String videoClipUrlStandard) {
		this.videoClipUrlStandard = videoClipUrlStandard;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
