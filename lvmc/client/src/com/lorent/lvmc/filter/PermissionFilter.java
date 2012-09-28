/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.filter;

import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.PermissionUtil;
import com.lorent.lvmc.util.StringUtil;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author jack
 */
public class PermissionFilter implements Filter{

    private Logger log = Logger.getLogger(PermissionFilter.class);
    @Override
    public void doFilter(ExecuteObject bean, String[] paras) throws Exception {
        String[] strParas = paras;//(String[]) paras.get(Constants.FILTER_STATIC_PARAS);
        boolean flag = PermissionUtil.hasPermission(strParas);
        if(!flag){
            throw new Exception(StringUtil.getErrorString("permit.nopermission"));
        }
    }
    
}
