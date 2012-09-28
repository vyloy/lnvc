<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin_mcu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/tab.css">
	<script type="text/javascript" src="js/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/tab.js" charset="utf-8"></script>
	
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/dialogue.js" charset="utf-8"></script>
	
  <body style="overflow: auto;">
  	<s:include value="/WEB-INF/pages/include/sys_tab_top.jsp">
  		<s:param name="title">
  			<s:text name="系统配置" />
  		</s:param>
  		<!-- 
  		<s:param name="selectAll">1</s:param>
  		<s:param name="addUrl">app/mcuAction_addMcu_mcu_edit.action*460*300*add</s:param>
  		<s:param name="searchUrl">search</s:param>
  		<s:param name="deleteUrl">app/sysServerConfigAction_editconfig_sysserverconfig_edit.action*delete</s:param>
  		<s:param name="editUrl">app/sysServerConfigAction_editconfig_sysserverconfig_edit.action*edit</s:param>
  		 -->
  	</s:include>
  	<table align="center" border="0" width="100%">
  	<tr>
  	<td>
  	
  	<s:if test="errorMsg.equals('')" >
  	
  	<s:form id="dialogue_form" action="app/sysServerConfigAction_saveconfig_sysserverconfigsuccess.action" validate="true">
  		<s:token />
    		
    		<fieldset>
				<legend>MCU服务配置</legend>
			<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
		    		<colgroup>
		    			<col style="width: 20%;"  />
		    			<col style="width: 30%;" />
		    			<col style="width: 20%;"  />
		    			<col style="width: 30%;" />
		    		</colgroup>
    		<tr>
    			<td class="dialogue_td_text">
    				CSIP
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.mcuserverip"  cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		<!-- 
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.mcuport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		 -->
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.mcu.url" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.serverUrl" cssStyle="width:300"  />
    			</td>
    		</tr>
    		<!-- 
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csip" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.csIP" cssStyle="width:300" theme="simple" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csport" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.csPort" cssStyle="width:300"  />
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csusername" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.csUsername" cssStyle="width:300" theme="simple" />
    			</td>
    			
    		</tr>
    		<tr>
    			
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.csuserpasswd" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.csUserPasswd" cssStyle="width:300" />
    			</td>
    		</tr>
    		 -->
			</table>	
			</fieldset>
    		

    		
    		<fieldset>
				<legend>LCM配置</legend>
    		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.sysconfig.customerCodeLength"></s:text>
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.customcodelength" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.sysconfig.confNoLength"></s:text>
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.confnolength" cssStyle="width:300" theme="simple"/>
    				<span class="field_desc">注：会议号码=客户号码+标识位(1位)+随机数，会议号码位数>客户号码位数+1</span>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.sysconfig.lccnoLength"></s:text>
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.lccnolength" cssStyle="width:300" theme="simple"/>
    				<span class="field_desc">注：用户号码=客户号码+随机数，用户号码位数>客户号码位数</span>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				最大会议数量
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.lcmconfnumber" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		

    		
    		</table>	
			</fieldset>
			
    		<fieldset>
				<legend>白板服务配置</legend>
    		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		<!-- 
    		<tr>
    			<td class="dialogue_td_text">
    				
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.whiteport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		 -->
    		
    		<tr>
    			<td class="dialogue_td_text">
    				
    				<s:text name="page.sysserverconfig.whitemaxnumber" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.whitenumber" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		</table>	
			</fieldset>
    		
    		<fieldset>
				<legend>邮件服务配置</legend>
    		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				服务器地址
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.emailserver" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.emailport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				
    				<s:text name="page.sysserverconfig.username" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.emailacount" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				
    				<s:text name="page.sysserverconfig.password" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.emailpassword" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		</table>	
			</fieldset>
    		
    		
    		<fieldset>
				<legend>即时通讯配置</legend>
    		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		
    		<!-- 
    		<tr>
    			<td class="dialogue_td_text">
    				即时通讯IP
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.openfireip" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		 -->
    		<tr>
    			<td class="dialogue_td_text">
    				
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.openfireport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				超时
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.openfiretimeout" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		</table>	
			</fieldset>
    		
    		<!--  -->
    		<fieldset>
				<legend>短信猫</legend>
    		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				通讯端口
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.serialport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		</table>	
			</fieldset>
    		
    		<!--  -->
    		
    		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		</td>
    		</tr>
    		<tr>
    		<td  colspan="4" align="center" >
    		<s:submit name="save" value=" 保  存  "  cssStyle="width:50"></s:submit> 
    		
    		<s:reset name="reset" value=" 取  消   " cssStyle="width:50"></s:reset>
    		</td>
    		</tr>
    		<tr>
    		<td class="dialogue_td_text"></td>
    		<td class="dialogue_td_input" colspan="3" >
    		</td>
    		</tr>
    		
    		<tr>
    		<td  colspan="4" align="center" >
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
  	<jsp:include flush="true" page="/WEB-INF/pages/include/tab_bottom.jsp" />
  	</s:if>
  	<s:else>
  		<div class="msgDiv"><s:property value="errorMsg"/></div>
  	</s:else>
  	
  	</td>
  	</tr>
  	</table>
  </body>
</html>