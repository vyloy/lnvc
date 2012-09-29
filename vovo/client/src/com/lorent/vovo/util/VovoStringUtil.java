package com.lorent.vovo.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.jivesoftware.smack.util.StringUtils;

import com.lorent.common.util.StringUtil;

public class VovoStringUtil extends StringUtil{

	private static List<KVBean> characterList = new ArrayList<KVBean>();
	
	static{
		characterList.add(new KVBean("&amp;", "&"));
		characterList.add(new KVBean("&", "&amp;"));
		characterList.add(new KVBean("<", "&lt;"));
//		characterMap.put("&amp;", "&");
//		characterMap.put("&", "&amp;");
	}
	
	public static String getUIString(String key){
		
//        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/vovo/resource/i18n/view"); 
        return StringUtil.getResourceString("com/lorent/vovo/resource/i18n/view", key);
    }
    
    public static String getErrorString(String key){
//        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/vovo/resource/i18n/error"); 
        return StringUtil.getResourceString("com/lorent/vovo/resource/i18n/error", key);
    }
	
    public static String convertSpecialCharString(String content){
        if(content == null){
            return null;
        }
//        Set<Entry<String, String>> set = characterMap.entrySet();
//        for(Entry<String, String> entry:set){
//            String key = entry.getKey();
//            String value =entry.getValue();
//            content = content.replaceAll(key, value);
//        }
        for(KVBean bean:characterList){
        	content = content.replaceAll(bean.getKey(), bean.getValue());
        }
        return content;
    }
    
    public static String getHeadString(String content,String flag){
    	if(content != null){
    		int idx = content.indexOf(flag);
    		if(idx>-1){
    			content = content.substring(0, idx);
    		}
    	}else{
    		content = "";
    	}
    	return content;
    }
    
    public static String getBottomString(String content,String flag){
    	if(content != null){
    		int idx = content.indexOf(flag);
    		if(idx>-1){
    			content = content.substring(idx+1);
    		}
    	}else{
    		content = "";
    	}
    	return content;
    }
    
    public static String replaceStarCharacter(String content,String newStr){
    	return content = content.replaceAll("\\*", newStr).replaceAll("\"", newStr);
    }
    
    public static void main(String args[]){
    	System.out.println(replaceStarCharacter("asdf*90",""));
    }
    
    public static String escapeForXML(String content){
    	return StringUtils.escapeForXML(content);
    }
    
	public static String encodeImgToStr(BufferedImage image) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		byte[] byteArray = baos.toByteArray();
		String str = Base64.encodeBase64String(byteArray);
		baos.close();
		// System.out.println(str);
		return str;
	}

	public static BufferedImage decodeStrToImg(String str) throws Exception {
		byte[] bs = Base64.decodeBase64(str);
		ByteArrayInputStream bais = new ByteArrayInputStream(bs);
		BufferedImage image = ImageIO.read(bais);
		bais.close();
		return image;
	}
	
	public static final String[] dirChs = {"*","\""};
	
	public static boolean dirContainspecialChar(String content){
		return containspecialChar(content,dirChs);
	}
	
	public static boolean containspecialChar(String content,String[] chs){
		boolean flag = false;
		for(int i=0;i<chs.length;i++){
			if(content.indexOf(chs[i])>-1){
				flag =  true;
				break;
			}
		}
		return flag;
	}

    
}

class KVBean{
	private String key;
	private String value;
	
	
	public KVBean(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
