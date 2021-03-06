package com.lorent.service.impl;

import java.util.List;

import com.lorent.dao.VideoClipDao;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.VideoClipBean;
import com.lorent.service.VideoClipService;

public class VideoClipServiceImpl extends GenericServiceImpl<VideoClipDao, VideoClipBean, Integer> implements VideoClipService {

	@Override
	public boolean addVideoClip(VideoClipBean bean) throws Exception {
		daoFacade.getVideoClipDao().createVideoClip(bean);
		return true;
	}

	@Override
	public List<VideoClipBean> getAllVideoClip() throws Exception {
		return daoFacade.getVideoClipDao().getAllVideoClip();
	}

	@Override
	public List<VideoClipBean> getAllMonitor() throws Exception {
		return daoFacade.getVideoClipDao().getAllMonitor();
	}

	@Override
	public List getMonitorList(int index, int size) throws Exception {
		return daoFacade.getVideoClipDao().getMonitorList(index, size);
	}

	@Override
	public List<VideoClipBean> getVideoClipList(int index, int size)
			throws Exception {
		return daoFacade.getVideoClipDao().getVideoClipList(index, size);
	}

	@Override
	public List getVideoClipList(int index, int size, String category)
			throws Exception {
		return daoFacade.getVideoClipDao().getVideoClipList(index, size, category);
	}

	@Override
	public boolean deleteVideoClip(int id) throws Exception {
		return daoFacade.getVideoClipDao().delete(new Integer[]{id});
	}

}
