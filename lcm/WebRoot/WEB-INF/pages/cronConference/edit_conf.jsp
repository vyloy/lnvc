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
  	<s:form id="dialogue_form" namespace="/app" action="cronConference_doEditConf_edit_conf">
  		<s:token />
  		<sd:tabbedpanel id="conf_tabbedpanel" cssClass="tabbedpanel" closeButton="true">
  			<sd:div label="%{getText('page.text.baseinfo')}" cssClass="tabpanel_div">
  				<jsp:include flush="true" page="/WEB-INF/pages/cronConference/conf_baseinfo.jsp"/>
  			</sd:div>
  			<sd:div label="%{getText('page.text.conf.addusers')}" cssClass="tabpanel_div">
  				<jsp:include flush="true" page="/WEB-INF/pages/cronConference/conf_users.jsp"/>
  			</sd:div>
  			<sd:div label="%{getText('page.text.confpowerinfo')}" cssClass="tabpanel_div">
  				<jsp:include flush="true" page="/WEB-INF/pages/conferenceNew/confpowerinfo.jsp"/>
  			</sd:div>
  		</sd:tabbedpanel>
  	</s:form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  </body>
  <script type="text/javascript">
  	change();
  	$(document).ready(function(){
  		 $(".type_select").change(function(){
  		 	change();
	  	 })
  	});
  	function change(){
  		var value = $(".type_select").attr("value");
  			if(value==1){
  				$(".date_select_date").css("display","");
  				$(".date_select_week").css("display","none");
  				$(".date_select_date").attr("name","conf.cronTime");
  				$(".date_select_week").attr("name","");
  			}else{
  				$(".date_select_date").css("display","none");
  				$(".date_select_week").css("display","");
  				$(".date_select_date").attr("name","");
  				$(".date_select_week").attr("name","conf.cronTime");
  			}
  	}
  </script>
</html>
