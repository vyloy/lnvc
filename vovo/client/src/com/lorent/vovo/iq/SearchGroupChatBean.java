package com.lorent.vovo.iq;

import java.util.ArrayList;
import java.util.List;

public class SearchGroupChatBean extends BaseBean{

	private List<String[]> groupChatInfos = new ArrayList<String[]>();

	public List<String[]> getGroupChatInfos() {
		return groupChatInfos;
	}

	public void setGroupChatInfos(List<String[]> groupChatInfos) {
		this.groupChatInfos = groupChatInfos;
	}
	
	public void addInfo(String[] infos){
		groupChatInfos.add(infos);
	}
}
