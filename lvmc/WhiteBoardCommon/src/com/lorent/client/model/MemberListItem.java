package com.lorent.client.model;


public interface MemberListItem {

	void here();

	boolean isHere();

	<D> D getData();

	void notHere();

	void handsUp(boolean display);

}
