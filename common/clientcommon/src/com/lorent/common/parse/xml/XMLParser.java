package com.lorent.common.parse.xml;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;


public class XMLParser {

	public static Document parse2Document(String xmlFilePath) throws Exception{ 
        SAXReader reader = new SAXReader(); 
        Document document = null; 
        InputStream in = XMLParser.class.getResourceAsStream(xmlFilePath); 
        document = reader.read(in); 
        return document; 
    }
	
}
