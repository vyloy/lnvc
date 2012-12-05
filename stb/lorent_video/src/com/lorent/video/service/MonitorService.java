package com.lorent.video.service;

import java.util.List;

import android.content.Context;

import com.lorent.common.dto.MonitorInfo;
import com.lorent.video.dao.MonitorDao;
import com.lorent.video.dao.MonitorDaoImpl;
import com.lorent.video.util.DBAdapterImpl;
import com.lorent.video.util.DaoProxyFactory;

public class MonitorService {

	DBAdapterImpl dbAdpater;
	MonitorDao monitorDao;
	
	public MonitorService(Context ctx){
//		dbAdpater = new DBAdapterImpl(ctx);
		monitorDao = ((MonitorDao)DaoProxyFactory.getProxyDaoObj(MonitorDaoImpl.class));
	}
	
	public List<MonitorInfo> getMonitorFromPosition(int page) throws Exception{
		return monitorDao.getMonitorFromPosition(page);//(List<MonitorInfo>)dbAdpater.getMonitorFromPosition(page);
	}
	
	public MonitorInfo saveMonitor(MonitorInfo info){
		return monitorDao.save(info);
	}
	
	public boolean deleteMonitor(Long id){
		return monitorDao.deleteById(id);
	}
}
