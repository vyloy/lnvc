/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.filter.FilterTag;
import com.lorent.lvmc.ui.LvmcJFileChooser;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class JFileChooserController extends BaseController{
    private Logger log = Logger.getLogger(JFileChooserController.class);
    
    @FilterTag(id="fileChooserFilters")
    public void approveSelection(ParaUtil paras) throws Exception{
        LvmcJFileChooser fileChooser = paras.getValue("fileChooser");
        fileChooser.setConvertFileToImgFlag(true);
        fileChooser.approveSelection();
    }
}
