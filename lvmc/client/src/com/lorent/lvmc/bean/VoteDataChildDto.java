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
public class VoteDataChildDto {
    private String parentid;
    private String titleid;
    private String title;
    private String titledescription;
    private String ItemType;
//    private List<String[]> list = new ArrayList<String[]>();
    private List<OptionDto[]> list = new ArrayList<OptionDto[]>();

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    
    
    public List<OptionDto[]> getList() {
        return list;
    }

    public void setList(List<OptionDto[]> list) {
        this.list = list;
    }

    public String getTitleid() {
        return titleid;
    }

    public void setTitleid(String titleid) {
        this.titleid = titleid;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String ItemType) {
        this.ItemType = ItemType;
    }

//    public List<String[]> getList() {
//        return list;
//    }
//
//    public void setList(List<String[]> list) {
//        this.list = list;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitledescription() {
        return titledescription;
    }

    public void setTitledescription(String titledescription) {
        this.titledescription = titledescription;
    }
    
    
}
