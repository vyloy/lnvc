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
public class VoteItemResultBean {
    private String itemid;//表决项id
    private List<VoteItemResultOptionBean> OptionSelectedIdList=new ArrayList<VoteItemResultOptionBean>();

    public List<VoteItemResultOptionBean> getOptionSelectedIdList() {
        return OptionSelectedIdList;
    }

    public void setOptionSelectedIdList(List<VoteItemResultOptionBean> OptionSelectedIdList) {
        this.OptionSelectedIdList = OptionSelectedIdList;
    }



    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }
    
}
