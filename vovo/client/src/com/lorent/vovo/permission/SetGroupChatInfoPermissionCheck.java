package com.lorent.vovo.permission;

import com.lorent.common.permission.NoPermissionException;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.VovoStringUtil;

public class SetGroupChatInfoPermissionCheck extends DefaultGroupChatPermissionCheck{

	@Override
	public void docheck(Object... paras) throws NoPermissionException {
		super.docheck(paras[0],VovoStringUtil.getErrorString("NoPermission.setGroupChatInfo"),Constants.GroupChatPermission.SetGroupChatInfo.toString());
	}
}
