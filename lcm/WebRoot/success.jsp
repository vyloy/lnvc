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
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
  </head>
  
  <body>
    <s:hidden name="callBackUrl" cssClass="callbackUrl"></s:hidden>
    <s:hidden name="title" cssClass="title" value="%{getText('page.general.confirmTitle')}"></s:hidden>
    <s:hidden name="title" cssClass="sucessMsg" value="%{getText('page.general.opsucess')}"></s:hidden>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			var callbackUrl = $(".callbackUrl").attr("value");
  			var title = $(".title").attr("value");
  			var sucessMsg = $(".sucessMsg").attr("value");
  			var callback = false;
  			if(callbackUrl){
  				callback = function(){
  					if(callbackUrl.indexOf("user_list.action")!=-1){
  						$(window.parent.document).contents().find(".index_iframe").contents().find(".user_frame").attr("src",callbackUrl);
  					}else if(callbackUrl.indexOf("add_dept_ok")!=-1 || callbackUrl.indexOf("modify_dept_ok")!=-1){
						window.parent.frames["index_iframe"].refreshNode();
  					}else{
 						$(window.parent.document).contents().find(".index_iframe").attr("src",callbackUrl);
 					}
 					
  				};
  			}
  			window.parent.openConfirm(title,sucessMsg,true,false,callback);
  		});
  	</script>
  </body>
</html>
