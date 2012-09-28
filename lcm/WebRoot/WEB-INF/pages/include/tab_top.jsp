<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%--<%@page import="com.opensymphony.xwork2.util.ValueStack"%>--%>
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
    <title>My JSP 'tag_top.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/tab.css">
  </head>
  <body>
  	<%
  		request.setCharacterEncoding("utf-8");
  		for(Enumeration e = request.getParameterNames();e.hasMoreElements();){
			String name = (String)e.nextElement();
			request.setAttribute(name,request.getParameter(name));
		}
  	%>
    <div class="tab_top_container">
    	<div class="tab_top_title">
    		${param.title }
		</div>
		<ul class="tab_top_op_ul">
			<s:if test="buttonMap.selectAll">
				<li>
					<input type="checkbox" name="selectAll" class="selectAll" />
					<span><s:text name="page.text.selectAll" /></span>
				</li>
			</s:if>
			<s:if test="buttonMap.stopConf">
				<li>
					<img src="images/tab_top_stop.gif">
					<a href="javascript:void(-1);" id="${param.stopConfUrl}"
					 	title="<s:text name="page.text.stopconf" />" class="toplink">
						<s:text name="page.text.stopconf" />
					</a>
				</li>
			</s:if>
			<s:if test="buttonMap.closeBilling">
				<li>
					<img src="images/tab_edit.gif">
					<a href="javascript:void(-1);" id="${param.closeBillingUrl }"
				 		title="<s:text name="page.billing.closebilling" />" class="toplink">
						<s:text name="page.billing.closebilling" />
					</a>
				</li>
			</s:if>
			<s:if test="buttonMap.edit">
				<li>
					<img src="images/tab_edit.gif">
					<a href="javascript:void(-1);" id="${param.editUrl }"
				 		title="<s:text name="page.text.edit" />" class="toplink">
						<s:text name="page.text.edit" />
					</a>
				</li>
			</s:if>
			<s:if test="buttonMap.add">
				<li>
					<img src="images/tab_add.gif">
					<a href="javascript:void(-1)" id="${param.addUrl }"
						title="<s:text name="page.text.add" />" class="toplink">
						<s:text name="page.text.add" />
					</a>
				</li>
			</s:if>
			<s:if test="buttonMap.delete">
				<li>
					<img src="images/tab_del.gif">
					<a href="javascript:void(-1)" id="${param.deleteUrl }"
						title="<s:text name="page.text.del" />" class="toplink">
						<s:text name="page.text.del"/>
					</a>
				</li>
			</s:if>			

			<s:if test="buttonMap.search">
				<li>
					<img src="images/tab_search.gif">
					<a href="javascript:void(-1)" id="${param.searchUrl }"
						title="<s:text name="page.text.search" />" class="tab_top_search">
						<s:text name="page.text.search" />
					</a>
				</li>
			</s:if>
			
			<s:if test="buttonMap.statistics">
				<li>
					<img src="images/tab_search.gif">
					<a href="javascript:void(-1)" id="${param.statisticsUrl }"
						title="<s:text name="page.text.statistics" />" class="top_statistics">
						<s:text name="page.text.statistics" />
					</a>
				</li>
			</s:if>
			
			<s:if test="buttonMap.importUser">
				<li>
					<img src="images/tab_search.gif">
					<a href="javascript:void(-1)" id="${param.importUserUrl}"
						title="<s:text name="importuser.text" />" class="toplink">
						<s:text name="importuser.text" />
					</a>
				</li>
			</s:if>
			
			<s:if test="buttonMap.exportUser">
				<li>
					<img src="images/tab_search.gif">
					<a href="users"
						title="<s:text name="exportuser.text" />">
						<s:text name="exportuser.text" />
					</a>
				</li>
			</s:if>
			
			</ul>
    	</div>
  </body>
</html>
