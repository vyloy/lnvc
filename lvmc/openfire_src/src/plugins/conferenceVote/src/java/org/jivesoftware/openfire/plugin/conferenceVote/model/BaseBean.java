package org.jivesoftware.openfire.plugin.conferenceVote.model;

import java.io.Serializable;

public class BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
	
}
