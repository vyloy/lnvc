package com.lorent.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.lorent.video.MainActivity;

import android.provider.BaseColumns;

public class MonitorInfo  implements BaseColumns{

	
	public static final String COL_TITLE = "title";
	public static final String COL_MONITORURL = "monitor_url";
	public static final String TABLE_NAME = "monitorinfo";
	public static final int PAGE_SIZE = 10;
	
	public static final String TABLE_CREATE = "create table " + TABLE_NAME + " (" + _ID + " integer primary key autoincrement, "
			+ COL_TITLE + " text not null, " + COL_MONITORURL + " text not null"
			+ ");";
	
	public static void initSqls(){
		DatabaseBean.addSql(TABLE_CREATE);
		if(MainActivity.device!=MainActivity.DeviceType.STB){
			DatabaseBean.addSql("insert into monitorinfo ("+COL_TITLE+","+COL_MONITORURL+") values ('1','rtsp://10.168.130.221:554/')");
		}else{
			DatabaseBean.addSql("insert into monitorinfo ("+COL_TITLE+","+COL_MONITORURL+") values ('1','rtsp://10.168.130.222:554/')");
			DatabaseBean.addSql("insert into monitorinfo ("+COL_TITLE+","+COL_MONITORURL+") values ('2','rtsp://10.168.130.223:554/')");
		}
//		DatabaseBean.addSql("insert into monitorinfo ("+COL_TITLE+","+COL_MONITORURL+") values ('2','rtsp://10.168.130.222:554/')");
		
	}
	
	public Long id;
	public String title;
	public String monitoUrl;
	
	
	
}
