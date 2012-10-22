package com.lorent.service;

import java.util.List;

import com.lorent.dao.VideoClipDao;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.VideoClipBean;

public interface VideoClipService extends IGenericService<VideoClipDao, VideoClipBean, Integer> {
	public List<VideoClipBean> getAllVideoClip() throws Exception;
	public List getVideoClipList(int index,int size) throws Exception;
	public boolean addVideoClip(VideoClipBean bean) throws Exception;
	public boolean deleteVideoClip(int id) throws Exception;
}
