/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.event;

import com.lorent.lvmc.dto.ListenerDto;
import com.lorent.lvmc.util.DataUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Administrator
 */
public class XmlEventConfig{

    private static Logger log = Logger.getLogger(XmlEventConfig.class);
    
    public XmlEventConfig() throws Exception{
    }
    
     /** 
     * 获取指定xml文档的Document对象,xml文件必须在classpath中可以找到 
     * 
     * @param xmlFilePath xml文件路径 
     * @return Document对象 
     */ 
    public static Document parse2Document(String xmlFilePath) throws Exception{ 
        SAXReader reader = new SAXReader(); 
        Document document = null; 
        try { 
            InputStream in = XmlEventConfig.class.getResourceAsStream(xmlFilePath); 
            document = reader.read(in); 
        } catch (DocumentException e) { 
//            System.out.println(e.getMessage()); 
//            System.out.println("读取classpath下xmlFileName文件发生异常，请检查CLASSPATH和文件名是否存在！"); 
//            e.printStackTrace(); 
            log.error("parse2Document", e);
            throw e;
        } 
        return document; 
    } 

    public void parseConfigFile() throws Exception{
        //java.net.URL url = getClass().getResource("/com/lorent/desktoplcc/util/event.xml");
        //将xml文档转换为Document的对象 
        Map<String,List<ListenerDto>> map = new HashMap<String,List<ListenerDto>>();
        Document document = parse2Document("event_listener.xml"); 
        //获取文档的根元素 
        Element root = (Element) document.getRootElement(); 
        for(Iterator it = root.elementIterator(); it.hasNext();){
            Element eventElement = (Element)it.next();
            String id = eventElement.attributeValue("id");
            if(map.containsKey(id)){
                throw new Exception("event.xml文件定义了重复的ID");
            }
            List<ListenerDto> list = new ArrayList<ListenerDto>();
            map.put(id, list);
            for(Iterator ite = eventElement.elementIterator();ite.hasNext();){
                Element listenerElement = (Element) ite.next();
                ListenerDto info = new ListenerDto();
                info.setClassName(listenerElement.attributeValue("class"));
                info.setMethodName(listenerElement.attributeValue("method"));
                list.add(info);
            }
        }
        DataUtil.setValue(DataUtil.Key.listener, map);
        //return map;
    }
    
    public static List<ListenerDto> getListeners(String eventId){
        Map<String,List<ListenerDto>> map = DataUtil.getValue(DataUtil.Key.listener);//(Map<String,List<ListenerDto>>) DataUtil.getValue("listeners");
        List<ListenerDto> list = map.get(eventId);
        return list;
    }
    
    public static void main(String args[]){
        try{
            XmlEventConfig config = new XmlEventConfig();
            config.parseConfigFile();
            Map<String,List<ListenerDto>> map = (Map<String,List<ListenerDto>>) DataUtil.getValue(DataUtil.Key.listener);
            for(String id:map.keySet()){
                List<ListenerDto> list = map.get(id);
                System.out.println(id);
                for(ListenerDto info:list){
                    System.out.println(info.getClassName());
                    System.out.println(info.getMethodName());
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
}
