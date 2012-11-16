package com.qd.tool;

import java.io.UnsupportedEncodingException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class DBFactory
{	
	public static void insertData(Context context, Uri uri1,Uri uri2)
	{
		context.getContentResolver().delete(uri2, null, null);
		String sql = "ServiceType in(?,?)";
		Cursor Mycursor = context.getContentResolver().query(uri1, new String[]{"ServiceId","ServiceType","ServiceName"}, sql, new String[]{"1","2"}, null);
		
		int count=1;
		int sid = 0;
		int serviceType =0;
		String serviceName = "";
		String logicNum ="001";
			
		while(Mycursor.moveToNext())
		{
			
			sid = Mycursor.getInt(Mycursor.getColumnIndex("ServiceId"));
			serviceType = Mycursor.getInt(Mycursor.getColumnIndex("ServiceType"));
			
			byte[] s = Mycursor.getBlob(2);
			try
			{
				serviceName = new String(s,"utf8");/////////////////new String(s,"gb2312");
			}
			catch (UnsupportedEncodingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ContentValues values = new ContentValues();
			values.put("MyLogicNum", logicNum);
			values.put("MyServiceId", sid);
			values.put("MyServiceType", serviceType);
			values.put("MyServiceName", serviceName);
			context.getContentResolver().insert(uri2, values);
			
			count++;
			logicNum = count+"";
			if(logicNum.length()==1 )
			{
				logicNum = "00"+logicNum;
			}
			
			else if(logicNum.length() == 2)
			{
				logicNum = "0"+logicNum;
			}
			
		}
		
		Mycursor.close();
	}

}
