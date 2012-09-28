<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" />
    <title><s:text name="generic.title" /></title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link rel="stylesheet" type="text/css" href="css/login2.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
  </head>
  <body>
  <table border="0" width="1003" height="680" cellspacing="0" cellpadding="0" >
	<tr><td><img src="images/home_logo.jpg" width="1003" height="229"/></td></tr>
	<tr><td><div class="contentContainer">
  	<form action="/lcm/j_spring_security_check" name="loginForm" method="post" class="loginForm">
		<table class="inputUl" >
			<tr>
				<td ><s:text name="login.userName" /></td>
				<td colspan="2"><s:textfield name="j_username" /></td>
				<td>
					<button onclick="document.loginForm.submit();">
						<s:text name="login.login" />
					</button>
				</td>
			</tr>
			<tr>
				<td><s:text name="login.password" /></td>
				<td colspan="2"><s:password name="j_password" /></td>
				<td>
					<button onclick="document.loginForm.reset();">
						<s:text name="login.reset" />
					</button>
				</td>
			</tr>
			<tr>
				<td><s:text name="login.code" /></td>
				<td style="vertical-align:middle;">
					<s:textfield name="code" id="inputCode" onkeyup="if(event.keyCode==13){document.loginForm.submit();}"/>
				</td>
				<td>
					<img src="imageCode" title="<s:text name='login.code.message' />" id="imageCode" />
				</td>
				<td>  						
					<select title="<s:text name='login.lan.message' />" name="requestLocale" id="changeLan">
						<%--<option value=""><s:text name="login.lan.tip"/></option>--%>
						<option value="zh_CN"><s:text name="login.lan.zh" /></option>
						<%--<option value="en_US"><s:text name="login.lan.en" /></option>--%>
					</select>
				</td>
			</tr>
			<tr style="font-size: 12px;">
				<td>
  					<s:text name="login.remenberme" />
  				</td>
				<td style="text-align:left;" colspan="2">
  					<input type="checkbox" id="remenberme" name="_spring_security_remember_me" style="width:15px;"/>
  				</td>
  				<td style="text-align:right;">	
  					<a href="javascript:void(0);" onclick="javascript:window.open('<s:text name="lccfilepath" />');" ><s:text name="page.top.downloadlcc" /></a>
  				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="msgDiv">
						${login_error }
					</div>
				</td>
			</tr>
		</table>
  	</form>
  	</div></td></tr>
  	<tr><td>
  		<img src="images/home_down.jpg" width="1003" height="260"/>
  	</td></tr>
  	</table>
  </body>
</html>
