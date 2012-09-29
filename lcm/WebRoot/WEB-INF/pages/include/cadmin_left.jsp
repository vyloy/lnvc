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
  	<security:authorize ifAllGranted="ROLE_CUSTOMER_ADMIN">
    <div class="index_left_container">
    	<div class="index_left_menu">
    			<ul class="index_left_ul" id="index_left_ul">
    				<li class="menu"><s:text name="page.display.text.confadmin" /></li>
    				<li class="menu_list">
    					<!-- <div><a href="app/conferenceAction_toOngoningConfs_conflist.action" target="index_iframe">
							<s:text name="page.display.text.onconf" />
						</a></div>
						<div><a href="app/conferenceAction_toImdConfs_conflist.action" target="index_iframe">
							<s:text name="page.display.text.impconf" />
						</a></div>
						<div><a href="app/conferenceAction_toAppConfs_conflist.action" target="index_iframe">
							<s:text name="page.display.text.appconf" />
						</a></div>
						<div><a href="app/cronConferenceAction_toCronConfList_cronconflist.action" target="index_iframe">
							<s:text name="page.display.text.perconf" />
						</a></div>
						<div><a href="app/confmeetingAction_toConfMeetingList_confmeeting_list.action" target="index_iframe">
							<s:text name="page.display.text.confmeeting" />
						</a></div> -->
						<div><a href="app/conferenceNewAction_toConferenceNewList_conferencenewlist.action" target="index_iframe">
							<s:text name="page.display.text.conferencenew" />
						</a></div>
					</li>
    			<!--<li class="menu"><s:text name="page.display.text.depadmin" /></li>
    			<li class="menu_list" >
					<div><a href="app/departmentAction_toDepList_departmentlist.action" target="index_iframe">
						<s:text name="page.display.text.struct" />
					</a></div>
					<div><a href="app/toPage_pages_conf_conflist.action" target="index_iframe">
						<s:text name="page.display.text.conflist" />
					</a></div>
					<div><a href="app/toPage_pages_conf_conflist.action" target="index_iframe">
						<s:text name="page.display.text.confrecord" />
					</a></div>
				</li>-->
    			<%--<li class="menu"><s:text name="page.display.text.roleadmin" /></li>
    			<li class="menu_list">&nbsp;</li>--%>
    			<!-- <li class="menu"><s:text name="page.display.text.useradmin" /></li>
    			<li class="menu_list">
    				<div><a href="app/userAction_toCustomerUserList_user_list.action" target="index_iframe">
						<s:text name="page.display.text.useradmin" />
					</a></div>
    			</li> -->
    			<!-- <li class="menu"><s:text name="page.display.text.mediaadmin" /></li>
    			<li class="menu_list">
    				<div><a href="app/mediaAction_toMediaList_media_list.action" target="index_iframe">
						<s:text name="page.display.text.mediaadmin" />
					</a></div>
    			</li> -->
    		</ul>
    		<img src="images/left_menu.gif" class="left_menu_img" />
    	</div>
    	<div class="left_menu_hide">
    		<img src="images/index_left_hide_button.gif"
    			 class="left_hide_img"
    			 	title="<s:text name='page.left.hide.tip' />" />
    	</div>
    </div>
    </security:authorize>
  </body>
</html>