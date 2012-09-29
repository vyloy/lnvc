package com.lorent.vovo.iq;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class ApplyInGroupChatXmlParser {

	public static ApplyInGroupChatBean parse(Document doc){
		ApplyInGroupChatBean bean = new ApplyInGroupChatBean();
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
		list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='applicant']");
		if(list!=null&&list.size()>0){
			String applicant = ((Element)list.get(0)).getText();
			bean.setApplicantJid(applicant);
		}
		list = null;
		list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='applicantname']");
		if(list!=null&&list.size()>0){
			String applicantName = ((Element)list.get(0)).getText();
			bean.setApplicantName(applicantName);
		}
		list = null;
		list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']/*[name()='roomjid']");
		if(list!=null&&list.size()>0){
			String roomJid = ((Element)list.get(0)).getText();
			bean.setRoomJid(roomJid);
		}
		return bean;
	}
	
}
