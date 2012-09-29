package com.lorent.vovo.iq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchGroupChatAuthorityBean extends BaseBean {

	private Map<String,List<String>> authorityMap = new HashMap<String,List<String>>();

	public Map<String, List<String>> getAuthorityMap() {
		return authorityMap;
	}

	public void setAuthorityMap(Map<String, List<String>> authorityMap) {
		this.authorityMap = authorityMap;
	}
	
	
	
}
