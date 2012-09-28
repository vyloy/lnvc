package com.lorent.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.lorent.dao.MediaDao;
import com.lorent.model.MediaBean;
import com.lorent.service.MediaService;
import com.lorent.util.Constant;

public class MediaServiceImpl extends GenericServiceImpl<MediaDao, MediaBean, Integer>
	implements MediaService {

	private static final long serialVersionUID = 1L;

	public boolean createMedia(MediaBean media) throws Exception {
		saveFile(media);
		this.save(media);
		return true;
	}

	public boolean removeMedia(List<MediaBean> medias) throws Exception {
		for(MediaBean media : medias){
			deleteFile(media);
			media.setStatus(Constant.RECORD_STATUS_DELETED);
			this.update(media);
			
		}
		return true;
	}


	public boolean renewMedia(MediaBean media) throws Exception {
		saveFile(media);
		MediaBean oldMedia = this.get(media.getId()); 
		deleteFile(oldMedia);
		this.update(media);
		return true;
	}


	private void deleteFile(MediaBean media) throws Exception {
		File oldFile = new File(media.getMediaPath());
		if(oldFile.exists()){
			oldFile.delete();
		}
	}
	
	private void saveFile(MediaBean media) throws Exception{
		FileOutputStream fos = new FileOutputStream(media.getMediaPath());
		FileInputStream fis = new FileInputStream(media.getFile());
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = fis.read(buffer))>0){
			fos.write(buffer, 0, len);
		}
		fos.flush();
		fos.close();
		fis.close();
	}

	
}
