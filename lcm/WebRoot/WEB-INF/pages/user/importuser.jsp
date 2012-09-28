<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'edit_user.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js"></script>
  </head>
  <body class="dialogue_body">
  	<form id="dialogue_form" action="users" method="post" enctype="multipart/form-data">
  		<input type="file" name="file_upload" id="file_upload"/>
  		<div id="template_div">
  		    <p><a href="template.xls">点击</a>下载模板</p>
  		</div>
  		<%String message=(String)request.getParameter("message");if(message!=null){ %>
  		<div>
  			<p style="color:orange"><%=message %></p>
  		</div>
  		<%} %>
  	</form>
  </body>
</html>
