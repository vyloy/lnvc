package com.lorent.whiteboard.server.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.model.Detailed;


public class TextSerializer {
	private static final Logger logger = LoggerFactory
			.getLogger(TextSerializer.class);

	public String serialize(Object obj){
		if(obj.getClass()==BroadcastCommand.class&&((BroadcastCommand) obj).getUpdater()instanceof Detailed){
			return ((BroadcastCommand) obj).toDetailedString();
		}
		return obj.toString();
	}
}
