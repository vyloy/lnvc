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
    
    <title>My JSP 'mcu_edit.jsp' starting page</title>
    
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
  	<s:form id="dialogue_form" namespace="/app" action="mcu_doEditMcu_mcu_edit" validate="true">
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
    				<s:text name="page.text.mcuname" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="server.serverName" theme="simple" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text"><s:text name="page.text.status" /></td>
    			<td class="dialogue_td_input">
    				<s:select list="statusList" value="server.serverStatus" name="server.serverStatus"
    					 headerKey="0" theme="simple">
    				</s:select>
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.mcu.url" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="server.serverUrl" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csip" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="server.csIP" theme="simple" />
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csport" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="server.csPort" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csusername" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="server.csUsername" theme="simple" />
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csuserpasswd" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="server.csUserPasswd" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text"><s:text name="page.text.desc" /></td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textarea name="server.serverDesc" cssClass="textarea" theme="simple" />
    			</td>
    		</tr>
    	</table>
  	</s:form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  	<div class="disable_tr mcu_disable_dialogue">
    	<form class="dialogue_inner_form" action="ajax/mcu_addMixer.action">
    		<span><s:text name="page.text.mixername" /></span>
    		<input type="text" name="mixerName" /><br/>
    		<span><s:text name="page.text.mixerkey" /></span>
    		<input type="text" name="mixerKey" /><br/>
  			<a href="javascript:void(-1)" class="click_to_cancel">
  				<s:text name="page.text.cancel" />
  			</a> 
  			<a href="javascript:void(-1)" class="click_to_submit">
  				<s:text name="page.text.ok" />
  			</a>
  		</form>
    </div>
  </body>
</html>
