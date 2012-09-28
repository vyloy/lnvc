package com.lorent.sharefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import com.lorent.sharefile.bean.UserInfoBean;

public class ShareFileDto {
	//key: meetingid,value: userinfos
	private static ConcurrentHashMap<String, Map<String, UserInfoBean>> meetingUserInfos = new ConcurrentHashMap<String, Map<String, UserInfoBean>>();
	
	public synchronized static void setUserInfo(String meetingID,UserInfoBean info){
		Map<String, UserInfoBean> userinfos = meetingUserInfos.get(meetingID);
		if (userinfos == null) {
			userinfos = new HashMap<String, UserInfoBean>();
			meetingUserInfos.put(meetingID, userinfos);
		}
		userinfos.put(info.getUserName(), info);
	}
	
	public synchronized static void clearUserInfo(IoSession session){
		Collection<Map<String, UserInfoBean>> values = meetingUserInfos.values();
		for (Map<String, UserInfoBean> map : values) {
			Collection<UserInfoBean> infos = map.values();
			for (UserInfoBean userInfoBean : infos) {
				if (userInfoBean.getAliveSession().equals(session)) {
					String userName = userInfoBean.getUserName();
					map.put(userName, null);
				}
			}
		}
	}
	
	public static ArrayList<UserInfoBean> getUserInfos(String meetingID){
		ArrayList<UserInfoBean> resultList = new ArrayList<UserInfoBean>();
		Map<String, UserInfoBean> map = meetingUserInfos.get(meetingID);
		if (map != null) {
			Collection<UserInfoBean> values = map.values();
			Object[] array = values.toArray();
			for (Object object : array) {
				resultList.add((UserInfoBean) object);
			}
		}
		return resultList;
	}
	
	public static UserInfoBean getUserInfo(String meetingID,String username){
		Map<String, UserInfoBean> map = meetingUserInfos.get(meetingID);
		if (map != null) {
			return map.get(username);
		}
		return null;
	}
	
}
