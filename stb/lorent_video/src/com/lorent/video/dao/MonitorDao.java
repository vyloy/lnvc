package com.lorent.video.dao;

import java.util.List;

import com.lorent.common.dto.MonitorInfo;

public interface MonitorDao {

	public MonitorInfo save(MonitorInfo info);
	
	public boolean deleteById(long id);
	
	public List<MonitorInfo> getMonitorFromPosition(int startp);
	
}
