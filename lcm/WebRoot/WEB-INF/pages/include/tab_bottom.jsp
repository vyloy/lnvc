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
    
    <title>My JSP 'tab_bottom.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/tab.css">

  </head>
  
  <body>
  	<div class="tab_bottom_container">
  		<div class="tab_bottom_info">
  			<s:text name="page.text.subpage.info">
  				<s:param><s:property value="#request.pageMap.resultCont" /></s:param>
  				<s:param><s:property value="#request.pageMap.currentPage" /></s:param>
  				<s:param><s:property value="#request.pageMap.pageCont" /></s:param>
  			</s:text>
  		</div>
  		<s:set name="page" value="#request.pageMap"></s:set>
  		<div class="tab_bottom_page">
  			<s:if test="#page.currentPage > 1">
  				<a href="<s:property value="#page.topage" />">
  					<s:text name="page.text.subpage.fist" />
  				</a>
  				<a href="<s:property value='#page.topage' />?currentPage=<s:property value='#page.currentPage-1' />">
  					<s:text name="page.text.subpage.pre" />
  				</a>
  			</s:if>
  			<s:else>
  				<a href="javascript:void(0);" class="off">
  					<s:text name="page.text.subpage.fist" />
  				</a>
  				<a href="javascript:void(0);" class="off">
  					<s:text name="page.text.subpage.pre" />
  				</a>
  			</s:else>
  			
  			<s:if test="#page.currentPage < #page.pageCont">
  				<a href="<s:property value='#page.topage' />?currentPage=<s:property value='#page.currentPage+1' />">
  					<s:text name="page.text.subpage.next" />
  				</a>
  				<a href="<s:property value='#page.topage+"?currentPage="+#page.pageCont' />">
  					<s:text name="page.text.subpage.last" />
  				</a>
  			</s:if>
  			<s:else>
  				<a href="javascript:void(0);" class="off">
  					<s:text name="page.text.subpage.next" />
  				</a>
  				<a href="javascript:void(0);" class="off">
  					<s:text name="page.text.subpage.last" />
  				</a>
  			</s:else>
  			<span><s:text name="page.text.subpage.jumpto" /></span>
			<form action="<s:property value='#page.topage'/>" class="tab_bottom_form">
  				<input type="text" name="currentPage" id="currentPage" />
  			</form>
  			<span><s:text name="page.text.subpage.page" /></span>
  			<a href="javascript:void(0)" class="tab_pagejump" alt="<s:property value='#page.pageCont' />">
  				<s:text name="page.text.subpage.jump" />
  			</a>
  		</div>
  	</div>
  </body>
</html>
