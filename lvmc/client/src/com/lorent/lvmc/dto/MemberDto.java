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
/**
 * @author Jack
 *
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
    //开启音频
    private boolean enableAudio;
    //开启视频    
    private boolean enableVideo;

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

	public boolean isEnableAudio() {
		return enableAudio;
	}

	public void setEnableAudio(boolean enableAudio) {
		this.enableAudio = enableAudio;
	}

	public boolean isEnableVideo() {
		return enableVideo;
	}

	public void setEnableVideo(boolean enableVideo) {
		this.enableVideo = enableVideo;
	}
    
    
}
