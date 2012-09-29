<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'emsg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  	<div style="width: 100%; text-align: center;color: #F79F41; font-size: 14px;clear: both;">
  		<%=request.getParameter("msg") %>
  	</div>
  	<button style="width: 60px;height: 23px;line-height:20px;vertical-align:middle;cursor: pointer;border:none;background-image: url(images/dialogue_button_bg.gif);
				color: #fff;margin-left: 5px;margin-top: 3px; position: absolute; bottom: 5px;left: 110px;" onclick="window.parent.closeDialogue()">
  		<s:text name="page.text.ok" />
  	</button>
  </body>
</html>
