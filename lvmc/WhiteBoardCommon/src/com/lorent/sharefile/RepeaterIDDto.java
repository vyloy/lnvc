package com.lorent.sharefile;

import java.util.ArrayList;

public class RepeaterIDDto {
	private static ArrayList<String> repeateridlist = new ArrayList<String>();
	
	public synchronized static int getRepeaterID(String key){
		
		if (repeateridlist.contains(key)) {
			return repeateridlist.indexOf(key)+15000;
		}
		else{
			repeateridlist.add(key);
			return repeateridlist.indexOf(key)+15000;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(RepeaterIDDto.getRepeaterID("21811-21069"));
		System.out.println(RepeaterIDDto.getRepeaterID("21811-21068"));
		System.out.println(RepeaterIDDto.getRepeaterID("21811-21069"));
		System.out.println(RepeaterIDDto.getRepeaterID("21811-21068"));
		System.out.println(RepeaterIDDto.getRepeaterID("21811-21069"));
		System.out.println(RepeaterIDDto.getRepeaterID("21222"));
	}
}
