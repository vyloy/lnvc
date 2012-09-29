package com.lorent.whiteboard.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.lorent.whiteboard.command.impl.BroadcastResultCommand;


public interface Updater<T extends View> extends Serializable{
	void change(T view);
	BroadcastResultCommand createResultCommand(Whiteboard board,long oid,long id);
	int getType();
	JSON toJSON();
}
