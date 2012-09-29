/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lorent.common.dto.LCMRoleDto;
import com.lorent.lvmc.event.AuthorityCheckListener;
import com.lorent.lvmc.event.DeleteDocumentAuthorityCheckListener;
import com.lorent.lvmc.event.FileUploadAuthorityCheckListener;
import com.lorent.lvmc.event.InviteJoinConferenceAuthorityCheckListener;
import com.lorent.lvmc.event.KickFromConferenceAuthorityCheckListener;
import com.lorent.lvmc.event.ScreenShareAuthorityCheckListener;
import com.lorent.lvmc.event.ShareDocumentAuthorityCheckListener;
import com.lorent.lvmc.event.VoteManageAuthorityCheckListener;

/**
 *
 * @author jack
 */
public class PermissionUtil {
    
    public final static String WHITE_BOARD = "whiteboard";
    public final static String FILE_UPLOAD = "fileupload";
    public final static String SHARE_DOCUMENT = "sharedocument";
    public final static String SCREEN_SHARE = "screenshare";
    public final static String DELETE_DOCUMENT = "deletedocument";
    public final static String INVITE_JOIN_CONFERENCE = "invitejoinconference";
    public final static String KICK_FROM_CONFERENCE = "kickfromconference";
    public final static String VOTE_MANAGE = "votemanage";
    public final static String AUTHORITY_OPERATE = "authorityoperate";
    public static Map<String,AuthorityCheckListener> authroityListeners = new ConcurrentHashMap<String,AuthorityCheckListener>();
    
    static{
    	addAuthorityListeners(FILE_UPLOAD,new FileUploadAuthorityCheckListener());
    	addAuthorityListeners(SHARE_DOCUMENT,new ShareDocumentAuthorityCheckListener());
    	addAuthorityListeners(SCREEN_SHARE,new ScreenShareAuthorityCheckListener());
    	addAuthorityListeners(DELETE_DOCUMENT,new DeleteDocumentAuthorityCheckListener());
    	addAuthorityListeners(INVITE_JOIN_CONFERENCE,new InviteJoinConferenceAuthorityCheckListener());
    	addAuthorityListeners(KICK_FROM_CONFERENCE,new KickFromConferenceAuthorityCheckListener());
    	addAuthorityListeners(VOTE_MANAGE, new VoteManageAuthorityCheckListener());
    }
    
    public static boolean hasPermission(String permission){
        LCMRoleDto role = DataUtil.getValue(DataUtil.Key.myPermission);
        boolean flag = role.getPermissions().containsKey(permission);
        return flag;
    }
    
    public static boolean hasPermission(String[] permissions)throws Exception{
        if(permissions == null || permissions.length == 0){
            throw new Exception(StringUtil.getErrorString("base.argsIsEmpty"));
        }
        boolean flag = true;
        for(String permission : permissions){
            boolean temp = hasPermission(permission);
            if(!temp){
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    public static void addAuthorityListeners(String authorityName,AuthorityCheckListener listener){
    	authroityListeners.put(authorityName, listener);
    }
    
    public static AuthorityCheckListener getAuthorityCheckListener(String name){
    	return authroityListeners.get(name);
    }
    
    
}
