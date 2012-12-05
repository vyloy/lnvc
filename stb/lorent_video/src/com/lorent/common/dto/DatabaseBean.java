package com.lorent.common.dto;

import java.util.ArrayList;
import java.util.List;

public class DatabaseBean{

	
	public static final List<String> sqlList = new ArrayList<String>();
	
	public static List<String> getSqls(){
		MonitorInfo.initSqls();
		return sqlList;
	}
	
	
	public static void addSql(String sql){
		sqlList.add(sql);
	}
	
}
