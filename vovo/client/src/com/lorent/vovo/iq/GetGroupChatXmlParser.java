package com.lorent.vovo.iq;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class GetGroupChatXmlParser {

	public static GroupChatJidBean parse(Document doc){
		GroupChatJidBean bean = new GroupChatJidBean();
		List list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='result']");
		if(list!=null&&list.size()>0){
			String result = ((Element)list.get(0)).getText();
			bean.setResult(result);
		}
		list = null;
		list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='message']");
		if(list!=null&&list.size()>0){
			String message = ((Element)list.get(0)).getText();
			bean.setMessage(message);
		}
		list = null;
		list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='roomjid']");
		if(list!=null&&list.size()>0){
			for(Object o:list){
				Element roomjidElement = (Element)o;
				bean.getJids().add(roomjidElement.getText());
			}
		}
		
		return bean;
	}
	
}
