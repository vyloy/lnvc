package com.lorent.lvmc.event;

import java.util.EventListener;

public interface BaseListener extends EventListener{
	public void action(int type, Object[] paras);
}
