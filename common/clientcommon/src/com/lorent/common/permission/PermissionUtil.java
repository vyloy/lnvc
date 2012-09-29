package com.lorent.common.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.lorent.common.parse.xml.XMLParser;





public class PermissionUtil {

	private String confPath;
	private Logger log = Logger.getLogger(PermissionUtil.class);
	private static  Map<String,List<PermissionCheckInter>> map = new HashMap<String,List<PermissionCheckInter>>();
	
	public PermissionUtil(String path){
		try {
			confPath = path;
            parseConfigFile();
        } catch (Exception ex) {
        	log.error(ex);
        }
	}
	
	public void checkPermission(String id,Object... paras)throws NoPermissionException{
		List<PermissionCheckInter> list = map.get(id);
		if(null!=list && list.size()>0){
			for(PermissionCheckInter check : list){
				check.docheck(paras);
	        }
		}
	}
	
	private void parseConfigFile() throws Exception{
        Document document = XMLParser.parse2Document(confPath); 
        //获取文档的根元素 
        Element root = (Element) document.getRootElement(); 
        String fileName = confPath.substring(confPath.lastIndexOf("\\")+"\\".length());
        for(Iterator it = root.elementIterator(); it.hasNext();){
            Element filtersElement = (Element)it.next();
            String id = filtersElement.attributeValue("id");
            if(map.containsKey(id)){
            	
                throw new Exception(fileName + "文件定义了重复的ID");
            }
            List<PermissionCheckInter> list = new ArrayList<PermissionCheckInter>();
            map.put(id, list);
            for(Iterator ite = filtersElement.elementIterator();ite.hasNext();){
                Element filterElement = (Element) ite.next();
                PermissionCheckInter inter = (PermissionCheckInter)Class.forName(filterElement.attributeValue("name")).newInstance();//(Filter)DataUtil.getApplicationContext().getBean(filterElement.attributeValue("name"));
                list.add(inter);
            }
        }
    }
	
	
	public static void main(String args[]){
		String confPath = "\\com\\lorent\\aa.xml";
		System.out.println(confPath.substring(confPath.lastIndexOf("\\")+"\\".length()));
	}
	
}
