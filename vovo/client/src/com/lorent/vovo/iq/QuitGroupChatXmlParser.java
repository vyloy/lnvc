package com.lorent.vovo.iq;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class QuitGroupChatXmlParser {

	public static QuitGroupChatBean parse(Document doc){
		QuitGroupChatBean bean = new QuitGroupChatBean();
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
			String roomJid = ((Element)list.get(0)).getText();
			bean.setRoomJid(roomJid);
		}
		list = null;
		list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='confirm']");
		if(list!=null&&list.size()>0){
			bean.setNeedToConfirm(true);
		}
		
		return bean;
	}
	
}
