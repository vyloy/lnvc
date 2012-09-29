package com.lorent.whiteboard.model;

public interface StateUpdater<T extends View> extends Updater<T>{
	int getStateType();
}
