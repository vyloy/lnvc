package com.lorent.client.model;

public interface MemberList {
	void execute(MemberListUpdater updater);
	void here();
	void refresh();
	void notHere();
	void handsUp(MemberListItem item);
	MemberListItem getMemberListItemByName(String name);
}
