/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author test
 */
public class VoteDataDto {
    private String themeid;
    private String theme;
    private String themeDescription;
    private String effective_time;
    private String begin_time;
    private String isStart;
    private String isClose;
    private String creator;
    private List<VoteDataChildDto> list = new ArrayList<VoteDataChildDto>();

    public String getIsClose() {
        return isClose;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    
    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEffective_time() {
        return effective_time;
    }

    public void setEffective_time(String effective_time) {
        this.effective_time = effective_time;
    }

    
    public String getThemeid() {
        return themeid;
    }

    public void setThemeid(String themeid) {
        this.themeid = themeid;
    }

    
    public List<VoteDataChildDto> getList() {
        return list;
    }

    public void setList(List<VoteDataChildDto> list) {
        this.list = list;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getThemeDescription() {
        return themeDescription;
    }

    public void setThemeDescription(String themeDescription) {
        this.themeDescription = themeDescription;
    }
    
}
