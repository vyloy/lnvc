package com.lorent.vovo.iq;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.lorent.vovo.util.VovoStringUtil;

public class IQXmlParser {

	private String spaceName = "";
	private Document doc;
	private String operateName = "";
	
	public static void main(String args[]){
		String text = "<iq type=\"result\" id=\"Qy99y-4\" to=\"85323@localhost/Smack\"><query xmlns=\"jabber:iq:groupchatplugin\"><operate name=\"getGroupChat\"><result>success</result><roomjid>fqq1@groupchat.localhost</roomjid></operate></query></iq>";
		IQXmlParser parser = new IQXmlParser(text);
		
	}
	
	public void parse2Document(String text){
		try {
			doc = DocumentHelper.parseText(text);
			List list = doc.selectNodes("/iq/*[name()='query']");
			if(list!=null&&list.size()>0){
				spaceName = ((Element)list.get(0)).getNamespaceURI();
				//System.out.println(spaceName);
			}
			list = null;
			list = doc.selectNodes("/iq/*[name()='query']/*[name()='operate']");
			if(list!=null&&list.size()>0){
				Element operateElement = (Element)list.get(0);
				operateName =  operateElement.attributeValue("name");
				System.out.println(operateName);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Element root = doc.getRootElement();
		
//		<iq type="result" id="Qy99y-4" to="85323@localhost/Smack"><query xmlns="jabber:iq:groupchatplugin"><operate name="getGroupChat"><result>success</result><roomjid>fqq1@groupchat.localhost</roomjid></operate></query></iq>
	}
	
	public String getOperateName(){
		return this.operateName;
	}
	
	public Document getDocument(){
		return doc;
	}
	
	public String getSpaceName(){
		return this.spaceName;
	}
	
	public IQXmlParser(String text){
		parse2Document(text);
	}
	
	public GroupChatJidBean parse2GroupChatJidBean(){
		GroupChatJidBean bean = new GroupChatJidBean();
		List list = doc.selectNodes("/iq/query/operate/result");
		if(list!=null&&list.size()>0){
			String result = ((Element)list.get(0)).getText();
			bean.setResult(result);
		}
		list = null;
		list = doc.selectNodes("/iq/query/operate/message");
		if(list!=null&&list.size()>0){
			String message = ((Element)list.get(0)).getText();
			bean.setResult(message);
		}
		list = null;
		list = doc.selectNodes("/iq/query/operate/roomjid");
		if(list!=null&&list.size()>0){
			for(Object o:list){
				Element roomjidElement = (Element)o;
				bean.getJids().add(roomjidElement.getText());
			}
		}
		
		return bean;
	}
}
