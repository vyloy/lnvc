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
    
    <title>My JSP 'dialogue_passconfirm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
  </head>
  <body class="dialogue_body">
  	<form action="ajax/conference_checkPass" onsubmit="return false" id="dialogue_form">
  		<input type="hidden" name="id" id="hiddenId" />
  		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 100%;"  />
    		</colgroup>
    		<tr>
    			<td style="text-align:center; color: #F79F41; height: 20px;">
    				<s:text name="page.text.passtip" />
    			</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;vertical-align:middle;">
    				<s:text name="page.conf.confpass" />
    				<input type="password" name="pass" style="width:50%"/>
    			</td>
    		</tr>
    	</table>	
  	</form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  </body>
</html>
