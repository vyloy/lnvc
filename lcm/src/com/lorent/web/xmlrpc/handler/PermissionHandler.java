package com.lorent.web.xmlrpc.handler;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.lorent.common.dto.LCMRoleDto;
import com.lorent.common.dto.VovoMyInfo;
import com.lorent.model.UserBean;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;

public class PermissionHandler extends BaseHandler{
	
	public LCMRoleDto getMyRoleAndPermission(String confno, String lccno) throws Exception {
		return serviceFacade.getConferenceNewService().getMyRoleAndPermission(confno, lccno);
	}
	
	public Map<String, LCMRoleDto> getConfUserRole(String confno, Object[] lccnos) throws Exception {
//		HashMap<String, LCMRoleDto> hashMap = new HashMap<String, LCMRoleDto>();
//		String[] temp = StringUtil.parseObjectArrayToArray(lccnos, String.class);
//		for (String lccno : temp) {
//			LCMRoleDto myRoleAndPermission = serviceFacade.getConferenceNewService().getMyRoleAndPermission(confno, lccno);
//			hashMap.put(lccno, myRoleAndPermission);
//		}
//		return hashMap;
		String[] temp = StringUtil.parseObjectArrayToArray(lccnos, String.class);
		return serviceFacade.getConferenceNewService().getConfUserRole(confno, temp);
	}
	/**
	 * 允许白板最大数量
	 * @param confno
	 * @return
	 */
	public String getWhiteBoardNumber(String confno){
		return PropertiesUtil.getConstant("lvmc.whiteboard.maxnumber");
	}
	
	public static void main(String[] args)throws Exception {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://127.0.0.1:6090/lcm/lcmRpc"));
		config.setEnabledForExtensions(true);
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		LCMRoleDto data = (LCMRoleDto)client.execute("lcmPerm.getMyRoleAndPermission", new Object[]{"576597", "57920"});
		if(data == null){
			System.out.println("no permission");
			return;
		}
		System.out.println(data.getNames());
		for(Iterator<String> it = data.getPermissions().keySet().iterator();it.hasNext();){
			String key = it.next();
			System.out.println("key = " + key + "&value = " + data.getPermissions().get(key));
		}
	}
	
	public VovoMyInfo getVovoMyInfo(String lccno)throws Exception{
		VovoMyInfo info = serviceFacade.getUserService().getVovoMyInfo(lccno);
		return info;
	}
	
	public boolean setVovoMyInfo(VovoMyInfo info) throws Exception{
		UserBean userBean = serviceFacade.getUserService().getByLccAccount(info.getLccAccount());
		userBean.setPosition(info.getPost());
		userBean.setRealName(info.getRealName());
		userBean.setEmail(info.getEmail());
		userBean.setSign(info.getSign());
		userBean.setMobile(info.getMobile());
		userBean.setGender(info.getGender());
		if(info.getDefaultImg()!=null && info.getDefaultImg().trim().length()>3){
			userBean.setMyPic(info.getDefaultImg());
		}
		return serviceFacade.getUserService().renewUser(userBean);
	}
	
	public boolean changePassWord(String lccno,String oldPass, String newPass,
			String repeatPass) throws Exception{
		UserBean user = serviceFacade.getUserService().getByLccAccount(lccno);
		return serviceFacade.getUserService().changePassword(user, oldPass, newPass, repeatPass);
	}
}
