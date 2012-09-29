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
  	<div class="search_container">
  		<s:form action="app/mcuAction_searchMcu_admin_mcu.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
    		<s:hidden name="isSearch" value="true" theme="simple" />
  			<s:text name="page.text.mcuname" />
  			<s:textfield name="server.serverName" theme="simple" />
  			<s:text name="page.text.mcuip" />
  			<s:textfield name="server.serverIp" theme="simple" />
  			<s:text name="page.text.status" />
  			<s:select list="statusList" name="server.serverStatus" headerKey=""
  				 headerValue="" theme="simple">
    		</s:select>
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
  	<s:if test="errorMsg.equals('')" >
  	<form action="app/mcuAction_deleteMcu_admin_mcu.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:12%" />
    			<col style="width:13%" />
    			<col style="width:15%" />
    			<col style="width:27%" />
    			<col style="width:5%" />
    			<col style="width:20%" />
    			<col style="width:5%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/mcuAction_sortMcu_admin_mcu.action"/>
    			</div></td>
    			<td sortby="serverName"><div><s:text name="page.text.mcuname" /></div></td>
    			<td sortby="csIP"><div><s:text name="page.text.csip" /></div></td>
    			<td sortby="serverUrl"><div><s:text name="page.mcu.url" /></div></td>
    			<td sortby="serverDesc"><div><s:text name="page.text.desc" /></div></td>
    			<td ><div><s:text name="page.text.mcumenbercont" /></div></td>
    			<td ><div><s:text name="page.text.mcumixer" /></div></td>
    			<td sortby="serverStatus"><div><s:text name="page.text.status" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="server">
    			<tr class="tab_data_tr">
    				<td>
    					<input type="checkbox" name="choose"
    					 	value="<s:property value='#server.id' />" />
    				</td>
    				<td title="<s:property value="#server.serverName" />">
    					<s:property value="#server.serverName" />
    				</td>
    				<td title="<s:property value="#server.csIP" />">
    					<s:property value="#server.csIP" />
    				</td>
    				<td title="<s:property value="#server.serverUrl" />">
    					<s:property value="#server.serverUrl" />
    				</td>
    				<td title="<s:property value="#server.serverDesc" />">
    					<s:property value="#server.serverDesc" />
    				</td>
    				<td title="<s:property value="#server.customers.size()"/>">
    					<%--<s:property value="#server.customers.size()"/>--%>
    					<s:property value="#server.customerSize" />
    				</td>
    				<s:set name="mixersStr" value="''" />
    				<s:iterator value="#server.mixers" id="mixer" >
    					<!--<s:property value="#mixer.mixerName" />&nbsp;-->
    					<s:set name="mixersStr" value="#mixersStr + #mixer.mixerName+' '" />
    				</s:iterator>
    				<s:property value="#mixers"/>
    				<td title="<s:property value="#mixersStr" />">
    					<s:property value="#mixersStr" />
    				</td>
    				<td>
    					<s:set name="status" value="#server.serverStatus" />
    					<s:if test="#status.equals(0)">
    						<div title="<s:text name="page.record.status.notactivated" />">
    							<s:text name="page.record.status.notactivated" />
    						</div>	
    					</s:if>
    					<s:elseif test="#status.equals(1)">
    						<div title="<s:text name="page.record.status.activated" />">
    							<s:text name="page.record.status.activated" />
    						</div>
    					</s:elseif>
    					<s:elseif test="#status.equals(2)">
    						<div title="<s:text name="page.record.status.stop" />">
    							<s:text name="page.record.status.stop" />
    						</div>
    					</s:elseif>
    					<s:elseif test="#status.equals(-1)">
    						<div title="<s:text name="page.record.status.delete" />">
    							<s:text name="page.record.status.delete" />
    						</div>
    					</s:elseif>
    				</td>
    			</tr>
    		</s:iterator>
    	</table>
  	</form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/tab_bottom.jsp" />
  	</s:if>
  	<s:else>
  		<div class="msgDiv"><s:property value="errorMsg"/></div>
  	</s:else>
    </div>
  </body>
</html>
