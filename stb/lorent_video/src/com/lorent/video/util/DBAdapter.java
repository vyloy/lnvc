package com.lorent.video.util;

import com.lorent.common.dto.MonitorInfo;

import android.database.SQLException;

public interface DBAdapter {

	public DBAdapterImpl open() throws SQLException;
	public void close();
	public boolean deleteById(long id);
	public Object save(Object info);
	
}
