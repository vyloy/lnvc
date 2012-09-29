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
  	<s:form id="dialogue_form" namespace="/app" action="conferenceType_doEditConferenceType_edit_conferencetype" validate="true">
  		<s:token />
  		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.conferencetypename" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="conferenceType.typeName" theme="simple" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text" style="width: 20%">
    				选择角色：
    			</td>
    			<td class="dialogue_td_input" style="width: 80%">
    				&nbsp;
    			</td>
   			</tr>
    	</table>
    	<div style="overflow-y:auto; height: 220px;width: 80%;">
	    	<table border="0" cellspacing="1" cellpadding="0" class="tab_data_table">
			<colgroup>
				
				<col style="width: 22%" />
				<col style="width: 22%" />
				<col style="width: 22%" />
			</colgroup>
			<% int i = 0; %>
			<s:iterator value="conferenceRoles" id="conferenceRole">
			<% i++; %>
			<% if((i%3)==1){%>
			<tr class="dialogue_tab_tr">
			<% } %>
			<td>
			<s:if test="selectedConferenceRoles!=null && selectedConferenceRoles.containsKey(#conferenceRole.id)">
				<input type="checkbox" checked="checked" name="choose" value="<s:property value='#conferenceRole.id' />" />
			</s:if>
			<s:else>
				<input type="checkbox" name="choose" value="<s:property value='#conferenceRole.id' />" />
			</s:else>
				<s:property value="#conferenceRole.roleName" /></td>
			<% if((i%3)==0){%>
			</tr>
			<% } %>
			</s:iterator>
			<% if((i%3)==1){%>
				<td>&nbsp;</td><td>&nbsp;</td></tr>
			<% } %>
			<% if((i%3)==2){%>
				<td>&nbsp;</td></tr>
			<% } %>
		</table>
		</div>
  	</s:form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  </body>
</html>
