/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jack
 */
public class LCMRoleDto implements Serializable{
    //角色名字
    private List<String> names = new ArrayList<String>();
    //拥有权限
    private Map<String, String> permissions = new HashMap<String, String>();
    //别名
    private String nickname;
    
    private String conferenceTitle;

    public String getConferenceTitle() {
        return conferenceTitle;
    }

    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }
    
    
    
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Map<String, String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, String> permissions) {
        this.permissions = permissions;
    }
    
    public void addPermission(String key, String value){
        this.permissions.put(key, value);
    }
    
    public void addName(String name){
        this.names.add(name);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    
}
