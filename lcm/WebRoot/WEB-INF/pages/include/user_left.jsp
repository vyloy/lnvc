<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'left.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/index.css">
  </head>
  
  <body>
    <div class="index_left_container">
    	<div class="index_left_menu">
    		<ul class="index_left_ul" id="index_left_ul">
    			<security:authorize ifAllGranted="ROLE_CUSTOMER_USER">
	    			<li class="menu"><s:text name="page.display.text.mybuildconf" /></li>
	    			<li class="menu_list">
	   					<div><a href="app/conferenceAction_toOngoningConfs_conflist.action?belong=mybuild" target="index_iframe">
							<s:text name="page.display.text.onconf" />
						</a></div>
						<div><a href="app/conferenceAction_toImdConfs_conflist.action?belong=mybuild" target="index_iframe">
							<s:text name="page.display.text.impconf" />
						</a></div>
						<div><a href="app/conferenceAction_toAppConfs_conflist.action?belong=mybuild" target="index_iframe">
							<s:text name="page.display.text.appconf" />
						</a></div>
						<div><a href="app/cronConferenceAction_toCronConfList_cronconflist.action?belong=mybuild" target="index_iframe">
							<s:text name="page.display.text.perconf" />
						</a></div>
					</li>
	    			<li class="menu"><s:text name="page.display.text.myjoinconf" /></li>
	    			<li class="menu_list">
	   					<div><a href="app/conferenceAction_toOngoningConfs_conflist.action?belong=myjoin" target="index_iframe">
							<s:text name="page.display.text.onconf" />
						</a></div>
						<div><a href="app/conferenceAction_toImdConfs_conflist.action?belong=myjoin" target="index_iframe">
							<s:text name="page.display.text.impconf" />
						</a></div>
						<div><a href="app/conferenceAction_toAppConfs_conflist.action?belong=myjoin" target="index_iframe">
							<s:text name="page.display.text.appconf" />
						</a></div>
						<div><a href="app/cronConferenceAction_toCronConfList_cronconflist.action?belong=myjoin" target="index_iframe">
							<s:text name="page.display.text.perconf" />
						</a></div>
					</li>
				</security:authorize>
				<security:authorize ifAllGranted="ROLE_CUSTOMER_DEP_ADMIN">
					<li class="menu"><s:text name="page.display.text.deptconf" /></li>
	    			<li class="menu_list">
	   					<div><a href="app/conferenceAction_toOngoningConfs_conflist.action?belong=dept" target="index_iframe">
							<s:text name="page.display.text.onconf" />
						</a></div>
						<div><a href="app/conferenceAction_toImdConfs_conflist.action?belong=dept" target="index_iframe">
							<s:text name="page.display.text.impconf" />
						</a></div>
						<div><a href="app/conferenceAction_toAppConfs_conflist.action?belong=dept" target="index_iframe">
							<s:text name="page.display.text.appconf" />
						</a></div>
						<div><a href="app/cronConferenceAction_toCronConfList_cronconflist.action?belong=dept" target="index_iframe">
							<s:text name="page.display.text.perconf" />
						</a></div>
					</li>
				</security:authorize>
    		</ul>
    		<img src="images/left_menu.gif" class="left_menu_img" />
    	</div>
    	<div class="left_menu_hide">
    		<img src="images/index_left_hide_button.gif"
    			 class="left_hide_img"
    			 	title="<s:text name='page.left.hide.tip' />" />
    	</div>
    </div>
  </body>
</html>