/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.manager;

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

import com.lorent.common.filter.ExecuteObject;
import com.lorent.common.filter.Filter;
import com.lorent.common.util.MethodUtil;

/**
 *
 * @author jack
 */
public class FilterManager extends BaseManager{
    
    private Logger log = Logger.getLogger(FilterManager.class);
    private Map<String,List<Filter>> filters;
    
    private void parseConfigFile(String filePath) throws Exception{
    	filters = new HashMap<String,List<Filter>>();
        Document document = parse2Document(filePath); 
        //获取文档的根元素 
        Element root = (Element) document.getRootElement(); 
        for(Iterator it = root.elementIterator(); it.hasNext();){
            Element filtersElement = (Element)it.next();
            String id = filtersElement.attributeValue("id");
            if(filters.containsKey(id)){
                throw new Exception("filter_conf.xml文件定义了重复的ID");
            }
            List<Filter> list = new ArrayList<Filter>();
            filters.put(id, list);
            for(Iterator ite = filtersElement.elementIterator();ite.hasNext();){
                Element filterElement = (Element) ite.next();
                Filter filter = (Filter)context.getSpringContext().getBean(filterElement.attributeValue("name"));
                list.add(filter);
            }
        }
    }
    
    public void init(String filePath) throws Exception{
    	parseConfigFile(filePath);
    }
    
    private Document parse2Document(String xmlFilePath) throws Exception{ 
        SAXReader reader = new SAXReader(); 
        Document document = null; 
        InputStream in = FilterManager.class.getResourceAsStream(xmlFilePath); 
        document = reader.read(in); 
        return document; 
    } 
    
    public Object doFilter(ExecuteObject o, String filtersId, String[] paras)throws Exception{
        List<Filter> filters = this.filters.get(filtersId);
        if(filters == null){
            throw new Exception("filters:" + filtersId + " not exist");
        }
        for(Filter filter : filters){
            filter.doFilter(o, paras);
        }
        return MethodUtil.invoke(o.getBean(), o.getMethod(), o.getParas());
    }
}
