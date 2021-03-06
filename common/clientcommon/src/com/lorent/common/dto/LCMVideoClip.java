package com.lorent.common.dto;

import java.io.Serializable;

public class LCMVideoClip implements Serializable {
	private static final long serialVersionUID = 1L;
	private String httpVideoUrlHyper;
	private String rtspVideoUrlHyper;
	private String httpVideoUrlHigh;
	private String httpVideoUrlStandard;
	private String rtspVideoUrlHigh;
	private String rtspVideoUrlStandard;
	private String thumbnailUrl;//视频缩略图url地址
	private String title;//标题
	private String description;//描述
	private String category;//类别
	private String createrName;
	private String createrNo;
	private Boolean ismonitor;//是否监控
	private String duration;//时长
	private Integer hits;//点击(人气)
	protected Integer id;
	protected Integer status;
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getHttpVideoUrlHigh() {
		return httpVideoUrlHigh;
	}
	public void setHttpVideoUrlHigh(String httpVideoUrlHigh) {
		this.httpVideoUrlHigh = httpVideoUrlHigh;
	}
	public String getHttpVideoUrlStandard() {
		return httpVideoUrlStandard;
	}
	public void setHttpVideoUrlStandard(String httpVideoUrlStandard) {
		this.httpVideoUrlStandard = httpVideoUrlStandard;
	}
	public String getRtspVideoUrlHigh() {
		return rtspVideoUrlHigh;
	}
	public void setRtspVideoUrlHigh(String rtspVideoUrlHigh) {
		this.rtspVideoUrlHigh = rtspVideoUrlHigh;
	}
	public String getRtspVideoUrlStandard() {
		return rtspVideoUrlStandard;
	}
	public void setRtspVideoUrlStandard(String rtspVideoUrlStandard) {
		this.rtspVideoUrlStandard = rtspVideoUrlStandard;
	}
	public String getHttpVideoUrlHyper() {
		return httpVideoUrlHyper;
	}
	public void setHttpVideoUrlHyper(String httpVideoUrlHyper) {
		this.httpVideoUrlHyper = httpVideoUrlHyper;
	}
	public String getRtspVideoUrlHyper() {
		return rtspVideoUrlHyper;
	}
	public void setRtspVideoUrlHyper(String rtspVideoUrlHyper) {
		this.rtspVideoUrlHyper = rtspVideoUrlHyper;
	}
}
