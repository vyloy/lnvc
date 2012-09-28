package com.lorent.whiteboard.command;

import java.io.Serializable;

import org.apache.mina.core.session.IoSession;

public interface Command extends Serializable{
	void execute(IoSession session);

}
