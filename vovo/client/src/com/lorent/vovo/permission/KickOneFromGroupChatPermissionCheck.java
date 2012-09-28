package com.lorent.vovo.permission;

import java.util.List;
import java.util.Map;

import com.lorent.common.permission.NoPermissionException;
import com.lorent.common.permission.PermissionCheckInter;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.VovoStringUtil;

public class KickOneFromGroupChatPermissionCheck extends DefaultGroupChatPermissionCheck{

	@Override
	public void docheck(Object... paras) throws NoPermissionException {
//		Map<String,List<String>> authorityMap = DataUtil.getValue(Constants.DataKey.groupChatAuthority);
//		String roomJid = (String)paras[0];
//		List<String> list = authorityMap.get(roomJid);
//		if(list==null){
//			throw new NoPermissionException(VovoStringUtil.getErrorString("NoPermission.kickOneFromGroupChat"));
//		}else{
//			if(!list.contains("kickOneFromGroupChat")){
//				throw new NoPermissionException(VovoStringUtil.getErrorString("NoPermission.kickOneFromGroupChat"));
//			}
//		}
		super.docheck(paras[0],VovoStringUtil.getErrorString("NoPermission.kickOneFromGroupChat"),Constants.GroupChatPermission.KickOneFromGroupChat.toString());
	}

}
