/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.filter;

import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.ui.WhiteBoardPanel;
import com.lorent.lvmc.util.StringUtil;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MaxWhiteBoardFilter implements Filter{

    @Override
    public void doFilter(ExecuteObject bean, String[] paras) throws Exception {
        WhiteBoardPanel whiteBoardPanel = ViewManager.getComponent(WhiteBoardPanel.class);
        if(whiteBoardPanel.getJTabbedPane1().getTabCount() >= 2){//多余两个电子白板则不能共享文档
//            JOptionPane.showMessageDialog(null, StringUtil.getErrorString("ismaxwhiteboard"));
            throw new Exception(StringUtil.getErrorString("ismaxwhiteboard"));
        }
//        return false;
    }
    
}
