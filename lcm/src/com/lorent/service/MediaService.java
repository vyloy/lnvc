package com.lorent.service;

import java.util.List;

import com.lorent.dao.MediaDao;
import com.lorent.model.MediaBean;



public interface MediaService extends IGenericService<MediaDao,MediaBean, Integer> 
{

	public boolean createMedia(MediaBean media) throws Exception;

	public boolean renewMedia(MediaBean media) throws Exception;

	public boolean removeMedia(List<MediaBean> medias)throws Exception;
}
