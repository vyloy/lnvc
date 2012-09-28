<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<link rel="icon" href="<%=basePath%>/images/animated_favicon1.gif" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=basePath%>/images/favicon.ico" type="image/x-icon" />     	
    <title><s:text name="generic.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
  </head>
  <body>
    <div class="index_container">
    	<s:action name="toPage_pages_include_top" executeResult="true" namespace="/app" />
    	<s:action name="toPage_pages_include_middle" executeResult="true" namespace="/app" />
    	<s:action name="toPage_pages_include_bottom" executeResult="true" namespace="/app" />
    	<s:action name="toPage_pages_include_dialogue" executeResult="true" namespace="/app" />
    </div>
  </body>
</html>
