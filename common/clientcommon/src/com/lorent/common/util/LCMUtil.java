/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.lorent.common.dto.LCMConferenceDto;
import com.lorent.common.dto.LCMConferenceRoleBean;
import com.lorent.common.dto.LCMConferenceTypeBean;
import com.lorent.common.dto.LCMRoleDto;
import com.lorent.common.dto.LCMVideoClip;
import com.lorent.common.dto.VovoMyInfo;
import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.tree.OrgTree;

/**
 *
 * @author jack
 */
public class LCMUtil {
    
	private String xmlrpcUrl;
    private XmlRpcClient client;
    private Logger log = Logger.getLogger(LCMUtil.class);
    
    private LCMUtil(String xmlrpcUrl) throws Exception{
    	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    	this.xmlrpcUrl = xmlrpcUrl;
        config.setServerURL(new URL(xmlrpcUrl));
        config.setEnabledForExtensions(true);
        config.setReplyTimeout(20 * 1000);
        client = new XmlRpcClient();
        client.setConfig(config);
    }
    
    public OrgTree<DepartmentBean,MemberBean> getOrgTree() throws Exception{
    	OrgTree<DepartmentBean,MemberBean> tree = (OrgTree<DepartmentBean,MemberBean>)client.execute("lcmOrgan.getOrganTree",new Object[]{});
    	return tree;
    }
    
    public static LCMUtil newInstance(String xmlrpcUrl) throws Exception{
    	return new LCMUtil(xmlrpcUrl);
    }
    
    public LCMRoleDto getMyRoleAndPermission(String confno, String lccno)throws Exception{
        LCMRoleDto data = (LCMRoleDto)client.execute("lcmPerm.getMyRoleAndPermission", new Object[]{confno, lccno});
        return data;
    }
    
    public Map<String, LCMRoleDto> getConfUserRole(String confno, String[] lccnos) throws Exception {
        Map<String, LCMRoleDto> ret = (Map<String, LCMRoleDto>)client.execute("lcmPerm.getConfUserRole", new Object[]{confno, lccnos});
        return ret;
    }
    
    public String getWhiteBoardNumber(String confno) throws Exception {
        String snum=(String) client.execute("lcmPerm.getWhiteBoardNumber",new Object[]{confno});
        return snum;
    }
    
    public Map<String, LCMConferenceDto> getConfList() throws Exception{
    	Map<String, LCMConferenceDto> ret = (Map<String, LCMConferenceDto>)client.execute("lcmConf.getConfList",new Object[]{});
    	return ret;
    }
    
    public String createConference(String lccno,String confName,String confPsw,Integer defaultRoleId,Integer confTypeId,Integer needApply,String topic,String description,String[] urIds) throws Exception{
    	return (String) client.execute("lcmConf.createConference",new Object[]{lccno,confName,confPsw,defaultRoleId,confTypeId,needApply,topic,description,urIds});
    }
    
    public boolean modifyConference(Integer id,String lccno,String confNo,String confName,String confPsw,Integer defaultRoleId,Integer confTypeId,Integer needApply,String topic,String description,String[] urIds) throws Exception{
    	return (Boolean)client.execute("lcmConf.modifyConference",new Object[]{id,lccno,confNo,confName,confPsw,defaultRoleId,confTypeId,needApply,topic,description,urIds});
    }
    
    public Map<String, Object> getConfDefaultOptions() throws Exception{
    	Map<String, Object> ret = (Map<String, Object>)client.execute("lcmConf.getConfDefaultOptions",new Object[]{});
    	return ret;
    }
    
    public boolean deleteConference(String lccno,String confNo) throws Exception{
    	return (Boolean)client.execute("lcmConf.deleteConference",new Object[]{lccno,confNo});
    }
    
    public boolean sendConferenceNotice(String lccno,String confNo,String[] usernames,boolean vovoNotice,boolean smsNotice,boolean emailNotice) throws Exception{
    	return (Boolean) client.execute("lcmConf.sendConferenceNotice",new Object[]{lccno,confNo,usernames,vovoNotice,smsNotice,emailNotice});
    }
    
    public VovoMyInfo getVovoMyInfo(String lccno)throws Exception{
    	return (VovoMyInfo)client.execute("lcmPerm.getVovoMyInfo",new Object[]{lccno});
    }
    
    public boolean changePassWord(String lccno,String oldPass, String newPass,
			String repeatPass) throws Exception{
    	return (Boolean) client.execute("lcmPerm.changePassWord",new Object[]{lccno,oldPass,newPass,repeatPass});
    }
    
