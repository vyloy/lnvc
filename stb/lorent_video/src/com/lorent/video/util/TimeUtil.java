package com.lorent.video.util;

public class TimeUtil {

	public static String convertMillisecond(int time){
		String str = "";
		String hh = "00";
		String mm = "00";
		String ss = "00";
		int tmp = 0;
		if(time>60*60*1000){
			hh = String.valueOf(time/(60*60*1000));
			hh = addOneZero(hh);
			tmp = time % (60*60*1000);
			if(tmp>60*1000){
				mm = String.valueOf((tmp/(60*1000)));
				mm = addOneZero(mm);
			}
			
			if(tmp>1000){
				ss = String.valueOf(tmp/1000);
				ss = addOneZero(ss);
			}
		}else if(time>60*1000){
			mm = String.valueOf((time/(60*1000)));
			mm = addOneZero(mm);
			tmp = time%(60*1000);
			if(tmp>1000){
				ss = String.valueOf(tmp/1000);
				ss = addOneZero(ss);
			}
		}else{
			if(time>1000){
				ss = String.valueOf(time/1000);
				ss = addOneZero(ss);
			}else if(time>0){
				ss = "01";
			}
		}
		str = hh + ":" + mm + ":" + ss;
		return str;
	}
	
	private static String addOneZero(String str){
		String newStr = "";
		if(str.length()<2){
			newStr = "0" + str;
		}else{
			newStr = str;
		}
		return newStr;
	}
	
	
	public static void main(String args[]){
		System.out.println(convertMillisecond(950));
	}
	
}
