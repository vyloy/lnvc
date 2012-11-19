package com.lorent.web.xmlrpc.handler;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.model.VideoClipBean;

public class VideoCommunityHandler extends BaseHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//上传视频信息
	public boolean uploadVideoClipInfo(String videoClipNameHyper,String videoClipNameHigh,String videoClipNameStandard,
			String thumbnailName,String title,String description,String ftpSrvIp,String createrName,String createrNo,
			String duration,String category) throws Exception{
		//VideoCommunity
		String rtspVideoClipUrlHyper = "rtsp://"+ftpSrvIp+":554/"+videoClipNameHyper;
		String rtspVideoClipUrlHigh = "rtsp://"+ftpSrvIp+":554/"+videoClipNameHigh;
		String rtspVideoClipUrlStandard = "rtsp://"+ftpSrvIp+":554/"+videoClipNameStandard;
		String httpVideoClipUrlHyper = "http://"+ftpSrvIp+":8800/"+videoClipNameHyper;
		String httpVideoClipUrlHigh = "http://"+ftpSrvIp+":8800/"+videoClipNameHigh;
		String httpVideoClipUrlStandard = "http://"+ftpSrvIp+":8800/"+videoClipNameStandard;
		String thumbnailUrl = "http://"+ftpSrvIp+":8800/"+thumbnailName;
		VideoClipBean bean = new VideoClipBean();
		bean.setRtspVideoUrlHyper(rtspVideoClipUrlHyper);
		bean.setRtspVideoUrlHigh(rtspVideoClipUrlHigh);
		bean.setRtspVideoUrlStandard(rtspVideoClipUrlStandard);
		bean.setHttpVideoUrlHyper(httpVideoClipUrlHyper);
		bean.setHttpVideoUrlHigh(httpVideoClipUrlHigh);
		bean.setHttpVideoUrlStandard(httpVideoClipUrlStandard);
		bean.setThumbnailUrl(thumbnailUrl);
		bean.setTitle(title);
		bean.setDescription(description);
		bean.setCreaterName(createrName);
		bean.setCreaterNo(createrNo);
		bean.setIsmonitor(false);
		bean.setDuration(duration);
		bean.setCategory(category);
		serviceFacade.getVideoClipService().addVideoClip(bean);
		return true;
	}
	
	//上传监控信息
	public boolean uploadMonitorInfo(String liveStreamUrl,String thumbnailName,String title,String description,String ftpSrvIp,String createrName,String createrNo)throws Exception{
		String thumbnailUrl = "http://"+ftpSrvIp+":8800/"+thumbnailName;
		VideoClipBean bean = new VideoClipBean();
		bean.setRtspVideoUrlHigh(liveStreamUrl);
		bean.setRtspVideoUrlStandard(liveStreamUrl);
		bean.setHttpVideoUrlHigh(liveStreamUrl);
		bean.setHttpVideoUrlStandard(liveStreamUrl);
		bean.setHttpVideoUrlHyper(liveStreamUrl);
		bean.setRtspVideoUrlHyper(liveStreamUrl);
		bean.setThumbnailUrl(thumbnailUrl);
		bean.setTitle(title);
		bean.setDescription(description);
		bean.setCreaterName(createrName);
		bean.setCreaterNo(createrNo);
		bean.setIsmonitor(true);
		serviceFacade.getVideoClipService().addVideoClip(bean);
		return true;
	}
	
	//删除视频信息
	public boolean deleteVideoClip(int videoClipId) throws Exception{
		return serviceFacade.getVideoClipService().deleteVideoClip(videoClipId);
	}
	
	//获得监控视频列表信息
	public List<LCMVideoClip> getMonitorList(Integer pageIndex,Integer pageSize) throws Exception{
		List<VideoClipBean> videoClipList = serviceFacade.getVideoClipService().getMonitorList(pageIndex, pageSize);
		 if (videoClipList.size() <= 0) {
			return null;
		 }
		 ArrayList<LCMVideoClip> arrayList = new ArrayList<LCMVideoClip>();
		 for (VideoClipBean videoClipBean : videoClipList) {
			 LCMVideoClip lcmVideoClip = new LCMVideoClip();
			 lcmVideoClip.setId(videoClipBean.getId());
			 lcmVideoClip.setStatus(videoClipBean.getStatus());
			 lcmVideoClip.setRtspVideoUrlHigh(videoClipBean.getRtspVideoUrlHigh());
			 lcmVideoClip.setRtspVideoUrlStandard(videoClipBean.getRtspVideoUrlStandard());
			 lcmVideoClip.setHttpVideoUrlHigh(videoClipBean.getHttpVideoUrlHigh());
			 lcmVideoClip.setHttpVideoUrlStandard(videoClipBean.getHttpVideoUrlStandard());
			 lcmVideoClip.setCreaterName(videoClipBean.getCreaterName());
			 lcmVideoClip.setCreaterNo(videoClipBean.getCreaterNo());
			 lcmVideoClip.setThumbnailUrl(videoClipBean.getThumbnailUrl());
			 lcmVideoClip.setTitle(videoClipBean.getTitle());
			 lcmVideoClip.setDescription(videoClipBean.getDescription());
			 lcmVideoClip.setCategory(videoClipBean.getCategory());
			 lcmVideoClip.setIsmonitor(videoClipBean.getIsmonitor());
			 lcmVideoClip.setHttpVideoUrlHyper(videoClipBean.getHttpVideoUrlHyper());
			 lcmVideoClip.setRtspVideoUrlHyper(videoClipBean.getRtspVideoUrlHyper());
			 arrayList.add(lcmVideoClip);
		 }
		 return arrayList;
	}
	
	//获得某类别视频列表信息
	public List<LCMVideoClip> getVideoClipList(Integer pageIndex,Integer pageSize,String category) throws Exception{
		
		List<VideoClipBean> videoClipList = serviceFacade.getVideoClipService().getVideoClipList(pageIndex, pageSize);
		 if (videoClipList.size() <= 0) {
			return null;
		 }
		 ArrayList<LCMVideoClip> arrayList = new ArrayList<LCMVideoClip>();
		 for (VideoClipBean videoClipBean : videoClipList) {
			 if (videoClipBean.getCategory() != null && videoClipBean.getCategory().equals(category)) {
				 LCMVideoClip lcmVideoClip = new LCMVideoClip();
				 lcmVideoClip.setId(videoClipBean.getId());
				 lcmVideoClip.setStatus(videoClipBean.getStatus());
				 lcmVideoClip.setRtspVideoUrlHigh(videoClipBean.getRtspVideoUrlHigh());
				 lcmVideoClip.setRtspVideoUrlStandard(videoClipBean.getRtspVideoUrlStandard());
				 lcmVideoClip.setHttpVideoUrlHigh(videoClipBean.getHttpVideoUrlHigh());
				 lcmVideoClip.setHttpVideoUrlStandard(videoClipBean.getHttpVideoUrlStandard());
				 lcmVideoClip.setCreaterName(videoClipBean.getCreaterName());
				 lcmVideoClip.setCreaterNo(videoClipBean.getCreaterNo());
				 lcmVideoClip.setThumbnailUrl(videoClipBean.getThumbnailUrl());
				 lcmVideoClip.setTitle(videoClipBean.getTitle());
				 lcmVideoClip.setDescription(videoClipBean.getDescription());
				 lcmVideoClip.setCategory(videoClipBean.getCategory());
				 lcmVideoClip.setIsmonitor(videoClipBean.getIsmonitor());
				 lcmVideoClip.setIsmonitor(videoClipBean.getIsmonitor());
				 lcmVideoClip.setHttpVideoUrlHyper(videoClipBean.getHttpVideoUrlHyper());
				 lcmVideoClip.setRtspVideoUrlHyper(videoClipBean.getRtspVideoUrlHyper());
				 lcmVideoClip.setDuration(videoClipBean.getDuration());
				 lcmVideoClip.setHits(videoClipBean.getHits());
				 arrayList.add(lcmVideoClip);
			 }
		 }
		 return arrayList;
	}
	
	//获得视频列表信息
	public List<LCMVideoClip> getVideoClipList(Integer pageIndex,Integer pageSize) throws Exception{
		 List<VideoClipBean> videoClipList = serviceFacade.getVideoClipService().getVideoClipList(pageIndex, pageSize);
		 if (videoClipList.size() <= 0) {
			return null;
		 }
		 ArrayList<LCMVideoClip> arrayList = new ArrayList<LCMVideoClip>();
		 for (VideoClipBean videoClipBean : videoClipList) {
			 LCMVideoClip lcmVideoClip = new LCMVideoClip();
			 lcmVideoClip.setId(videoClipBean.getId());
			 lcmVideoClip.setStatus(videoClipBean.getStatus());
			 lcmVideoClip.setRtspVideoUrlHigh(videoClipBean.getRtspVideoUrlHigh());
			 lcmVideoClip.setRtspVideoUrlStandard(videoClipBean.getRtspVideoUrlStandard());
			 lcmVideoClip.setHttpVideoUrlHigh(videoClipBean.getHttpVideoUrlHigh());
			 lcmVideoClip.setHttpVideoUrlStandard(videoClipBean.getHttpVideoUrlStandard());
			 lcmVideoClip.setCreaterName(videoClipBean.getCreaterName());
			 lcmVideoClip.setCreaterNo(videoClipBean.getCreaterNo());
			 lcmVideoClip.setThumbnailUrl(videoClipBean.getThumbnailUrl());
			 lcmVideoClip.setTitle(videoClipBean.getTitle());
			 lcmVideoClip.setDescription(videoClipBean.getDescription());
			 lcmVideoClip.setCategory(videoClipBean.getCategory());
			 lcmVideoClip.setIsmonitor(videoClipBean.getIsmonitor());
			 lcmVideoClip.setIsmonitor(videoClipBean.getIsmonitor());
			 lcmVideoClip.setHttpVideoUrlHyper(videoClipBean.getHttpVideoUrlHyper());
			 lcmVideoClip.setRtspVideoUrlHyper(videoClipBean.getRtspVideoUrlHyper());
			 lcmVideoClip.setDuration(videoClipBean.getDuration());
			 lcmVideoClip.setHits(videoClipBean.getHits());
			 arrayList.add(lcmVideoClip);
		 }
		 return arrayList;
	}
	
	public Integer getVideoListLength() throws Exception{
		return serviceFacade.getVideoClipService().getAllVideoClip().size();
	}
	
	public Integer getVideoListLength(String category) throws Exception{
		List<VideoClipBean> allVideoClip = serviceFacade.getVideoClipService().getAllVideoClip();
		ArrayList<VideoClipBean> arrayList = new ArrayList<VideoClipBean>();
		for (VideoClipBean videoClipBean : allVideoClip) {
			if (videoClipBean.getCategory() != null && videoClipBean.getCategory().equals(category)) {
				arrayList.add(videoClipBean);
			}
		}
		return arrayList.size();
	}
	
	public Integer getMonitorListLength() throws Exception{
		return serviceFacade.getVideoClipService().getAllMonitor().size();
	}
}
