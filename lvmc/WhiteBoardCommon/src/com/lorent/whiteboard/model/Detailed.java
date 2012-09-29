package com.lorent.whiteboard.model;

import com.alibaba.fastjson.JSON;

public interface Detailed {
	String toDetailedString();
	JSON toJSON(boolean detail);
}
