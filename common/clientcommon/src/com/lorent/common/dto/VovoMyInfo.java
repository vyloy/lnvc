package com.lorent.common.dto;

import java.util.HashMap;
import java.util.Map;

import com.lorent.common.tree.MemberBean;

public class VovoMyInfo extends MemberBean{

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> permissions = new HashMap<String, String>();

	public Map<String, String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, String> permissions) {
		this.permissions = permissions;
	}
	
}
