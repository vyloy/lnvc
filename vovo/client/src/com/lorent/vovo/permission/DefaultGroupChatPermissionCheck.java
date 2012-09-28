package com.lorent.vovo.permission;

import java.util.List;
import java.util.Map;

import com.lorent.common.permission.NoPermissionException;
import com.lorent.common.permission.PermissionCheckInter;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.VovoStringUtil;

public class DefaultGroupChatPermissionCheck implements PermissionCheckInter {

	@Override
	public void docheck(Object... paras) throws NoPermissionException {
		Map<String,List<String>> authorityMap = DataUtil.getValue(Constants.DataKey.groupChatAuthority);
		String roomJid = (String)paras[0];
		String errorMsg = (String)paras[1];
		String flagCharacter = (String)paras[2];
		List<String> list = authorityMap.get(roomJid);
		if(list==null){
			throw new NoPermissionException(errorMsg);
		}else{
			if(!list.contains(flagCharacter)){
				throw new NoPermissionException(errorMsg);
			}
		}
	}

}
