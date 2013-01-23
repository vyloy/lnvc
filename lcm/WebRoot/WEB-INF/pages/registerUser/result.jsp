<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>络威视频会议系统 -- 激活帐号</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/tab.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/tab.js"></script>
  <body><img src="<%=basePath%>/images//vovo.png" alt="1111" width="256" height="256">
  	<div class="contentContainer" style="height:100px;line-height:100px;">
  	您的号码已经成功激活，您现在已经可以登录络威网络视频会议系统。  	</div>
  </body>
</html>
