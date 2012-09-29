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
  </head>
  
 <body class="dialogue_body">
 <s:form id="dialogue_form" namespace="/app" action="confmeeting_doSaveOrUpdateConf_confmeeting_baseinfo">
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
    		<s:text name="page.text.confname" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.confSubject" theme="simple" />
    	</td>
    		<td class="dialogue_td_text">
    		<span class="required">*</span>
   			<s:text name="page.conf.meetingtype" />
   		</td>
   		<td class="dialogue_td_input">
   		<s:if test="conf.id==null">
   		    <s:select list="mixOrTransList" name="conf.ismix" theme="simple" headerKey="" headerValue="请选择"></s:select>
   		</s:if>
   		<s:else>
   		    <s:select list="mixOrTransList" name="conf.ismix" theme="simple" disabled="true" headerKey="" headerValue="请选择"></s:select>
   		</s:else>
   		</td>
    </tr>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.public" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select id="ispublic" list="yesNoList" name="conf.confPublic" theme="simple" onchange="if(this.value==1){$('#userpasswd')[0].disabled='true';}if(this.value==2){$('#userpasswd')[0].disabled='';}"/>
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.confpass" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:password name="conf.realPassword" theme="simple" id="userpasswd" disabled="true"/>
    	</td>
    </tr>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.conf.medialayout" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="mcuMediaLayout" name="conf.mcuMediaLayOut"></s:select>
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.conf.mediaquality" />
    	</td>
    	<s:if test="conf.id == null">
	    	<td class="dialogue_td_input">
	    		<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" />
			</td>
		</s:if>
		<s:else>
			<td class="dialogue_td_input">
	    		<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" disabled="true"/>
			</td>
		</s:else>
    </tr>
	<tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.desc" />
    	</td>
    	<td class="dialogue_td_input" colspan="3">
    		<s:textarea name="conf.confDesc" cssClass="dialogue_textarea" theme="simple" />
    	</td>
	</tr>
</table>
</s:form>
<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
</body>
<script type="text/javascript">
  	$(document).ready(function(){
  		 var ispublic = $("#ispublic")[0].value;
  		 if(ispublic==1){
  		 	$("#userpasswd")[0].disabled='true';
  		 }
  		 if(ispublic==2){
  		 	$("#userpasswd")[0].disabled='';
  		 }
  	});
</script>
</html>
