/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.awt.Color;

/**
 *
 * @author jack
 */
public class Constants {
    public static final int WHITEPANEL_STATE_PAN_SELECT = 0;
    public static final int WHITEPANEL_STATE_PAN_DESELECT = 1;
    public static final int WHITEPANEL_PAN_ICON_WIDHT_OFFSET = 0;
    public static final int WHITEPANEL_PAN_ICON_HEIGHT_OFFSET = 32;
    public static final String UserPath = System.getProperty("user.home").replace("\\", "/");
    public static final String AppPath = System.getProperty("user.dir").replace("\\", "/");
    public static final String UserDataPath = System.getProperty("user.dir").replace("\\", "/")+"/UserData";
    public static final String DllPath = System.getProperty("user.dir").replace("\\", "/")+"/dll";
    public static final String LayoutDataPath = System.getProperty("user.home").replace("\\", "/")+"/lorent/lvmc/layouts";
    public static final String SKIN_PATH = System.getProperty("user.dir").replace("\\", "/")+"/skin";
    
    public static final int OPER_ADD = 0;
    public static final int OPER_MODIFY = 1;
    public static final int OPER_DELETE = 2;
    
    public static final String[] SUPPORT_CONVERT_FILETYPE  ={"pdf","ppt","doc","xls"};
    
    public static final boolean DEFAULT_OPEN_CAMERA = true;
    
    public static final String[] SPECIL_CHARATERS = {"&","#","\\*","%",":","：","@"};
    public static final String SPECIL_CHARATERS_REGEX = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    public static final int DOCKING_LAYOUT_DIRECTION_UP = 0;
    public static final int DOCKING_LAYOUT_DIRECTION_DOWN = 1;
    public static final int DOCKING_LAYOUT_DIRECTION_LEFT = 2;
    public static final int DOCKING_LAYOUT_DIRECTION_RIGHT = 3;
    
    public static final int MSGTYPE_TO_ALL = 100;
    public static final int MSGTYPE_TO_ONE = 101;
    
    public static final int MEMBER_STATUS_JOIN = 1;
    public static final int MEMBER_STATUS_LEAVE = 2;
    public static final int MEMBER_STATUS_UPDATE = 3;
    
    public static final int MEMBER_STATUS_SHAREDESKTOP_FREE = 0;
    public static final int MEMBER_STATUS_SHAREDESKTOP_VIEW = 1;
    public static final int MEMBER_STATUS_SHAREDESKTOP_CONTROL = 2;
    
    public static final int SELECTITEMTYPE_SINGLE = 1;// 单选 ，
    public static final int SELECTITEMTYPE_MULTI = 2;// 多选 
    public static final int SELECTITEMTYPE_YES_OR_NO = 0;// 是否
    public static final String ITEMKEY = "ITEMKEY";// 
    
    public static final String XMLNS="jabber:iq:conferencevoteplugin";
    
    public static final String FILTER_STATIC_PARAS = "staticParas";
    public static final String FILTER_DYNAMIC_PARAS = "dynamicParas"; 
    public static final String FILE_NAME = "fileName";
    
    //获取历史聊天记录数
    public static int MAX_HISTORY = 30;
    
    public static final String SUPPER_USER = "admin";
    
    /* --------------------  look and feel class name -------------------------*/
    public static final String PLAF_METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static final String PLAF_NIMBUS = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    public static final String PLAF_WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String PLAF_MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String PLAF_MAC = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    public static final String PLAF_ACRYL = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
    public static final String PLAF_AERO = "com.jtattoo.plaf.aero.AeroLookAndFeel";
    public static final String PLAF_ALUMINIUM = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
    public static final String PLAF_BERNSTEIN = "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";
    public static final String PLAF_FAST = "com.jtattoo.plaf.fast.FastLookAndFeel";
    public static final String PLAF_GRAPHITE = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
    public static final String PLAF_HIFI = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
    public static final String PLAF_LUNA = "com.jtattoo.plaf.luna.LunaLookAndFeel";
    public static final String PLAF_MCWIN = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
    public static final String PLAF_MINT = "com.jtattoo.plaf.mint.MintLookAndFeel";
    public static final String PLAF_NOIRE = "com.jtattoo.plaf.noire.NoireLookAndFeel";
    public static final String PLAF_SMART = "com.jtattoo.plaf.smart.SmartLookAndFeel";
    public static final String PLAF_CUSTOM = "com.jtattoo.plaf.custom.systemx.SystemXLookAndFeel";
    
    public static final String APPLICATION_NAME = "VOVOT.exe";

    public enum AppName{
    	UCS,
    	VOVO
    }
    
    public enum VideoParam{
    	VideoEquipment,
    	PixelType,
    	PixelValue,
    	FrameRate,
    	VideoBitrate
    }
    
    public enum AudioParam{
    	MicEquipment,
    	NarratorEquipment,
    	MicVolume,
    	NarratorVolume
    }
    
    public static void main(String args[]) {
        StringBuffer sb = new StringBuffer();
        String str = "sdf#adsf%ds*asdf%asd：f:@！（）()!^";
        for(int i = 0;i<str.length();i++){
            if((str.charAt(i) >= '0' && str.charAt(i) <= '9') || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                sb.append(str.charAt(i));
            }
        }
        System.out.println(sb.toString());
    }
    
    public static final String COMPERE_STR = "主持人";
    public static final String NARRATOR_STR = "主讲人";
    public static final String PARTICIPANT_STR = "普通会议者";
    public static final int GRANT_AUTHORITY = 1;
    public static final int REVOKE_AUTHORITY = -1;
    
    public static final Object[] AUDIO_CODES = {"PCMU", "G729", "G711", "SILK"};
}
