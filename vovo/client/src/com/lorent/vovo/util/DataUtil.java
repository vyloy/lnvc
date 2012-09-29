package com.lorent.vovo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smackx.filetransfer.FileTransfer;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.lorent.common.dto.VovoMyInfo;
import com.lorent.common.manager.DataManager;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.PrivateData;
import com.lorent.vovo.ui.GroupChatSetFrame;
import com.lorent.vovo.ui.GroupMemberListPanel;
import com.lorent.vovo.util.Constants.DataKey;

public class DataUtil {

	private final static DataManager dataManager = Vovo.getMyContext().getDataManager();
	
	public static <T> T getValue(Constants.DataKey key) {
        return (T)dataManager.getValue(key.toString());
    }
    
    public static <T> T removeElement(Constants.DataKey key){
        return (T)dataManager.removeByKey(key.toString());
    }
    
    public static void clear(){
    	dataManager.clear();
    }
    
    public static void setValue(Constants.DataKey key, Object value){
    	dataManager.setValue(key.toString(), value);
    }
	
    
    public static void main(String args[]){
    	System.out.println(Constants.DataKey.userName.toString());
    }
    
    public static String getUserName(){
    	return getValue(Constants.DataKey.userName);
    }
    
//    
//    public static Boolean getShowExitMenuItem(){
//        return getValue(Key.showExitMenuItem);
//    }
//    
//    public static ApplicationContext getApplicationContext(){
//        return (ApplicationContext)getValue(Key.ApplicationContext);
//    }
//    
//    public static void setApplicationContext(ApplicationContext context){
//        setValue(Key.ApplicationContext, context);
//    }
//    
//    public static LoginInfo getLoginInfo(){
//        return (LoginInfo)getValue(Key.LoginInfo);
//    }
//    
//    
//    
//    public static String getUniqueFileID() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
//        String uniqueFileID = format.format(new Date());
//        return uniqueFileID;
//    }
//    
//    public static Component getTopWindow(){
//        return (Component)getValue(Key.TopWindow);
//    }
//    
//    public static Boolean getShowFlag(){
//        return (Boolean)getValue(Key.showFlag);
//    }
//    
//    public static Boolean getLccRegisterFlag(){
//        return (Boolean)getValue(Key.LccRegisterFlag);        
//    }
//    
//    public static LvmcJNIListener getLvmcJNIListener(){
//        return (LvmcJNIListener)getValue(Key.LvmcJNIListener);  
//    }
//    
//    public static VideoViewsPanelItem getPrimitiveVideoPanel(){
//        return getValue(Key.primitiveVideoPanel);
//    }
//    
//    public static String getVideoLayout(){
//        return getValue(Key.videoLayout);
//    }   
//    
//    public static Chat getPrivateChat(String key){
//    	Map<String, Chat> map = getValue(Key.privateChats);
//    	if(map == null){
//    		return null;
//    	}
//    	return map.get(key);
//    }
//    
    public static MultiUserChat removeGroupChat(String key){
    	MultiUserChat muc = null;
    	Map<String, MultiUserChat> map = getValue(Constants.DataKey.groupChat);
    	if(map!=null){
    		muc = map.remove(key);
    	}
    	return muc;
    }
    
    public static void addGroupChat(String key, MultiUserChat muc){
    	Map<String, MultiUserChat> map = getValue(Constants.DataKey.groupChat);
    	if(map == null){
    		map = new HashMap<String, MultiUserChat>();
    		setValue(Constants.DataKey.groupChat, map);
    	}
		map.put(key, muc);
    }
    
    public static MultiUserChat getGroupChat(String key){
    	Map<String, MultiUserChat> map = getValue(Constants.DataKey.groupChat);
    	if(map == null){
    		return null;
    	}
    	return map.get(key);
    }
    
    public static GroupMemberListPanel removeGroupMemberListPanel(String key){
    	GroupMemberListPanel panel = null;
    	Map<String, GroupMemberListPanel> map = getValue(Constants.DataKey.GroupMemberListPanel);
    	if(map != null){
    		panel = map.remove(key);
    	}
    	return panel;
    }
    
