package com.lorent.whiteboard.model;



public interface RemoteFigure{
	long getId();
	void setId(long id);
	boolean equals(long id,Class<?> clazz);
	boolean isNeedToSetId();
}
