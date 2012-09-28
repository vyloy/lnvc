package org.jivesoftware.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.tree.DefaultElement;
import org.xmpp.packet.Packet;

public class XMLUtil {
	public static Node getSingleNode(Element element, String xpath, String xmlns){
		Node node = null;
		if(xmlns == null){
			XPath x = element.createXPath("//" + xpath);
			node = x.selectSingleNode(element);
		}else{
			xpath = "//xmlns:" + xpath;
			Map map = new HashMap();
			map.put("xmlns", xmlns);
			XPath x = element.createXPath(xpath);
			x.setNamespaceURIs(map);
			node = x.selectSingleNode(element);
		}
		return node;
	}
	
	public static List<Node> getNodes(Element element, String xpath, String xmlns){
		List<Node> nodes = null;
		if(xmlns == null){
			XPath x = element.createXPath("//" + xpath);
			nodes = x.selectNodes(element);
		}else{
			xpath = "//xmlns:" + xpath;
			Map map = new HashMap();
			map.put("xmlns", xmlns);
			XPath x = element.createXPath(xpath);
			x.setNamespaceURIs(map);
			nodes = x.selectNodes(element);
		}
		return nodes;
	}
	
	public static Object unSerializeObject(String content) throws Exception{
		ByteArrayInputStream sis = new ByteArrayInputStream(StringUtils.decodeBase64(content));
        ObjectInputStream ois = new ObjectInputStream(sis);
        return ois.readObject();
	}
	
	public static String serializeObject(Object obj) throws Exception{
		ByteArrayOutputStream sw = new ByteArrayOutputStream();   
        ObjectOutputStream oos = new ObjectOutputStream(sw);
        oos.writeObject(obj);
        return StringUtils.encodeBase64(sw.toByteArray());
	}
	
	public static String getPropertiesString(Packet packet){
		Element element = packet.getElement().element("properties");
		if(element != null){
			return element.asXML();
		}else{
			return null;
		}
	}
	
	public static Map<String, Object> getProperties(Packet packet) throws Exception{
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		Document doc = DocumentHelper.parseText(packet.getElement().asXML());
		DefaultElement root = (DefaultElement) doc.getRootElement();
		List elements = root.element("properties").elements("property");
		for (Object object : elements) {
			DefaultElement element = (DefaultElement)object;
			Element nameElement = element.element("name");
			Element valueElement = element.element("value");
			Attribute attribute = valueElement.attribute("type");
			if (attribute != null && attribute.getValue().equals("java-object")) {
				Object unSerializeObject = XMLUtil.unSerializeObject(valueElement.getStringValue());
				result.put(nameElement.getStringValue(), unSerializeObject);
			}
			else{
				result.put(nameElement.getStringValue(), valueElement.getStringValue());
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception{
//		File file = new File("temp.txt");
		FileReader fr = new FileReader("d:/temp.txt");
		StringBuffer sb = new StringBuffer();
		char[] buf = new char[1024]; 
		while(fr.read(buf)!= -1){
			sb.append(buf);
		}
		String temp = sb.toString().trim();
		System.out.println(temp);
		System.out.println("=====================");
		Document doc = DocumentHelper.parseText(temp);
//		Element rootElement = doc.getRootElement();
//		List selectNodes = doc.selectNodes("properties");
//		System.out.println(selectNodes);
//		for (Object object : selectNodes) {
//			System.out.println(object);
//		}
		List<Node> nodes = XMLUtil.getNodes(doc.getRootElement(), "property", "http://www.jivesoftware.com/xmlns/xmpp/properties");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		for (Node node : nodes) {
//			System.out.println(node.asXML());
			
			List<Node> nodes2 = XMLUtil.getNodes((Element) node, "name", "http://www.jivesoftware.com/xmlns/xmpp/properties");
			for (Node node2 : nodes2) {
//				System.out.println(node2);
			}
		}
		
		
		DefaultElement root = (DefaultElement) doc.getRootElement();
		
		List elements = root.element("properties").elements("property");
		for (Object object : elements) {
			
			System.out.println(object);
			
			DefaultElement element = (DefaultElement)object;
			Element nameElement = element.element("name");
			System.out.println("name:"+nameElement.getStringValue());
			Element valueElement = element.element("value");
			Attribute attribute = valueElement.attribute("type");
			if (attribute != null && attribute.getValue().equals("java-object")) {
				Object unSerializeObject = XMLUtil.unSerializeObject(valueElement.getStringValue());
				hashMap.put(nameElement.getStringValue(), unSerializeObject);
			}
			else{
				hashMap.put(nameElement.getStringValue(), valueElement.getStringValue());
			}
			
		}
		
//		System.out.println(doc.getRootElement().element("properties").getPath());

		
		
	}
}
