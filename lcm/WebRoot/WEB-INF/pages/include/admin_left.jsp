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
    			<li class="menu"><s:text name="page.display.text.resourceadmin" /></li>
    			<li class="menu_list">
					<!-- 
					<div><a href="app/mcuAction_toMcuListPage_admin_mcu.action" target="index_iframe"
							title="<s:text name="page.display.text.resourceadmin.mcuconfig" />">
						<s:text name="page.display.text.resourceadmin.mcuconfig" />
					</a></div>
					 -->
					
					<div><a href="app/sysServerConfigAction_searchconfig_sysserverconfig.action" target="index_iframe"
							title="<s:text name="page.display.text.resourceadmin.sysconfig" />">
						<s:text name="page.display.text.resourceadmin.sysconfig" />
					</a></div>
					<div><a href="app/systemParasAction_toList_parasList.action" target="index_iframe"
							title="<s:text name="page.systemparas.set" />">
						<s:text name="page.systemparas.set" />
					</a></div>
					<!-- 会议录音管理 
					<div><a href="app/recordMediaAction_toRecordList_recordMediaList.action" target="index_iframe"
							title="<s:text name="page.display.text.resourceadmin.recordmediaconfig" />">
						<s:text name="page.display.text.resourceadmin.recordmediaconfig" />
					</a></div>
					 -->
					
					<%--<div><a href="app/toPage_pages_conf_conflist.action" target="index_iframe"
							title="<s:text name="page.display.text.resourceadmin.sysstatus" />">
						<s:text name="page.display.text.resourceadmin.sysstatus" />
					</a></div>
					<div><a href="app/conferenceAction_toOngoningConfs_conflist.action" target="index_iframe"
							title="<s:text name="page.display.text.resourceadmin.ongoingconf" />">
						<s:text name="page.display.text.resourceadmin.ongoingconf" />
					</a></div>--%>
				</li> 
    			<li class="menu"><s:text name="page.display.text.useradmin" /></li>
    			<li class="menu_list" >
    				<div><a href="app/customerAction_toEditCustomer_customer_edit.action" target="index_iframe"
							title="<s:text name="page.display.text.useradmin.customeradmin" />">
						<s:text name="page.display.text.useradmin.customeradmin" />
					</a></div>
					<div><a href="app/organAction_showOrgan_organ_list.action" target="index_iframe"
							title="<s:text name="page.organ.title" />">
						<s:text name="page.organ.title"></s:text>
					</a></div>				
					<!-- <div><a href="app/userAction_toInnerUserList_user_list.action" target="index_iframe"
							title="<s:text name="page.display.text.useradmin.customeruseradmin" />">
						<s:text name="page.display.text.useradmin.customeruseradmin"></s:text>
					</a></div> -->
					<!--<div><a href="app/userAction_toSystemUserList_user_list.action" target="index_iframe"
							title="<s:text name="page.display.text.useradmin.systemuseradmin" />">
						<s:text name="page.display.text.useradmin.systemuseradmin"></s:text>
					</a></div>-->
					<!--<div><a href="app/userAction_toMemberUserList_user_list.action" target="index_iframe"
							title="<s:text name="page.display.text.useradmin.memberadmin" />">
						<s:text name="page.display.text.useradmin.memberadmin"></s:text>
					</a></div>-->
    			</li>
    			<li class="menu"><s:text name="page.display.text.logadmin" /></li>
    			<li class="menu_list">
					<div><a href="app/loggerAction_toLoggerList_loggerlist.action" target="index_iframe"
							title="<s:text name="page.display.text.logadmin.oplogadmin" />">
						<s:text name="page.display.text.logadmin.oplogadmin"></s:text>
					</a></div>
					<%--<div><a href="app/toPage_pages_conf_conflist.action" target="index_iframe"
							title="<s:text name="page.display.text.logadmin.explogadmin" />">
						<s:text name="page.display.text.logadmin.explogadmin"></s:text>
					</a></div>--%>
				</li>
    			<%--<li class="menu"><s:text name="page.display.text.costadmin" /></li>
    			<li class="menu_list">
    				<div><a href="app/ratesAction_toRatesList_rateslist.action" target="index_iframe"
							title="<s:text name="page.display.text.costadmin.tariffconf"/>">
						<s:text name="page.display.text.costadmin.tariffconf"></s:text>
					</a></div>
					<div><a href="app/billingAction_toBillingList_billinglist.action" target="index_iframe"
							title="<s:text name="page.display.text.costadmin.billadmin"/>">
						<s:text name="page.display.text.costadmin.billadmin"></s:text>
					</a></div>
					<div><a href="app/toPage_pages_conf_conflist.action" target="index_iframe"
							title="<s:text name="page.display.text.logadmin.arrears" />">
						<s:text name="page.display.text.costadmin.arrears"></s:text>
					</a></div>
    			</li> --%>
    			<!-- 权限管理 -->
    			<li class="menu"><s:text name="page.display.text.authoritymanager" /></li>
    			<li class="menu_list">
    				<!--<div><a href="app/conferenceTypeAction_toConferenceTypeList_conferencetypelist.action" target="index_iframe"
							title="<s:text name="page.display.text.authoritymanager.conferencetypedefine"/>">
						<s:text name="page.display.text.authoritymanager.conferencetypedefine"></s:text>
					</a></div>-->
					<div><a href="app/conferenceRoleAction_toConferenceRoleList_conferencerolelist.action" target="index_iframe"
							title="<s:text name="page.display.text.authoritymanager.roledefine"/>">
						<s:text name="page.display.text.authoritymanager.roledefine"></s:text>
					</a></div>
					<div><a href="app/authorityAction_toAuthorityList_authoritylist.action" target="index_iframe"
							title="<s:text name="page.display.text.authoritymanager.authoritydefine"/>">
						<s:text name="page.display.text.authoritymanager.authoritydefine"></s:text>
					</a></div>
					<!--  <div><a href="app/conferenceNewAction_toConferenceNewList_conferencenewlist.action" target="index_iframe">
						<s:text name="page.display.text.conferencenew" />
					</a></div>-->
    			</li>
    			<%--<li class="menu"><s:text name="page.display.text.mediaadmin" /></li>
    			<li class="menu_list">
    				<div><a href="app/toPage_pages_conf_conflist.action" target="index_iframe"
							title="<s:text name="page.display.text.mediaadmin.advadmin" />">
						<s:text name="page.display.text.mediaadmin.advadmin"></s:text>
					</a></div>
					<div><a href="app/toPage_pages_conf_conflist.action" target="index_iframe"
							title="<s:text name="page.display.text.mediaadmin.videoadmin" />">
						<s:text name="page.display.text.mediaadmin.videoadmin"></s:text>
					</a></div>
    			</li>--%>
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