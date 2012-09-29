package com.lorent.whiteboard.client;

import com.lorent.whiteboard.model.View;

public class ViewParam {

	private static volatile View view;
	
	public static View getView(){
		return view;
	}
	
	public static void setView(View v){
		view=v;
	}
	
}
