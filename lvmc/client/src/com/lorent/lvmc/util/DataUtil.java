/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;


import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.springframework.context.ApplicationContext;

import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.event.LvmcJNIListener;
import com.lorent.lvmc.ui.VideoViewsPanelItem;

/**
 *
 * @author jack
 */
public class DataUtil {

    private final static Map<String, Object> globalVar = new HashMap<String, Object>();
    //public static final String EVENT_LISTENER = "listener";
    //设置KEY值在枚举类型内容，这样可以避免重复的KEY值设置，从而覆盖要保存的对象
    public enum Key{
        ApplicationContext,
        Connection,
        LoginInfo,
        Room,
        ReadyToLeaveRoom,
        listener,
        recieverName,
        filters,
        myPermission,
        optionMap,
        TopWindow,
        showFlag,
        LccRegisterFlag,
        LvmcJNIListener,
        DockingLayoutMeetingPanel,
        showExitMenuItem,
        primitiveVideoPanel,
        videoLayout,
        RepeaterHost,
        Restart,
        privateChats,
        Initiative,
        ScreenShareProcess,
        AppName,
        AleardyHangup,
        IsAnswer,
        SelectAudioCodes,
        SystemParas
    }
    
    public static <T> T getValue(Key key) {
        return (T)globalVar.get(key.toString());
    }
    
    public static <T> T removeElement(Key key){
        return (T)globalVar.remove(key.toString());
    }
    
    public static Boolean getShowExitMenuItem(){
        return getValue(Key.showExitMenuItem);
    }
    
    public static void setValue(Key key, Object value){
        globalVar.put(key.toString(), value);
    }
    
    public static ApplicationContext getApplicationContext(){
        return (ApplicationContext)getValue(Key.ApplicationContext);
    }
    
    public static void setApplicationContext(ApplicationContext context){
        setValue(Key.ApplicationContext, context);
    }
    
    public static LoginInfo getLoginInfo(){
        return (LoginInfo)getValue(Key.LoginInfo);
    }
    
    public static void clear(){
        globalVar.clear();
    }
    
    public static String getUniqueFileID() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
        String uniqueFileID = format.format(new Date());
        return uniqueFileID;
    }
    
    public static Component getTopWindow(){
        return (Component)getValue(Key.TopWindow);
    }
    
    public static Boolean getShowFlag(){
        return (Boolean)getValue(Key.showFlag);
    }
    
    public static Boolean getLccRegisterFlag(){
        return (Boolean)getValue(Key.LccRegisterFlag);        
    }
    
    public static LvmcJNIListener getLvmcJNIListener(){
        return (LvmcJNIListener)getValue(Key.LvmcJNIListener);  
    }
    
    public static VideoViewsPanelItem getPrimitiveVideoPanel(){
        return getValue(Key.primitiveVideoPanel);
    }
    
    public static String getVideoLayout(){
        return getValue(Key.videoLayout);
    }   
    
    public static Chat getPrivateChat(String key){
    	Map<String, Chat> map = getValue(Key.privateChats);
    	if(map == null){
    		return null;
    	}
    	return map.get(key);
    }
    
    public static void addPrivateChat(String key, Chat chat){
    	Map<String, Chat> map = getValue(Key.privateChats);
    	if(map == null){
    		map = new HashMap<String, Chat>();
    		setValue(Key.privateChats, map);
    	}
		map.put(key, chat);
    }
    
    public static Constants.AppName getAppName(){
    	return getValue(Key.AppName);
    }
    
    public static void setAppName(Constants.AppName appName){
    	setValue(Key.AppName, appName);
    }
    
    public static String getSystemPara(String key){
    	Map<String, String> paras = getValue(Key.SystemParas);
    	if(paras == null){
    		return null;
    	}
    	return paras.get(key);
    }
}
