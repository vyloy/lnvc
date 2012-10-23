package com.lorent.model;

public class VideoClipBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String videoClipUrl;//视频存放url地址
	private String thumbnailUrl;//视频缩略图url地址
	private String title;//标题
	private String description;//描述
	private String category;//类别
	public String getVideoClipUrl() {
		return videoClipUrl;
	}
	public void setVideoClipUrl(String videoClipUrl) {
		this.videoClipUrl = videoClipUrl;
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