    public static void addGroupMemberListPanel(String key,GroupMemberListPanel panel){
    	Map<String, GroupMemberListPanel> map = getValue(Constants.DataKey.GroupMemberListPanel);
    	if(map == null){
    		map = new HashMap<String, GroupMemberListPanel>();
    		setValue(Constants.DataKey.GroupMemberListPanel, map);
    	}
		map.put(key, panel);
    }
	
    public static GroupMemberListPanel getGroupMemberListPanel(String key){
    	Map<String, GroupMemberListPanel> map = getValue(Constants.DataKey.GroupMemberListPanel);
    	if(map == null){
    		return null;
    	}
    	return map.get(key);
    }
    
    public static void addGroupChatSetFrame(String key,GroupChatSetFrame frame){
    	Map<String, GroupChatSetFrame> map = getValue(Constants.DataKey.GroupChatSetFrame);
    	if(map == null){
    		map = new HashMap<String, GroupChatSetFrame>();
    		setValue(Constants.DataKey.GroupChatSetFrame, map);
    	}
    	map.put(key, frame);
    }
    
    public static GroupChatSetFrame getGroupChatSetFrame(String key){
    	Map<String, GroupChatSetFrame> map = getValue(Constants.DataKey.GroupChatSetFrame);
    	if(map == null){
    		return null;
    	}
    	return map.get(key);
    }
    
    public static Chat getFriendChat(String lccno){
    	Map<String, Chat> chats = getValue(DataKey.FRIEND_CHAT);
    	return chats.get(lccno);
    }
    
    public static void addFriendChat(String lccno, Chat chat){
    	Map<String, Chat> chats = getValue(DataKey.FRIEND_CHAT);
    	chats.put(lccno, chat);
    }
    
    public static VovoMyInfo getMyInfo(){
    	return getValue(Constants.DataKey.MYINFO);
    }
    
    public static FileTransfer getFileTransfer(String streamId){
    	Map<String, FileTransfer> map = getValue(DataKey.FileTransfer);
    	return map.get(streamId);
    }
    
    public static void addFileTransfer(String streamId, FileTransfer f){
    	Map<String, FileTransfer> map = getValue(DataKey.FileTransfer);
    	map.put(streamId, f);
    }
    
    public static void removeFileTransfer(String streamId){
    	Map<String, FileTransfer> map = getValue(DataKey.FileTransfer);
    	map.remove(streamId);
    }
    
    public static FileTransferRequest getFileTransferRequest(String streamId){
    	Map<String, FileTransferRequest> map = getValue(DataKey.FileTransferRequest);
    	return map.get(streamId);
    }
    
    public static void addFileTransferRequest(String streamId, FileTransferRequest f){
    	Map<String, FileTransferRequest> map = getValue(DataKey.FileTransferRequest);
    	map.put(streamId, f);
    }
    
    public static void removeFileTransferRequest(String streamID){
    	Map<String, FileTransferRequest> map = getValue(DataKey.FileTransferRequest);
    	map.remove(streamID);
    }
    
    public static List<Object[]> getNotReadMsg(String key){
    	Map<String, List<Object[]>> map = getValue(DataKey.NotReadMsg);
    	return map.get(key);
    }
    
    public static void addNotReadMsg(String key, Object[] paras){
    	Map<String, List<Object[]>> map = getValue(DataKey.NotReadMsg);
    	List<Object[]> msgs = map.get(key);
    	if(msgs == null){
    		msgs = new ArrayList<Object[]>();
    		map.put(key, msgs);
    	}
    	msgs.add(paras);
    }
    
    public static void removeNotReadMsg(String key){
    	Map<String, List<Object[]>> map = getValue(DataKey.NotReadMsg);
    	map.remove(key);
    }
    
    public static PrivateData getPrivateData(){
    	return (PrivateData)getValue(Constants.DataKey.PrivateData);
    }
    
}
