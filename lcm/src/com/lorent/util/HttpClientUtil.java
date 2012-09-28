package com.lorent.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class HttpClientUtil {

	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		//HttpGet httpGet = new HttpGet("http://10.168.250.12:9090/plugins/userService/userservice?type=existGroup&secret=123&username=admin&groups=asdf");
//		HttpGet httpGet = new HttpGet("http://10.168.250.12:9090/plugins/userService/userservice?type=createShareGroup&secret=123&username=admin&groups=bbb");
//		HttpGet httpGet = new HttpGet("http://10.168.250.12:9090/plugins/userService/userservice?type=removeShareGroup&secret=123&username=admin&groups=bbb");
//		HttpGet httpGet = new HttpGet("http://10.168.250.12:9090/plugins/userService/userservice?type=addGroupUser&secret=123&username=8504759&groups=ttt");
		HttpGet httpGet = new HttpGet("http://10.168.250.12:9090/plugins/userService/userservice?type=removeGroupUser&secret=123&username=8504759&groups=ttt");

		HttpResponse response1 = httpClient.execute(httpGet);

		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the
		// network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST either fully consume the response content or abort
		// request
		// execution by calling HttpGet#releaseConnection().

		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();
			// do something useful with the response body
//			String ret = EntityUtils.toString(entity1);
			SAXReader sr = new SAXReader();
			Document doc = sr.read(entity1.getContent());
			XPath xpath = doc.getRootElement().createXPath("result");
			Node node = xpath.selectSingleNode(doc);
			System.out.println(node.getText());
			// and ensure it is fully consumed
			EntityUtils.consume(entity1);
		} finally {
			httpGet.releaseConnection();
		}

	}
	
	public static String executeGet(String url) throws Exception {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response1 = httpClient.execute(httpGet);
			if (response1.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				HttpEntity entity1 = response1.getEntity();
				String ret = EntityUtils.toString(entity1);
				EntityUtils.consume(entity1);
				return ret;
			}
		} finally {
			httpGet.releaseConnection();
		}
		return null;
	}
}
