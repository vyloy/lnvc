package org.jivesoftware.openfire.plugin.conferenceVote.util;

import java.text.ParseException;
import java.util.Date;

public class DateUtil {

	public static Date getDate(String dateStr,String pattern) throws ParseException{
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(pattern);
		Date date = dateFormat.parse(dateStr);
		return date;
	}
	
	public static String getDateStr(Date date,String pattern){
		if(date == null){
			return "";
		}
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
}
