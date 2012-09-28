package com.lorent.util;

public class Counter {
	private int num = 0;
	
	public Counter(int initNum){
		num = initNum;
	}
	
	public void add(){
		num = num  + 1;
	}
	
	public int getNum(){
		return num;
	}
	
	public void reset(){
		num = 0;
	}
	
	@Override
	public String toString() {
		return num + "";
	}
}
