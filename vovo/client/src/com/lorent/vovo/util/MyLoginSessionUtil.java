package com.lorent.vovo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MyLoginSessionUtil {

	public static List<String> sessionIds = new ArrayList<String>();
	
	public static void logout(String urlStr) {
		for (String str : sessionIds) {
			downLoadPages(urlStr + "/logout.jsp?jsessionid=" + str,false);
		}
	}
	
	public static void downLoadPages(String urlStr, boolean addSession) {
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;

		try {
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");

			in = httpConn.getInputStream();
			//            out = new FileOutputStream(new File(outPath));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String input = "";
			StringBuffer content = new StringBuffer();
			while ((input = br.readLine()) != null) {
				content.append(input);
			}
			System.out.println(content.toString());
			if (addSession) {
				sessionIds.add(content.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//                out.close();
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String counthttpurl = "http://10.168.250.12:6090/videoserver/video.jsp";
		MyLoginSessionUtil.downLoadPages(counthttpurl, true);
		MyLoginSessionUtil.logout("http://10.168.250.12:6090/videoserver");
		
		InetAddress localHost = InetAddress.getLocalHost();
		byte[] address = localHost.getAddress();
		for (byte b : address) {
			int x = b;
			System.out.println(x);
		}
		System.out.println(localHost+" , "+address);
	}

}
