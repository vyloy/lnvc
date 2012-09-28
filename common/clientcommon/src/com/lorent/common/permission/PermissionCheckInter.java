package com.lorent.common.permission;

public interface PermissionCheckInter {
	void docheck(Object... paras)throws NoPermissionException;
}
