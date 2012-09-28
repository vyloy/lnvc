package com.lorent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniqueIntIDCreator {

//	private static final List<Integer> cacheList = new ArrayList<Integer>();
	
	private static int currentId = 15000;
	
	private static final int MAX_VALUE = Integer.MAX_VALUE; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<20;i++){
			System.out.println(create());
		}
		Integer a = new Integer(2);
		Integer b = new Integer(2);
		System.out.println(a.equals(b));
	}
	
//	public static int create(){
//		Random rd = new Random();
//    	int id = rd.nextInt(Integer.MAX_VALUE);
//    	Integer itg = new Integer(id);
//    	if(cacheList.contains(itg)){
//    		return create();
//    	}else{
//    		cacheList.add(itg);
//    		return id;
//    	}
//	}
	
	public static synchronized int create(){
		if(currentId == MAX_VALUE){
			currentId = 15000;
		}else{
			currentId++;
		}
		return currentId;
	}

}
