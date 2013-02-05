package com.lorent.whiteboard.model;

import java.awt.geom.Rectangle2D.Double;



public interface RemoteFigure{
	long getId();
	void setId(long id);
	boolean equals(long id,Class<?> clazz);
	boolean isNeedToSetId();
	Double getBounds();
}
