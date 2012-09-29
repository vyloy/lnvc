package com.lorent.vovo.iq;

import java.util.ArrayList;
import java.util.List;

public class GroupChatJidBean extends BaseBean{

	
	private List<String> jids = new ArrayList<String>();
	
	
	public List<String> getJids() {
		return jids;
	}
	public void setJids(List<String> jids) {
		this.jids = jids;
	}
	
	
}
