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
	
  <body>
  	<s:include value="/WEB-INF/pages/include/tab_top.jsp">
  		<s:param name="title">
  			<s:text name="page.display.text.resourceadmin.mcuconfig" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<s:param name="editUrl">app/mcuAction_toEditMcu_mcu_edit.action*460*300*edit</s:param>
  		<s:param name="addUrl">app/mcuAction_addMcu_mcu_edit.action*460*300*add</s:param>
  		<s:param name="deleteUrl">app/mcuAction_deleteMcu_admin_mcu.action*delete</s:param>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
  	<div class="tab_content_container">
  	
  	<s:if test="errorMsg.equals('')" >
  	
  	<s:form id="dialogue_form" action="app/sysServerConfigAction_saveconfig_sysserverconfig.action" validate="true">
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
    				<s:text name="page.sysserverconfig.serverip" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.serverip" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
            <tr>
    		<td align="center">MCU服务配置</td>
    		<td class="dialogue_td_input" colspan="3" theme="simple">
    		<hr style="color:gray;width: 400" />
    		</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.mcuport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		
    		
    		<tr>
    		<td align="left">CS服务配置</td>
    		<td class="dialogue_td_input" colspan="3" theme="simple">
    		<hr style="color:gray;width: 400" />
    		</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.csport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.cstimeout" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.cstimeout" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.username" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.csusername" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.password" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.cspassword" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    		<td align="left">文件服务配置</td>
    		<td class="dialogue_td_input" colspan="3" theme="simple">
    		<hr style="color:gray;width: 400" />
    		</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.fileport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.filemaxlimit" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.filemaxlimit" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.filetype" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.allownfilestyle" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    		<td align="left">桌面共享服务配置</td>
    		<td class="dialogue_td_input" colspan="3" theme="simple">
    		<hr style="color:gray;width: 400" />
    		</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.password" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.desktoppassword" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.desktopport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />2
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.desktopport2" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />3
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.desktopport3" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		
    		<tr>
    		<td align="left">白板服务配置</td>
    		<td class="dialogue_td_input" colspan="3" theme="simple">
    		<hr style="color:gray;width: 400" />
    		</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.whiteport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.whitemaxnumber" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.whitenumber" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    		<td align="left">邮件服务配置</td>
    		<td class="dialogue_td_input" colspan="3" theme="simple">
    		<hr style="color:gray;width: 400" />
    		</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.emailport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.username" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.emailacount" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.password" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.emailpassword" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		<tr>
    		<td align="left">Openfire服务配置</td>
    		<td class="dialogue_td_input" colspan="3" theme="simple">
    		<hr style="color:gray;width: 400" />
    		</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.sysserverconfig.port" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="sysServerconfigBean.openfireport" cssStyle="width:300" theme="simple"/>
    			</td>
    		</tr>
    		
    		
    		</td>
    		</tr>
    		<tr>
    		<td class="dialogue_td_text"></td>
    		<td class="dialogue_td_input" colspan="3" >
    		</td>
    		</tr>
    		
    		<tr>
    		<td  colspan="4" align="center" >
    		<s:submit name="save" value=" 确定 " align="center" cssStyle="width:40"></s:submit> 
    		
    		<s:reset name="reset" value=" 重置  " align="center" cssStyle="width:40"></s:reset>
    		</td>
    		</tr>
    		
    		<tr>
    		<td class="dialogue_td_text"></td>
    		<td class="dialogue_td_input" colspan="3" >
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
    </div>
  </body>
</html>
