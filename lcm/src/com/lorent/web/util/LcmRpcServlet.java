package com.lorent.web.util;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.xmlrpc.XmlRpcConfigImpl;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

public class LcmRpcServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private XmlRpcServletServer server;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			server.execute(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			server = new XmlRpcServletServer();
			PropertyHandlerMapping handlerMapping = new PropertyHandlerMapping();
			String[] handlerStr = config.getInitParameter("handler").split(",\\s+");
			System.out.println(handlerStr.length);
			for (int i = 0; i < handlerStr.length; i++) {
				String[]strs = handlerStr[i].split("-");
				handlerMapping.addHandler(strs[0], Class.forName(strs[1]));
			}
			server.setHandlerMapping(handlerMapping);
			XmlRpcConfigImpl rpcConfig = (XmlRpcConfigImpl)server.getConfig();
			rpcConfig.setEnabledForExtensions(true);
			rpcConfig.setContentLengthOptional(false);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}