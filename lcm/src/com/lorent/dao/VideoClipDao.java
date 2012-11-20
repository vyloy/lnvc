package com.lorent.dao;

import java.util.List;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.VideoClipBean;

public interface VideoClipDao extends IGenericDao<VideoClipBean, Integer> {
	boolean createVideoClip(VideoClipBean videoClip)throws CustomSqlException,ArgsException;
	List<VideoClipBean> getVideoClipList(int index,int size) throws Exception;
	List<VideoClipBean> getVideoClipList(int index,int size,String category) throws Exception;
	List<VideoClipBean> getMonitorList(int index,int size) throws Exception;
	List<VideoClipBean> getAllVideoClip() throws Exception;
	List<VideoClipBean> getAllMonitor() throws Exception;
}
