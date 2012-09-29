<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sd" uri="/struts-dojo-tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'conflist.jsp' starting page</title>
    <sd:head/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
  </head>
  <body class="dialogue_body">
 <table border="0" cellspacing="0" cellpadding="0" class="dialogue_table tab_panel_table">
    <colgroup>
    	<col style="width: 20%;"  />
    	<col style="width: 30%;" />
    	<col style="width: 20%;"  />
    	<col style="width: 30%;" />
    </colgroup>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.confname" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.confSubject" disabled="true" />
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.callno" />
    	</td>
    	<td class="dialogue_td_input">
			<s:textfield name="conf.confno" disabled="true" />    			
    	</td>
    </tr>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.starttime" />
    	</td>
    	<td class="dialogue_td_input date_input">
    		<input type="text" disabled="true" value="<s:date name='conf.startTime' format='yyyy-MM-dd HH:mm' />" />
    	</td>
    	<td class="dialogue_td_text date_input">
    		<s:text name="page.text.endtime" />
    	</td>
    	<td class="dialogue_td_input">
    		<input type="text" disabled="true" value="<s:date name='conf.endTime' format='yyyy-MM-dd HH:mm' />" />
    	</td>
    </tr>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.host" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.confHost.username" disabled="true" />
    	</td>
    	
    	<td class="dialogue_td_text">
    		<s:text name="page.text.confpass" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:password name="conf.confPass" theme="simple" disabled="true" />
    	</td>
    </tr>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.confmode" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="confModeList" name="conf.confMode" disabled="true" />
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.memberlimit" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.confMemberCount" disabled="true" />
    	</td>
    </tr>
    
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.conf.mixerkey" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.mcuMixerKey" disabled="true" />
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.conf.medialayout" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="mcuMediaLayout" name="conf.mcuMediaLayOut" disabled="true" />
    	</td>
    </tr>
    
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.public" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="yesNoList" name="conf.confPublic" disabled="true" />
    	</td>
    	
    	<!--  <td class="dialogue_td_text">
    		<s:text name="page.text.requiredlogin" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="yesNoList" name="conf.requiredLogin" disabled="true" />
    	</td>-->
    	
    	<s:if test="conf.confType==2">
    		<td class="dialogue_td_text">
    			<s:text name="page.conf.allowbefore" />
    		</td>
    		<td class="dialogue_td_input">
    			<s:select list="allowBeforeList" name="conf.confAllowBefore" disabled="true" />
    		</td>
    	</s:if>
    	<s:else>
    		<td class="dialogue_td_text">
    			<s:text name="page.conf.mediaquality" />
    		</td>
    		<td class="dialogue_td_input">
    			<s:select list="mediaQuality" name="conf.mcuMediaQuality" disabled="true" theme="simple" />
    		</td>
    	</s:else>
    </tr>
    
     <!--<tr>
    	 <td class="dialogue_td_text">
    		<s:text name="page.text.videolimit" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.confVideoCount" disabled="true" />
    	</td>
	
    </tr>-->
    <s:if test="conf.confType==2">
    	<tr>
    		<td class="dialogue_td_text">
    			<s:text name="page.conf.mediaquality" />
    		</td>
    		<td class="dialogue_td_input">
    			<s:select list="mediaQuality" name="conf.mcuMediaQuality" disabled="true" theme="simple" />
    		</td>
    		<td colspan="2"></td>
    	</tr>
    </s:if>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.desc" />
    	</td>
    	<td class="dialogue_td_input" colspan="3">
    		<s:textarea name="conf.confDesc" cssClass="dialogue_textarea" disabled="true" theme="simple" />
    	</td>
     </tr>
     <tr>
     	<td class="dialogue_td_text">
     		<s:text name="page.conf.memberuser" />
     	</td>
     	<td class="dialogue_td_input" colspan="3">
     		<s:set name="userStr" value="''" />
     		<s:iterator id="user" value="conf.users">
     			<%--<s:property value="#user.username" />--%>
     			<s:set name="userStr" value="#userStr+' '+#user.username" />
     		</s:iterator>
     		<s:textarea name="userStr" cssClass="dialogue_textarea" disabled="true" theme="simple" />
     	</td>
     </tr>
</table>
</body>
</html>