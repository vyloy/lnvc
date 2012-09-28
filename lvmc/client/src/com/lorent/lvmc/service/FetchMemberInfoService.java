package com.lorent.lvmc.service;

import java.util.Map;

import com.lorent.common.dto.LCMRoleDto;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.LCMUtil;

public class FetchMemberInfoService {

	public MemberDto getMemberDtoByName(String name)throws Exception{
        MemberDto temp = new MemberDto();
        temp.setName(name);
        temp.setOnline(true);
        temp.setNickname(name);
        return temp;
    }
	
}
