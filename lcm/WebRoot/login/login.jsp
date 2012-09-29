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
    <link rel="icon" href="<%=basePath%>/images/animated_favicon1.gif" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=basePath%>/images/favicon.ico" type="image/x-icon" />   
    <title><s:text name="generic.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
  </head>
  <body>
  	<div class="contentContainer">
  		<div class="login_form">
  			<form action="j_spring_security_check" name="loginForm" method="post">
  				<ul class="inputUl">
  					<li>
  						<span><s:text name="login.userName" /></span>
  						<s:textfield name="j_username" tabindex="1"/>
  						<%--<input type="text" name="j_username" />--%>
  						<button onclick="document.loginForm.submit();">
  							<s:text name="login.login" />
  						</button>
  					</li>
  					<li>
  						<span><s:text name="login.password" /></span>
  						<s:password name="j_password" tabindex="2"/>
  						<button onclick="document.loginForm.reset();">
  							<s:text name="login.reset" />
  						</button>
  					</li>
  					<li>
  						<span><s:text name="login.code" /></span>
  						<s:textfield name="code" id="inputCode" onkeyup="if(event.keyCode==13){document.loginForm.submit();}" tabindex="3"/>
  						<img src="imageCode" title="<s:text name='login.code.message' />" id="imageCode" />
  						<select title="<s:text name='login.lan.message' />" name="requestLocale" id="changeLan">
  							<%--<option value=""><s:text name="login.lan.tip"/></option>--%>
  							<option value="zh_CN"><s:text name="login.lan.zh" /></option>
  							<%--<option value="en_US"><s:text name="login.lan.en" /></option>--%>
  						</select>
  					</li>
  				</ul>
  				<!-- 
  				<div class="remenberme_text">
  					<s:text name="login.remenberme" />
  				</div>
  				<div class="remenberme_input">	
  					<input type="checkbox" id="remenberme" name="_spring_security_remember_me" />
  				</div>
  				 -->
  				<!-- <div class="download_lcc">	
  					<a style="color:#fff;" href="javascript:void(0);" onclick="javascript:window.open('<s:text name="lccfilepath" />');" ><s:text name="page.top.downloadlcc" /></a>
  				</div> -->
  				
  			</form>
  		</div>
  		<div class="msgDiv">
  			${login_error }
  		</div>
  	</div>
  </body>
</html>
