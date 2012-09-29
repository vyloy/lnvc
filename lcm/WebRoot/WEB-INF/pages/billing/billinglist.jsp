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
	<link rel="stylesheet" type="text/css" href="css/tab.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/tab.js"></script>
  </head>
  <body>
  	<s:include value="/WEB-INF/pages/include/tab_top.jsp">
  		<s:param name="title">
  			<s:text name="page.display.text.costadmin.billadmin" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<security:authorize ifAnyGranted="ROLE_ADMIN">
			<%--<s:param name="editUrl">app/ratesAction_toEditRates_edit_rates.action*400*300*edit</s:param>
  			<s:param name="addUrl">app/ratesAction_addRates_edit_rates.action*400*300*add</s:param>
  			<s:param name="deleteUrl">app/ratesAction_deleteRates_rateslist.action*delete</s:param>--%>  		
  			<s:param name="closeBillingUrl">app/billingAction_closeBilling_billinglist.action*closeBill</s:param>
  			<s:param name="statisticsUrl">app/billingAction_toStatistics_billing_statistics.action</s:param>
  		</security:authorize>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
    <div class="tab_content_container">
    <div class="search_container user_list_search">
  		<s:form action="app/billingAction_searchBilling_billinglist.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.billing.customercode" />
  			<s:textfield name="billing.customerCode" theme="simple" />
  			<s:text name="page.billing.customername" />
  			<s:textfield name="billing.customerName" theme="simple" />
  			<s:text name="page.billing.ispay" />
  			<s:select list="payList" name="ispay" headerKey="" headerValue="" />
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
  	<s:if test="errorMsg.equals('')">
  	<form action="app/billingAction_closeBilling_billinglist.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:8%" />
    			<col style="width:10%" />
    			<col style="width:10%" />
    			<col style="width:15%" />
    			<col style="width:10%" />
    			<col style="width:10%" />
    			<col style="width:10%" />
    			<col style="width:10%" />
    			<col style="width:10%" />
    			<col style="width:8%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/billingAction_sortBilling_billinglist.action"/>
    			</div></td>
    			<td sortby="id"><div><s:text name="page.billing.billingid" /></div></td>
    			<td sortby="conference"><div><s:text name="page.billing.customercode" /></div></td>
    			<td sortby="conference"><div><s:text name="page.billing.customername" /></div></td>
    			<td sortby="conference"><div><s:text name="page.billing.confname" /></div></td>
    			<td sortby="conference"><div><s:text name="page.billing.confstarttime" /></div></td>
    			<td sortby="conference"><div><s:text name="page.billing.confendtime" /></div></td>
    			<td sortby="rates"><div><s:text name="page.billing.ratesname" /></div></td>
    			<td sortby="totalCost"><div><s:text name="page.billing.totalcost" /></div></td>
    			<td sortby="actualCost"><div><s:text name="page.billing.actualcost" /></div></td>
    			<td sortby="isPay"><div><s:text name="page.billing.ispay" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="billing">
    			<tr class="tab_data_tr">
        		<td><div>
        			<input type="checkbox" name="choose" value="<s:property value='#billing.id' />"/>
        		</div></td>
        		<td title="<s:property value='#billing.id' />"><div>
        			<s:property value="#billing.id" />
        		</div></td>
        		<td title="<s:property value='#billing.conference.customer.customerCode' />"><div>
        			<s:property value="#billing.customerCode" />
        		</div></td>
        		<td title="<s:property value='#billing.conference.customer.customerName' />"><div>
        			<s:property value="#billing.customerName" />
        		</div></td>
        		<td title="<s:property value='#billing.conference.confSubject' />"><div>
        			<s:property value="#billing.conference.confSubject" />
        		</div></td>
        		<td title="<s:date name="#billing.conference.startTime" format="yyyy-MM-dd HH:mm" />">
        			<div><s:date name="#billing.conference.startTime" format="yyyy-MM-dd HH:mm" /></div>
        		</td>
        		<td title="<s:date name="#billing.conference.endTime" format='yyyy-MM-dd HH:mm'/>">
        			<div><s:date name="#billing.conference.endTime" format="yyyy-MM-dd HH:mm" /></div>
        		</td>
        		<td title="<s:property value='#billing.rates.ratesName' />"><div>
        			<s:property value="#billing.rates.ratesName" />
        		</div></td>
        		<td title="<s:property value='#billing.totalCost' />"><div>
        			<s:property value="#billing.totalCost" />
        		</div></td>
        		<td title="<s:property value='#billing.actualCost' />"><div>
        			<s:property value="#billing.actualCost" />
        		</div></td>
        		<s:if test="#billing.isPay">
        			<td title="<s:text name='page.billing.haspay' />"><div>
        				<s:text name="page.billing.haspay" />
        			</div></td>
        		</s:if>
        		<s:else>
        			<td title="<s:text name='page.billing.notpay' />"><div>
        				<s:text name="page.billing.notpay" />
        			</div></td>
        		</s:else>
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
