package com.lorent.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.VideoClipDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.ConferenceBean;
import com.lorent.model.VideoClipBean;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;

public class VideoClipDaoImpl extends GenericDaoImpl<VideoClipBean,Integer> implements VideoClipDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean createVideoClip(VideoClipBean videoClip)
			throws CustomSqlException, ArgsException {
		if (videoClip == null ) {
			throw new ArgsException("args.inputisrequired");
		}
		save(videoClip);
		return true;
	}

	@Override
	public List<VideoClipBean> getVideoClipList(int index, int size)
			throws Exception {
		List<VideoClipBean> list = new ArrayList<VideoClipBean>();
		String hql = "from VideoClipBean video where video.ismonitor = false order by video.id";
		List<VideoClipBean> all = queryByHql(hql);
		int begin =  index * size;
		for (int i = 0; i < all.size(); i++) {
			if (i >= begin && i< begin+size) {
				list.add(all.get(i));
			}
		}
		return list;
	}

	@Override
	public List<VideoClipBean> getVideoClipList(int index, int size,
			String category) throws Exception {
		List<VideoClipBean> list = new ArrayList<VideoClipBean>();
		String hql = "from VideoClipBean video where video.ismonitor = false and video.category='"+category+"' order by video.id";
		List<VideoClipBean> all = queryByHql(hql);
		int begin =  index * size;
		for (int i = 0; i < all.size(); i++) {
			if (i >= begin && i< begin+size) {
				list.add(all.get(i));
			}
		}
		return list;
	}

	@Override
	public List<VideoClipBean> getMonitorList(int index, int size)
			throws Exception {
		List<VideoClipBean> list = new ArrayList<VideoClipBean>();
		String hql = "from VideoClipBean video where video.ismonitor = true order by video.id";
		List<VideoClipBean> all = queryByHql(hql);
		int begin =  index * size;
		for (int i = 0; i < all.size(); i++) {
			if (i >= begin && i< begin+size) {
				list.add(all.get(i));
			}
		}
		return list;
	}

	@Override
	public List<VideoClipBean> getAllMonitor() throws Exception {
		String hql = "from VideoClipBean video where video.ismonitor = true order by video.id";
		List<VideoClipBean> list = queryByHql(hql);
		return list;
	}

	@Override
	public List<VideoClipBean> getAllVideoClip() throws Exception {
		String hql = "from VideoClipBean video where video.ismonitor = false order by video.id";
		List<VideoClipBean> list = queryByHql(hql);
		return list;
	}
	
}
