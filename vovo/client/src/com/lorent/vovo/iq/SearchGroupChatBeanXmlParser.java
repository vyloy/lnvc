package com.lorent.vovo.iq;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class SearchGroupChatBeanXmlParser {

	public static SearchGroupChatBean parse(Document doc){
		SearchGroupChatBean bean = new SearchGroupChatBean();
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
		list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='info']");
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Element info = (Element)list.get(i);
				String roomJid = info.elementText("roomjid");
				String groupName = info.elementText("groupname");
				String groupdesc = info.elementText("groupdesc");
				bean.addInfo(new String[]{roomJid,groupName,groupdesc});
			}
			
		}
		
		return bean;
	}
	
}
