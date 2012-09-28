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
  	<s:form id="dialogue_form" namespace="/app" action="rates_doEditRates_edit_rates" validate="true">
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
    				<s:text name="page.billing.ratesname" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="rates.ratesName" theme="simple" />
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.billing.ratestype" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="ratesTypeList" name="rates.ratesType" />
    			</td>
    		</tr>
    		<!-- <tr>
    			<td class="dialogue_td_text"><s:text name="page.billing.starttime" /></td>
    			<td class="dialogue_td_input">
    				<sd:datetimepicker name="rates.startTime" displayFormat="yyyy-MM-dd" />
    			</td>
    			<td class="dialogue_td_text"><s:text name="page.billing.endtime" /></td>
    			<td class="dialogue_td_input">
    				<sd:datetimepicker name="rates.endTime" displayFormat="yyyy-MM-dd" />
    			</td>
    		</tr> -->
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.billing.ratestarriff" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="rates.ratesTarriff" />
    			</td>
    			<td class="dialogue_td_text"><s:text name="page.text.status" /></td>
    			<td class="dialogue_td_input">
    				<s:select list="statusList" name="rates.ratesStatus"
    					 headerKey="0" theme="simple">
    				</s:select>
    			</td>
    		</tr>
    	</table>
  	</s:form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  </body>
</html>
