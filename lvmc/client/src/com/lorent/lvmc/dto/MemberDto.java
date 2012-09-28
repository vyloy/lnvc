/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.dto;

import com.lorent.common.dto.LCMRoleDto;

/**
 *
 * @author jack
 */
public class MemberDto {
    //成员名字
    private String name;
    //角色
    private LCMRoleDto role;
    //是否在线
    private boolean online;
    //别名
    private String nickname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public LCMRoleDto getRole() {
        return role;
    }

    public void setRole(LCMRoleDto role) {
        this.role = role;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
