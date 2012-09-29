<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sd" uri="/struts-dojo-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mcu_edit.jsp' starting page</title>
    <sd:head/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/dialogue.js" charset="utf-8"></script>
  </head>
  
  <body class="dialogue_body">
  		<sd:tabbedpanel id="conf_tabbedpanel" cssClass="tabbedpanel" closeButton="true">
  			<s:iterator value="conferenceRoles" id="role">
  			<sd:div label="#role.value" cssClass="tabpanel_div">
  				<s:include value="/WEB-INF/pages/conferenceNew/conferencenew_users.jsp">
	  				<s:param name="role">
		  				<s:property value="key"/>
		  			</s:param>
	  			</s:include>
  			</sd:div>
  			</s:iterator>
  		</sd:tabbedpanel>
  </body>
</html>