    public boolean setVovoMyInfo(VovoMyInfo info) throws Exception{
    	return (Boolean) client.execute("lcmPerm.setVovoMyInfo",new Object[]{info});
    }
    
    public boolean canCreateConf(String lccno)throws XmlRpcException{
    	return (Boolean)client.execute("lcmConf.canCreateConf", new Object[]{lccno});
    }
    
    public int getConfUserNum()throws Exception{
    	return (Integer)client.execute("lcmConf.getConfUserNum", new Object[]{});
    }
    
    public long getSystemTime()throws Exception{
    	return (Long)client.execute("lcmConf.getSystemTime", new Object[]{});
    }
    
    public boolean createUCSConf(String lccno, String confno)throws Exception{
    	return (Boolean)client.execute("lcmConf.createUCSConf", new Object[]{lccno, confno});
    }
    
    public boolean removeUCSConf(String lccno, String confno)throws Exception{
    	return (Boolean)client.execute("lcmConf.removeUCSConf", new Object[]{lccno, confno});
    }
    
    public boolean inviteUserFromMcu(String confNo,String siplccno) throws Exception{
    	return (Boolean)client.execute("lcmConf.inviteUserFromMcu", new Object[]{confNo,siplccno});
    }
    
    public boolean removeUserFromMcu(String confNo,String lccno) throws Exception{
    	log.info("removeUserFromMcu confNo = " + confNo + " & lccno = " + lccno);
    	return (Boolean)client.execute("lcmConf.removeUserFromMcu", new Object[]{confNo,lccno});
    }
    
    public boolean grantConfAuthority(String confNo,String lccno,String roleName) throws Exception{
    	return (Boolean)client.execute("lcmConf.grantConfAuthority", new Object[]{confNo,lccno,roleName});
    }
    
    public boolean revokeConfAuthority(String confNo,String lccno,String roleName) throws Exception{
    	return (Boolean)client.execute("lcmConf.revokeConfAuthority", new Object[]{confNo,lccno,roleName});
    }
    
    public Object[] getUCSConf()throws Exception{
    	return (Object[])client.execute("lcmConf.getUCSConf", new Object[]{});
    }
    
    public boolean uploadVideoClipInfo(String videoClipNameHyper,String videoClipNameHigh,String videoClipNameStandard,String thumbnailName,String title,String description,String ftpSrvIp,String createrName,String createrNo,String duration,String category) throws Exception{
    	return (Boolean)client.execute("lcmVideo.uploadVideoClipInfo",new Object[]{videoClipNameHyper,videoClipNameHigh,videoClipNameStandard,thumbnailName,title,description,ftpSrvIp,createrName,createrNo,duration,category});
    }
    
    public boolean uploadMonitorInfo(String liveStreamUrl,String thumbnailFtpUrl,String title,String description,String ftpSrvIp,String createrName,String createrNo)throws Exception{
    	return (Boolean)client.execute("lcmVideo.uploadMonitorInfo",new Object[]{liveStreamUrl,thumbnailFtpUrl,title,description,ftpSrvIp,createrName,createrNo});
    }
    
    public int getVideoListLength() throws Exception{
    	return (Integer)client.execute("lcmVideo.getVideoListLength",new Object[]{});
    }
    
    public int getVideoListLength(String category) throws Exception{
    	return (Integer)client.execute("lcmVideo.getVideoListLength",new Object[]{category});
    }
    
    public int getMonitorListLength() throws Exception{
    	return (Integer)client.execute("lcmVideo.getMonitorListLength",new Object[]{});
    }
    
    public boolean deleteVideoClip(int videoClipId) throws Exception{
    	return (Boolean)client.execute("lcmVideo.deleteVideoClip",new Object[]{videoClipId});
    }
    

    public boolean enableConfUserVideo(String confno, String lccno, boolean enable)throws Exception{
    	return (Boolean)client.execute("lcmConf.enableConfUserVideo",new Object[]{confno, lccno, enable});
    }
    
    public boolean enableConfUserAudio(String confno, String lccno, boolean enable)throws Exception{
    	return (Boolean)client.execute("lcmConf.enableConfUserAudio",new Object[]{confno, lccno, enable});
    }
    
