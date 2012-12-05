package com.lorent.video.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.lorent.common.dto.MonitorInfo;
import com.lorent.video.util.DBAdapterImpl;

public class MonitorDaoImpl extends BaseDao implements MonitorDao {

	public MonitorInfo save(MonitorInfo info){
		ContentValues initialValues = new ContentValues();
		initialValues.put(MonitorInfo.COL_TITLE, info.title);
		initialValues.put(MonitorInfo.COL_MONITORURL, info.monitoUrl);
		info.id = db.insert(MonitorInfo.TABLE_NAME, null, initialValues);
		return info;
	}
	
	public boolean deleteById(long id){
		boolean flag = db.delete(MonitorInfo.TABLE_NAME, MonitorInfo._ID + "=" + id, null) > 0;
		return flag;
	}
	
	public List<MonitorInfo> getMonitorFromPosition(int startp) {
		List<MonitorInfo> infos = new ArrayList<MonitorInfo>();
		String sql= "select * from " + MonitorInfo.TABLE_NAME 
		+ " limit "+String.valueOf(DBAdapterImpl.PAGE_SIZE)+ " offset " +String.valueOf(startp);    
        Cursor rec = db.rawQuery(sql, null);
        while(rec.moveToNext()){
        	MonitorInfo info = new MonitorInfo();
        	info.id = rec.getLong(rec.getColumnIndex(MonitorInfo._ID));
        	info.title = rec.getString(rec.getColumnIndex(MonitorInfo.COL_TITLE));
        	info.monitoUrl = rec.getString(rec.getColumnIndex(MonitorInfo.COL_MONITORURL));
        	infos.add(info);
        }
        rec.close();
        return infos;
	}
	
}
