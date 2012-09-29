/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.event;

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
 * @author Jack
 */
public class EventHelper{

    private Logger log = Logger.getLogger(EventHelper.class);
    
    public EventHelper() throws Exception{
    }
    
     /** 
     * 获取指定xml文档的Document对象,xml文件必须在classpath中可以找到 
     * 
     * @param xmlFilePath xml文件路径 
     * @return Document对象 
     */ 
    private Document parse2Document(String xmlFilePath) throws Exception{ 
        SAXReader reader = new SAXReader(); 
        Document document = null; 
        try { 
            InputStream in = EventHelper.class.getResourceAsStream(xmlFilePath); 
            document = reader.read(in); 
        } catch (DocumentException e) { 
            log.error("parse2Document", e);
            throw e;
        } 
        return document; 
    } 

    public Map<String,List<Listener>> parseConfigFile(String filePath) throws Exception{
        //将xml文档转换为Document的对象 
        Map<String,List<Listener>> map = new HashMap<String,List<Listener>>();
        Document document = parse2Document(filePath); 
        //获取文档的根元素 
        Element root = (Element) document.getRootElement(); 
        for(Iterator it = root.elementIterator(); it.hasNext();){
            Element eventElement = (Element)it.next();
            String id = eventElement.attributeValue("id");
            if(map.containsKey(id)){
                throw new Exception("event.xml文件定义了重复的ID");
            }
            List<Listener> list = new ArrayList<Listener>();
            map.put(id, list);
            for(Iterator ite = eventElement.elementIterator();ite.hasNext();){
                Element listenerElement = (Element) ite.next();
                Listener info = new Listener();
                info.setClassName(listenerElement.attributeValue("class"));
                info.setMethodName(listenerElement.attributeValue("method"));
                list.add(info);
            }
        }
        return map;
        
    }
    
}
