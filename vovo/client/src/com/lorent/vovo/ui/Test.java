package com.lorent.vovo.ui;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Test extends Bean<java.util.List> {

	public Test(){

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}

}

class Bean<T> {
	public Bean(){
		Class<T> clazz = (Class<T>)((ParameterizedType)getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println(clazz);
	}
	
}

interface BeabInter<T>{}