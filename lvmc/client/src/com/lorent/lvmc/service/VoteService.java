/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.service;

import com.lorent.lvmc.util.LvmcOpenfireUtil;
import com.lorent.lvmc.util.StringUtil;

/**
 *
 * @author test
 */
public class VoteService extends BaseService{
    public void sendVoteData(String theme){
        theme = StringUtil.convertSpecialCharString(theme);
//        theme = theme.replaceAll("&", "&amp;");
        LvmcOpenfireUtil.sendVoteData(theme);
    }
}