    //MemberBean 包含ip地址的bean；fromlccno 发送者；tolccno 当为""发送全体，也可指定号码
    public boolean broadcastMyIpAddress(MemberBean bean,String fromlccno,String tolccno) throws Exception{
    	return (Boolean)client.execute("lcmVideo.broadcastMyIpAddress",new Object[]{bean,fromlccno,tolccno});
    }
    
    //index由0开始
    //获得点播视频列表，index由0开始
    public LCMVideoClip[] getVideoClipList(Integer pageIndex,Integer pageSize) throws Exception{
    	Object obj = (Object)client.execute("lcmVideo.getVideoClipList",new Object[]{pageIndex,pageSize});
    	if (obj != null) {
			Object[] lcmVideoClipbjects = (Object[]) obj;
			LCMVideoClip[] lcmVideoClips = new LCMVideoClip[lcmVideoClipbjects.length];
    		for (int i = 0; i < lcmVideoClipbjects.length; i++) {
    			lcmVideoClips[i] = (LCMVideoClip) lcmVideoClipbjects[i];
			}
    		return lcmVideoClips;
		}
    	else{
    		return null;
    	}
    }
    
    public LCMVideoClip[] getVideoClipList(Integer pageIndex,Integer pageSize,String category) throws Exception{
    	Object obj = (Object)client.execute("lcmVideo.getVideoClipList",new Object[]{pageIndex,pageSize,category});
    	if (obj != null) {
			Object[] lcmVideoClipbjects = (Object[]) obj;
			LCMVideoClip[] lcmVideoClips = new LCMVideoClip[lcmVideoClipbjects.length];
    		for (int i = 0; i < lcmVideoClipbjects.length; i++) {
    			lcmVideoClips[i] = (LCMVideoClip) lcmVideoClipbjects[i];
			}
    		return lcmVideoClips;
		}
    	else{
    		return null;
    	}
    }
    
    //获得监控视频列表，index由0开始
    public LCMVideoClip[] getMonitorList(Integer pageIndex,Integer pageSize) throws Exception{
    	Object obj = (Object)client.execute("lcmVideo.getMonitorList",new Object[]{pageIndex,pageSize});
    	if (obj != null) {
			Object[] lcmVideoClipbjects = (Object[]) obj;
			LCMVideoClip[] lcmVideoClips = new LCMVideoClip[lcmVideoClipbjects.length];
    		for (int i = 0; i < lcmVideoClipbjects.length; i++) {
    			lcmVideoClips[i] = (LCMVideoClip) lcmVideoClipbjects[i];
			}
    		return lcmVideoClips;
		}
    	else{
    		return null;
    	}
    }
    
	/**
	 * 
	 * @param users 每一个Object是String[],其中Str[0]=username,Str[1]=realname,Str[2]=lccno,Str[3]=passwd
	 * @return
	 */
    public void addOrUpdateUCSUser(Object[] users)throws Exception{
    	client.execute("lcmUser.addOrUpdateUCSUser", new Object[]{users});
    }
    
    public static void main(String[] args) throws Exception{
        String xmlurl = "http://10.168.250.12:6090/lcm/lcmRpc";
        LCMUtil lcm = LCMUtil.newInstance(xmlurl);
        int x = 0;
        int y = 6;
        LCMVideoClip[] videoClipList = lcm.getVideoClipList(x, y);
        for (LCMVideoClip lcmVideoClip : videoClipList) {
			System.out.println("全部: " +lcmVideoClip.getId()+","+lcmVideoClip.getHttpVideoUrlHigh()+","+lcmVideoClip.getThumbnailUrl());
		}
        System.out.println("length: "+lcm.getVideoListLength());
       
        LCMVideoClip[] monitorList = lcm.getMonitorList(x, y);
        for (LCMVideoClip lcmVideoClip : monitorList) {
			System.out.println(lcmVideoClip.getId()+","+lcmVideoClip.getHttpVideoUrlHigh()+","+lcmVideoClip.getThumbnailUrl());
		}
        System.out.println("length: "+lcm.getVideoListLength());
        
       videoClipList = lcm.getVideoClipList(x, y, "电影");
       for (LCMVideoClip lcmVideoClip : videoClipList) {
			System.out.println("电影"+ lcmVideoClip.getId()+","+lcmVideoClip.getHttpVideoUrlHigh()+","+lcmVideoClip.getThumbnailUrl());
       }
       System.out.println("电影 length: "+lcm.getVideoListLength("电影"));
//		lcm.uploadMonitorInfo("url123", "ftp://xxxx", "biaoti", "miaoshu", "10.168.250.12", "createname", "33013");
		
//        lcm.deleteVideoClip(3);
	}
    
