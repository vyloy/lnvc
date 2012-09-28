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
  	<title><s:text name="page.text.statistics" /></title>
    <base href="<%=basePath%>">
    <link rel="icon" href="<%=basePath%>/images/animated_favicon1.gif" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=basePath%>/images/favicon.ico" type="image/x-icon" />     	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/tab.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/tab.js"></script>
	<style type="text/css" media="print">
		.noprint{display : none}
	</style>
  </head>
  <body>
    <div class="tab_content_container">	
	   	<table class="tab_report_table"  cellpadding="0" cellspacing="1" >
	   		<caption class="tab_report_caption"><s:text name="page.billing.statisticsbycustomer"/></caption>
	   		<colgroup>
	   			<col style="width:20%" />
	   			<col style="width:20%" />
	   			<col style="width:20%" />
	   			<col style="width:20%" />
	   			<col style="width:20%" />
	   		</colgroup>
	   		<tr class="tab_data_table_header">
	   			<td><div><s:text name="page.billing.customername" /></div></td>
	   			<td><div><s:text name="page.billing.customercode" /></div></td>
	   			<td><div><s:text name="page.billing.totaltime" /></div></td>
	   			<td><div><s:text name="page.billing.totalcalccost" /></div></td>
	   			<td><div><s:text name="page.billing.totalactualcost" /></div></td>
	   		</tr>
	   		<s:iterator value="#request.pageMap.billingStatics" var="stat">
	   			<tr class="tab_data_tr">
	        		<td title="<s:property value='#stat.customer.customerName' />">
		        		<div>
		        			<s:property value="#stat.customer.customerName" />
		        		</div>
	        		</td>
	        		<td title="<s:property value='#stat.customer.customerCode' />">
		        		<div>
		        			<s:property value="#stat.customer.customerCode" />
		        		</div>
	        		</td>
	        		<td title="<s:property value='#stat.totaltime/(60*1000)' /><s:text name="page.text.minute"/>">
		        		<div>
		        			<s:property value="#stat.totaltime/(60*1000)" /><s:text name="page.text.minute"/>
		        		</div>
	        		</td>
	        		<td title="<s:property value='#stat.calcCost' />">
		        		<div>
		        			<s:property value="#stat.calcCost" />
		        		</div>
	        		</td>
	        		<td title="<s:property value='#stat.actualCost' />">
		        		<div>
		        			<s:property value="#stat.actualCost" />
		        		</div>
	        		</td>
	     		</tr>
	   		</s:iterator>
	   		<tr valign="bottom" align="center">
	   			<td colspan="5">
	   				<div class="noprint">
		   				<button class="tab_report_button" onclick="javascript:window.print();"><s:text name="page.text.print"/></button>
		   				<button class="tab_report_button" onclick="javascript:window.close();"><s:text name="page.text.cancel"/></button>
	   				</div>
	   			</td>
	   		</tr>
	   	</table>
    </div>
  </body>
</html>
