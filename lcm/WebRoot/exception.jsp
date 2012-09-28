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
    
    <title>My JSP 'exception.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
  </head>
  <body>
  	<s:hidden name="callBackUrl" cssClass="callbackUrl" />
  	<s:hidden name="exception.message" cssClass="emsg"></s:hidden>
  	<s:hidden name="title" cssClass="title" value="%{getText('page.error.title')}"></s:hidden>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			var title = $(".title").attr("value");
  			var msg = $(".emsg").attr("value");
  			var callbackUrl = $(".callbackUrl").attr("value");
  			window.parent.openConfirm(title,msg,true,false,function(){
  				$(window.parent.document).find(".index_iframe").attr("src",callbackUrl);
  			});
  		});
  	</script>
  </body>
</html>
