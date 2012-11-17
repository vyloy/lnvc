/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lorent.common.dto.LCMRoleDto;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.LCMUtil;
import com.lorent.lvmc.util.LvmcOpenfireUtil;
import com.lorent.lvmc.util.LvmcUtil;

/**
 *
 * @author jack
 */
public class ConfService extends BaseService{
    
    public List<MemberDto> getMemberList()throws Exception{
        if(LvmcUtil.isUCSAPP() || LvmcUtil.isVOVOAPP()){
        	FetchMemberInfoService fetchMemberInfoService = new FetchMemberInfoService();
        	List<MemberDto> ret = new ArrayList<MemberDto>();
        	Object object = ControllerFacade.execute("phoneController", "getMemberinfomap");
        	System.out.println("object is " + object);
        	if(object==null){
        		return null;
        	}
        	Map<String, String[]> memberinfomap = (Map<String, String[]>) object;
        	if(memberinfomap.size()>0){
        		Set<String> set = memberinfomap.keySet();
        		for(String key:set){
        			if(DataUtil.getLoginInfo()!=null && key.equals(DataUtil.getLoginInfo().getUsername())){
        				continue;
        			}
        			MemberDto temp = fetchMemberInfoService.getMemberDtoByName(key);
            		ret.add(temp);
        		}
        		return ret;
        	}else{
        		return null;
        	}
        	/*Object[] obs = LCCUtil.getInstance().fetchMemberList(LvmcOpenfireUtil.getConfNo().split("@")[0]);
        	FetchMemberInfoService fetchMemberInfoService = new FetchMemberInfoService();
        	for(Object obj:obs){
        		String name = (String)obj;
        		if(DataUtil.getLoginInfo()!=null && name.equals(DataUtil.getLoginInfo().getUsername())){
        			continue;
        		}
        		MemberDto temp = fetchMemberInfoService.getMemberDtoByName(name);
        		ret.add(temp);
        	}*/
        	
        }else{
        	return getOpenfireMemberList();
        }
    }
    
    public MemberDto getMemberDtoByName(String name)throws Exception{
        MemberDto temp = new MemberDto();
        temp.setName(name);
        temp.setOnline(true);
        Map<String, LCMRoleDto> confUserRole = LCMUtil.getConfUserRole(DataUtil.getLoginInfo().getConfno(), new String[]{name});
        LCMRoleDto roleDto = confUserRole.get(name);
        temp.setRole(roleDto);
        temp.setNickname(roleDto.getNickname());
        return temp;
    }
    
    public List<MemberDto> getOpenfireMemberList()throws Exception{

        List<MemberDto> ret = new ArrayList<MemberDto>();
        List<String> currentMembers = LvmcOpenfireUtil.getCurrentMembers();
        if(DataUtil.getLoginInfo()!=null && !currentMembers.contains(DataUtil.getLoginInfo().getUsername())){
        	currentMembers.add(DataUtil.getLoginInfo().getUsername());
        }
        for(String currentMember : currentMembers){
        	MemberDto temp = null;
        	if(LvmcUtil.isUCSAPP() || LvmcUtil.isVOVOAPP()){
            	FetchMemberInfoService fetchMemberInfoService =  new FetchMemberInfoService();
        		temp = fetchMemberInfoService.getMemberDtoByName(currentMember);
        	}else{
        		temp = getMemberDtoByName(currentMember);
        	}
            ret.add(temp);
        }
        return ret;
    } 
}
