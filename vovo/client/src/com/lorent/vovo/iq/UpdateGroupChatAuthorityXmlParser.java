package com.lorent.vovo.iq;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class UpdateGroupChatAuthorityXmlParser {

	public static UpdateGroupChatAuthorityBean parse(Document doc){
		UpdateGroupChatAuthorityBean bean = new UpdateGroupChatAuthorityBean();
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
			String roomJid = ((Element)list.get(0)).attributeValue("name");
			bean.setRoomJid(roomJid);
			List marks = ((Element)list.get(0)).elements("mark");
			if(marks!=null && marks.size()>0){
				for(int i=0;i<marks.size();i++){
					Element mark = (Element)marks.get(i);
					bean.getMarks().add(mark.getText());
				}
			}
			
		}
		
		return bean;
	}
	
}
