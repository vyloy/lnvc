package com.myplugin.test;

import java.io.Serializable;

public class TestObject implements Serializable{
	
	int a = 100;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
}
