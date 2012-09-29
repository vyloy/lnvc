/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.filter;

import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.MethodUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author jack
 */
public class FilterManager {
    
    private Logger log = Logger.getLogger(FilterManager.class);
    private static FilterManager instance = new FilterManager();
            
    private void parseConfigFile() throws Exception{
        Map<String,List<Filter>> map = new HashMap<String,List<Filter>>();
        Document document = parse2Document("filter_conf.xml"); 
        //获取文档的根元素 
        Element root = (Element) document.getRootElement(); 
        for(Iterator it = root.elementIterator(); it.hasNext();){
            Element filtersElement = (Element)it.next();
            String id = filtersElement.attributeValue("id");
            if(map.containsKey(id)){
                throw new Exception("filter_conf.xml文件定义了重复的ID");
            }
            List<Filter> list = new ArrayList<Filter>();
            map.put(id, list);
            for(Iterator ite = filtersElement.elementIterator();ite.hasNext();){
                Element filterElement = (Element) ite.next();
                Filter filter = (Filter)DataUtil.getApplicationContext().getBean(filterElement.attributeValue("name"));
                list.add(filter);
            }
        }
        DataUtil.setValue(DataUtil.Key.filters, map);
    }
    
    private Document parse2Document(String xmlFilePath) throws Exception{ 
        SAXReader reader = new SAXReader(); 
        Document document = null; 
        InputStream in = FilterManager.class.getResourceAsStream(xmlFilePath); 
        document = reader.read(in); 
        return document; 
    } 
    
    private FilterManager(){
        try {
            parseConfigFile();
        } catch (Exception ex) {
        	log.error("FilterManager", ex);
            ex.printStackTrace();
        }
    }
    
    public static FilterManager getInstance(){
        return instance;
    }
    
    public Object doFilter(ExecuteObject o, String filtersId, String[] paras)throws Exception{
        Map<String,List<Filter>> map = DataUtil.getValue(DataUtil.Key.filters);
        List<Filter> filters = map.get(filtersId);
        if(filters == null){
            throw new Exception("filters:" + filtersId + " not exist");
        }
        for(Filter filter : filters){
            filter.doFilter(o, paras);
        }
//        o.getMethod().invoke(o.getBean(), o.getParas());
        return MethodUtil.invoke(o.getBean(), o.getMethod(), o.getParas());
    }
}
