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
    
    <title>My JSP 'middle.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  	<div class="index_middle_container">
  		<security:authorize ifAllGranted="ROLE_ADMIN">
  			<jsp:include flush="true" page="/WEB-INF/pages/include/admin_left.jsp" />	
  			<!-- 
  			<iframe src="app/mcuAction_toMcuListPage_admin_mcu.action" frameborder="0" class="index_iframe" name="index_iframe"></iframe>
  			 -->
  			<iframe src="app/sysServerConfigAction_searchconfig_sysserverconfig.action" frameborder="0" class="index_iframe" name="index_iframe"></iframe>
  		</security:authorize>
  		<security:authorize ifNotGranted="ROLE_ADMIN">
  			<security:authorize ifAllGranted="ROLE_CUSTOMER_ADMIN">
  				<jsp:include flush="true" page="/WEB-INF/pages/include/cadmin_left.jsp" />
  				<iframe src="app/conferenceNewAction_toConferenceNewList_conferencenewlist.action" frameborder="0" class="index_iframe" name="index_iframe"></iframe>
  			</security:authorize>
  			<security:authorize ifAllGranted="ROLE_CUSTOMER_USER">
  				<jsp:include flush="true" page="/WEB-INF/pages/include/user_left.jsp" />
  				<iframe src="app/conferenceAction_toOngoningConfs_conflist.action?belong=mybuild" frameborder="0" class="index_iframe" name="index_iframe"></iframe>
  			</security:authorize>
  			<security:authorize ifAllGranted="ROLE_CUSTOMER_DEP_ADMIN">
  				<jsp:include flush="true" page="/WEB-INF/pages/include/user_left.jsp" />
  				<iframe src="app/conferenceAction_toOngoningConfs_conflist.action?belong=dept" frameborder="0" class="index_iframe" name="index_iframe"></iframe>
  			</security:authorize>
  		</security:authorize>
  	</div>
  </body>
</html>
