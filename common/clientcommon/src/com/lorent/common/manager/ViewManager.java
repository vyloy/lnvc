package com.lorent.common.manager;

import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;

import com.lorent.common.util.DataUtil;
import com.lorent.common.util.StringUtil;

public class ViewManager extends BaseManager{
	
	private String i18nPath;
	public void init(String i18nPath){
		this.i18nPath = i18nPath;
	}
	
	private DataUtil data = new DataUtil();
	
	public <T> T removeView(String key){
		return (T)data.removeByKey(key);
	}
	
	public <T> T createView(Class<T> clazz, String key)throws Exception{
		Object view = clazz.newInstance();
		if(key != null){
			data.setValue(key, view);
		}
		return (T)view;
	}
	
	public <T> T createView(Class<T> clazz, Class partypes[], Object arglist[], String key)throws Exception{
		Constructor ct = clazz.getConstructor(partypes);
		Object view = ct.newInstance(arglist);
		if(key != null){
			data.setValue(key, view);
		}
		return (T)view;
	}
	
	public <T> T getView(String key){
		return (T)data.getValue(key);
	}
	
	public void setView(Object view,String key) throws Exception{
		data.setValue(key, view);
	}
	
	public String getUIString(String key){
		return StringUtil.getResourceString(i18nPath, key);
	}
	
	public String getFormatString(String key, Object...paras){
        return MessageFormat.format(StringUtil.getResourceString(i18nPath, key), paras);
    }
	
	//设置窗口显示屏幕中心位置
    public void setWindowCenterLocation(Window w) throws Exception{
        if (w != null) {
            double width = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - w.getPreferredSize().width) / 2;
            double height = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - w.getPreferredSize().height) / 2;
            w.setLocation((int) width, (int) height);
        }
    }
    
    public void setWindowRightButtomLocation(Window w) throws Exception{
    	if (w != null) {
    		int bottmToolKitHeight = Toolkit.getDefaultToolkit().getScreenInsets(w.getGraphicsConfiguration()).bottom;
    		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth() - w.getWidth();
    		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - w.getHeight() - bottmToolKitHeight;
    		w.setLocation((int) width, (int) height);
		}
    	
    }
    
    public boolean isCheckShowLeft(Object obj,int len){
    	java.awt.Component component = (java.awt.Component)obj;
        java.awt.Point p = component.getLocationOnScreen();
//        if(p.getX()<width){
//        	return HorizontalLocation.LEFT;
//        }else if(p.getX()==width){
//        	return HorizontalLocation.CENTER;
//        }else{
//        	return HorizontalLocation.RIGHT;
//        }
        if(p.getX()>=len){
        	return true;
        }else{
        	return false;
        }
    }
}
