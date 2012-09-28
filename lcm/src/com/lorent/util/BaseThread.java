package com.lorent.util;

public abstract class BaseThread implements Runnable {
	
	protected Object[] args;
	
	public BaseThread(Object... args){
		this.args = args;
	}
	
	public abstract void run();
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

}
