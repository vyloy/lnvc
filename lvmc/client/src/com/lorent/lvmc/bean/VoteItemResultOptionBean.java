/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.bean;

/**
 *
 * @author test
 */
public class VoteItemResultOptionBean {
     private String id;//表决项选项id
    private String num;//表决项投票数量
    private String percent;//表决项投票百分比

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
    
    
}
