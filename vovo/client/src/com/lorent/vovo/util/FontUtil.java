package com.lorent.vovo.util;

import java.awt.Color;

import com.lorent.vovo.dto.FontStyle;

public class FontUtil {
	private static FontStyle myNameStyle;
	private static FontStyle myDateStyle;
	private static FontStyle friendNameStyle;
	private static FontStyle friendDateStyle;
	private static FontStyle noticeStyle;
	
	public static FontStyle getMyNameStyle() {
		if(myNameStyle == null){
			myNameStyle = new FontStyle();
			myNameStyle.setColor(new Color(0, 80, 40));
			myNameStyle.setSize(12);
		}
		return myNameStyle;
	}
	public static FontStyle getFriendNameStyle() {
		if(friendNameStyle == null){
			friendNameStyle = new FontStyle();
			friendNameStyle.setColor(Color.BLUE);
			friendNameStyle.setSize(12);
		}
		return friendNameStyle;
	}
	public static FontStyle getMyDateStyle() {
		if(myDateStyle == null){
			myDateStyle = new FontStyle();
			myDateStyle.setColor(new Color(0, 80, 40));
			myDateStyle.setSize(10);
		}
		return myDateStyle;
	}
	public static FontStyle getFriendDateStyle() {
		if(friendDateStyle == null){
			friendDateStyle = new FontStyle();
			friendDateStyle.setColor(Color.BLUE);
			friendDateStyle.setSize(10);
		}
		return friendDateStyle;
	}
	public static FontStyle getNoticeStyle() {
		if(noticeStyle == null){
			noticeStyle = new FontStyle();
			noticeStyle.setColor(Color.GRAY);
			noticeStyle.setSize(10);
		}
		return noticeStyle;
	}
	
	
}
