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
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/tab.js"></script>
  <body>
  	<s:include value="/WEB-INF/pages/include/tab_top.jsp">
  		<s:param name="title">
  			<s:text name="page.display.text.useradmin.customer" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<s:param name="editUrl">app/customerAction_toEditCustomer_customer_edit.action*600*400*edit</s:param>
  		<s:param name="addUrl">app/customerAction_toAddCustomer_customer_edit.action*600*400*add</s:param>
  		<s:param name="deleteUrl">app/customerAction_deleteCustomer_admin_customer.action*delete</s:param>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
  	<div class="tab_content_container">
  	<div class="search_container">
  		<s:form namespace="/app" action="customerAction_searchCustomer_admin_customer" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.text.customername" />
  			<s:textfield name="customer.customerName" theme="simple" />
  			<!--<s:text name="page.text.customeradmin" />
  			<s:textfield name="customer.customerAdmin.username" theme="simple" />-->
  			<s:text name="page.text.customercode" />
  			<s:textfield name="customer.customerCode" theme="simple" />
  			<s:text name="page.text.status" />
  			<s:select list="statusList" name="customer.customerStatus" headerKey=""
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
  	<s:if test="errorMsg.equals('')">
  	<form action="app/customerAction_deleteCustomer_admin_customer.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:10%" />
    			<col style="width:10%" />
    			<col style="width:7%" />
    			<!-- <col style="width:7%" />
    			<col style="width:7%" />
    			<col style="width:7%" />
    			<col style="width:7%" /> -->
    			<col style="width:12%" />
    			<col style="width:6%" />
    			<col style="width:6%">
    			<!--<col style="width:10%" />
    			<col style="width:10%" /> -->
    			<col style="width:10%" />
    			<col style="width:6%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/customerAction_sortCustomer_admin_customer.action"/>
    			</div></td>
    			<td sortby="customerName"><div><s:text name="page.text.customername" /></div></td>
    			<td sortby="customerCode"><div><s:text name="page.text.customercode" /></div></td>
    			<td sortby="customerUserlimit"><div><s:text name="page.text.customeruserlimit" /></div></td>
    			<!-- <td sortby="imdConfNoLimit"><div><s:text name="page.text.imdconfnolimit" /></div></td>
    			<td sortby="appConfNoLimit"><div><s:text name="page.text.appconfnolimit" /></div></td>
    			<td sortby="perConfNoLimit"><div><s:text name="page.text.perconfnolimit" /></div></td>
    			<td sortby="confMeetingNoLimit"><div><s:text name="page.text.confmeetingnolimit" /></div></td> -->
    			<td sortby="customerAddress"><div><s:text name="page.text.address" /></div></td>
    			<td sortby="customerContact"><div><s:text name="page.text.contact" /></div></td>
    			<td sortby="customerPhone"><div><s:text name="page.text.phone" /></div></td>
    			<!-- <td sortby="mcuServer"><div><s:text name="page.text.mcuname" /></div></td>
    			<td sortby="rates"><div><s:text name="page.customer.rates" /></div></td>-->
    			<td sortby="customerDesc"><div><s:text name="page.text.desc" /></div></td>
    			<td sortby="customerStatus"><div><s:text name="page.text.status" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="customer">
    			<tr class="tab_data_tr">
    				<td>
    					<input type="checkbox" name="choose"
    					 	value="<s:property value='#customer.id' />" />
    				</td>
    				<td title="<s:property value="#customer.customerName" />">
    					<s:property value="#customer.customerName" />
    				</td>
    				<td title="<s:property value="#customer.customerCode" />">
    					<s:property value="#customer.customerCode" />
    				</td>
    				<td title="<s:property value="#customer.customerUserlimit" />">
    					<s:property value="#customer.customerUserlimit" />
    				</td>
    				<!-- <td title="<s:property value="#customer.imdConfNoLimit" />">
    					<s:property value="#customer.imdConfNoLimit" />
    				</td>
    				<td title="<s:property value="#customer.appConfNoLimit" />">
    					<s:property value="#customer.appConfNoLimit" />
    				</td>
    				<td title="<s:property value="#customer.perConfNoLimit" />">
    					<s:property value="#customer.perConfNoLimit" />
    				</td>
    				<td title="<s:property value="#customer.confMeetingNoLimit" />">
    					<s:property value="#customer.confMeetingNoLimit" />
    				</td> -->
    				<td title="<s:property value="#customer.customerAddress" />">
    					<s:property value="#customer.customerAddress" />
    				</td>
    				<td title="<s:property value="#customer.customerContact" />">
    					<s:property value="#customer.customerContact" />
    				</td>
    				<td title="<s:property value="#customer.customerPhone" />">
    					<s:property value="#customer.customerPhone" />
    				</td>
    				<!--<td title="<s:property value="#customer.mcuServer.serverName" />">
    					<s:property value="#customer.mcuServer.serverName" />
    				</td>
    				<td title="<s:property value="#customer.rates.ratesName" />">
    					<s:property value="#customer.rates.ratesName" />
    				</td>-->
    				<td title="<s:property value="#customer.customerDesc"/>">
    					<s:property value="#customer.customerDesc"/>
    				</td>
    				<td>
    					<s:set name="status" value="#customer.customerStatus" />
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
