package com.lorent.vovo.iq;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class FetchGroupChatAuthorityXmlParser {

	public static FetchGroupChatAuthorityBean parse(Document doc){
		FetchGroupChatAuthorityBean bean = new FetchGroupChatAuthorityBean();
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
			for(int i=0;i<list.size();i++){
				Element roomJid = (Element)list.get(i);
				List<String> marks = new ArrayList<String>();
				bean.getAuthorityMap().put(roomJid.attributeValue("name"), marks);
				List markElements = roomJid.elements("mark");
				if(markElements!=null && markElements.size()>0){
					for(int j=0;j<markElements.size();j++){
						Element mark = (Element)markElements.get(j);
						marks.add(mark.getText());
					}
				}
				
			}
		}
		
		return bean;
	}
	
}