    public static void main2(String[] args)throws Exception {
//        String m=LCMUtil.newInstance("http://127.0.0.1:6090/lcm/lcmRpc").getWhiteBoardNumber("416997");
//        LCMRoleDto ms=LCMUtil.getMyRoleAndPermission("416997","41742");
//        System.out.println("================"+ms);
//        System.out.println("================"+m);
        
        String xmlurl = "http://10.168.250.12:6090/lcm/lcmRpc";
        LCMUtil newInstance = LCMUtil.newInstance(xmlurl);
        Map<String, LCMConferenceDto> confList = newInstance.getConfList();
        Collection<LCMConferenceDto> values = confList.values();
        for (LCMConferenceDto lcmConferenceDto : values) {
			System.out.println(lcmConferenceDto.getId()+" "+lcmConferenceDto.getConfNo()+
					" "+lcmConferenceDto.getConferenceName()+
					","+lcmConferenceDto.getTopic()+","+lcmConferenceDto.getDescription());
		}
        
//        String createConference = newInstance.createConference("13705","LcmUtil测试会议1","密码",3,1,0,"topic","description",new String[]{""});
//        System.out.println(createConference);
        
//        boolean modifyConference = newInstance.modifyConference(28,"10000","85696725","11111","密码",3,1,0,"topic1","description1");
//        System.out.println(modifyConference);
        
        Map<String, Object> confDefaultOptions = newInstance.getConfDefaultOptions();
        System.out.println(confDefaultOptions);
//        ArrayList list_role = (ArrayList) confDefaultOptions.get("conference_type");
        Map<Integer, LCMConferenceTypeBean> beanMap = (Map<Integer, LCMConferenceTypeBean>) confDefaultOptions.get("conference_type");
        Collection<LCMConferenceTypeBean> values2 = beanMap.values();
        for (LCMConferenceTypeBean lcmConferenceTypeBean : values2) {
			System.out.println(lcmConferenceTypeBean.getId()+","+lcmConferenceTypeBean.getTypeName());
		}
        
        Map<Integer, LCMConferenceRoleBean> beanMap2 = (Map<Integer, LCMConferenceRoleBean>) confDefaultOptions.get("conference_role");
        Collection<LCMConferenceRoleBean> values3 = beanMap2.values();
        for (LCMConferenceRoleBean lcmConferenceRoleBean : values3) {
			System.out.println(lcmConferenceRoleBean.getId()+","+lcmConferenceRoleBean.getRoleName());
		}
        
//        Map<String, LCMRoleDto> confUserRole = newInstance.getConfUserRole("136166", new String[]{"13705","13407","13213","13755","13808","13861","13537"});
//        Collection<LCMRoleDto> values4 = confUserRole.values();
//        for (LCMRoleDto lcmRoleDto : values4) {
//        	System.out.println(lcmRoleDto.getNickname());
//        	List<String> names = lcmRoleDto.getNames();
//        	for (String string : names) {
//				System.out.println(string);
//			}
//			
//		}
        System.out.println("go");
//        boolean inviteUserFromMcu = newInstance.inviteUserFromMcu("326579", "33039");
//        System.out.println(inviteUserFromMcu);
        
        
        boolean bflag = newInstance.removeUserFromMcu("326579", "33039");
        System.out.println(bflag);
        
        
//        LCMRoleDto myRoleAndPermission = newInstance.getMyRoleAndPermission("136757", "13705");
//        System.out.println(myRoleAndPermission);
        
//        newInstance.sendConferenceNotice("2000000","85696725", new String[]{"2000000","3000000"}, false, true, false);
//        newInstance.changePassWord("2000000", "123456", "1234567", "1234567");
        
//        VovoMyInfo vovoMyInfo = newInstance.getVovoMyInfo("2000000");
//        vovoMyInfo.setPost("我是职位");
//        vovoMyInfo.setEmail("g@163.com");
//        vovoMyInfo.setSign("我是签名");
//        vovoMyInfo.setMobile("13700000000");
//        vovoMyInfo.setGender("female");
//        newInstance.setVovoMyInfo(vovoMyInfo);
//        System.out.println(PasswordUtil.getDesString("5a+G56CBMzQ="));
        
//        System.out.println(object);
        
//        boolean deleteConference = newInstance.deleteConference("10000", "85662014");
//        System.out.println(deleteConference);
    }
}
