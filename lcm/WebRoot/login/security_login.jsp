<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'security_login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body onload='document.f.j_username.focus();'> 
<h3>Login with Username and Password</h3><form name='f' action='/lcm/j_spring_security_check' method='POST'> 
 <table> 
    <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr> 
    <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr> 
    <tr><td><input type='checkbox' name='_spring_security_remember_me'/></td><td>Remember me on this computer.</td></tr> 
    <tr><td colspan='2'><input name="submit" type="submit"/></td></tr> 
    <tr><td colspan='2'><input name="reset" type="reset"/></td></tr> 
  </table> 
</form></body>
</html>
