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
  <script>
  	function restartServer(){
  		var f = window.confirm("确定重启服务器吗？");
  		if(f){
  			window.location = 'app/sysServerConfigAction_restartServer_sysserverconfig.action';
  		}
  	}
  </script>
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
		
		<!-- 
					<img src="images/tab_edit.gif">
		 -->
			
			<s:if test="buttonMap.edit">
				<li>
					<a href="javascript:restartServer();" class="toplink">
						<s:text name="重启系统服务" />
					</a>
				</li>
			</s:if>
			
			
			</ul>
    	</div>
  </body>
</html>
