package com.lorent.vovo.bean;

import android.graphics.Bitmap;

public class RecordBean {
	private int id;
	private String name;
	private String lccno;
	private String calltime;
	public String getCalltime() {
		return calltime;
	}
	public void setCalltime(String calltime) {
		this.calltime = calltime;
	}
	private Bitmap type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLccno() {
		return lccno;
	}
	public void setLccno(String lccno) {
		this.lccno = lccno;
	}
	public Bitmap getType() {
		return type;
	}
	public void setType(Bitmap type) {
		this.type = type;
	}
	

}
