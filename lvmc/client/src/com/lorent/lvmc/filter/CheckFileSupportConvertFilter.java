/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.filter;

import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.StringUtil;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class CheckFileSupportConvertFilter implements Filter{

    @Override
    public void doFilter(ExecuteObject bean, String[] paras) throws Exception {
        boolean bFlag = false;
//        String filename = ((String[])paras.get(Constants.FILTER_DYNAMIC_PARAS))[0];
        String filename = ((ParaUtil)bean.getParas()[0]).getValue(Constants.FILE_NAME);
        String extName = filename.substring(filename.lastIndexOf(".")+1);
        for (String fileType : com.lorent.lvmc.util.Constants.SUPPORT_CONVERT_FILETYPE) {
            if (fileType.equals(extName.toLowerCase())) {
                bFlag = true;
                break;
            }
        }
        if(!bFlag){
            throw new Exception(StringUtil.getErrorString("convertfile.notsupport"));
        }
//        return bFlag;
    }
    
}
