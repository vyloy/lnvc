package com.lorent.common.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil {

	private static final Logger log = Logger.getLogger(HttpUtil.class);
	
	public static class ResponseEntity{
		public String content;
		public int statusCode;
		@Override
		public String toString() {
			return statusCode + "\n" + content;
		}
	}
	
	public static ResponseEntity doGet(String url) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		HttpResponse response1 = httpclient.execute(httpGet);

		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the
		// network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST either fully consume the response content or abort
		// request
		// execution by calling HttpGet#releaseConnection().

		try {
			log.info(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			ResponseEntity re = new ResponseEntity();
			re.content = EntityUtils.toString(entity1, "utf8");
			re.statusCode = response1.getStatusLine().getStatusCode();
			EntityUtils.consume(entity1);
			return re;
		} finally {
			httpGet.releaseConnection();
		}
	}

	public static ResponseEntity doPost(String url, Map<String, String> map) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(Iterator<String> it = map.keySet().iterator(); it.hasNext();){
			String key = it.next();
			nvps.add(new BasicNameValuePair(key, map.get(key)));
		}		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("utf8")));
		
		HttpResponse response2 = httpclient.execute(httpPost);

		try {
			log.info(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			ResponseEntity re = new ResponseEntity();
			re.content = EntityUtils.toString(entity2, "utf8");
			re.statusCode = response2.getStatusLine().getStatusCode();
			EntityUtils.consume(entity2);
			return re;
		} finally {
			httpPost.releaseConnection();
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("sender", "33012");
		map.put("content", "我是谁");
		ResponseEntity re = doPost("http://10.168.150.72:6090/lcm/ajax/guestbook_sendAdvice.action", map);
		System.out.println(re);
	}
}
