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
	<link rel="stylesheet" type="text/css" href="css/tab.css">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/dialogue.js" charset="utf-8"></script>
  </head>
  
  <body>
    <s:include value="/WEB-INF/pages/include/sys_tab_top.jsp">
  		<s:param name="title">
  			<s:text name="客户管理" />
  		</s:param>
  		<!-- 
  		<s:param name="selectAll">1</s:param>
  		<s:param name="addUrl">app/mcuAction_addMcu_mcu_edit.action*460*300*add</s:param>
  		<s:param name="searchUrl">search</s:param>
  		<s:param name="deleteUrl">app/sysServerConfigAction_editconfig_sysserverconfig_edit.action*delete</s:param>
  		<s:param name="editUrl">app/sysServerConfigAction_editconfig_sysserverconfig_edit.action*edit</s:param>
  		 -->
  	</s:include>
  	<s:form id="dialogue_form" namespace="/app" action="customer_doEditCustomer_customer_edit" validate="true">
  		<s:token />
  		<fieldset>
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
    				<s:text name="page.text.customername" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.customerName" theme="simple" />
    			</td>
    			<td class="dialogue_td_text">
    				<s:text name="page.text.customercode" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.customerCode" theme="simple"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.customeruserlimit" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.customerUserlimit" theme="simple"  />
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.confpeoplelimit" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.confPeopleLimit" theme="simple"  />
    			</td>
    		</tr>
    		<!-- <tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.imdconfnolimit" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.imdConfNoLimit" theme="simple"/>
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.appconfnolimit" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.appConfNoLimit" theme="simple"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.perconfnolimit" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.perConfNoLimit" theme="simple"/>
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.confmeetingnolimit" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.confMeetingNoLimit" theme="simple"/>
    			</td>
    		</tr> -->
    		<tr>
    			<td class="dialogue_td_text"><s:text name="page.text.status" /></td>
    			<td class="dialogue_td_input">
    				<s:select list="statusList" value="customer.customerStatus" name="customer.customerStatus"
    					 headerKey="0" theme="simple">
    				</s:select>
    			</td>
    			<td class="dialogue_td_text"><s:text name="page.text.address" /></td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.customerAddress" />
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="dialogue_td_text"><s:text name="page.text.contact" /></td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.customerContact" />
    			</td>
    			<td class="dialogue_td_text"><s:text name="page.text.phone" /></td>
    			<td class="dialogue_td_input">
    				<s:textfield name="customer.customerPhone" />
    			</td>
    		</tr>
    		
    		<!-- <tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.mcuchoose" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="serverList" listKey="id"
    					 listValue="serverName" name="customer.mcuServer.id" />
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.customer.chooserates" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="ratesList" listKey="id"
    					 listValue="ratesName" name="customer.rates.id" />
    			</td>
    		</tr> -->
    		<tr>
    			<td class="dialogue_td_text"><s:text name="page.text.desc" /></td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textarea name="customer.customerDesc" theme="simple" cssClass="dialogue_textarea" />
    			</td>
    		</tr>
    		<tr>
    		<td  colspan="4" align="center" >
   			  <p style="color:orange;text-align:center;margin-top:5px;">
				<s:iterator value="fieldErrors.values" id="value" status="status">
					<s:if test="#status.index==0">
						<s:property value="#value[0]" />
					</s:if>
				</s:iterator>
				<s:if test="exception.message!=null">
			  		<s:property value="exception.message" />
			  	</s:if>
   			  </p>
    		</td>
    		</tr>
    	</table>
    	</fieldset>
    	<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    	  <tr>
    	    <td colspan="4" align="center">
    		<s:submit name="save" value=" 保  存  "  cssStyle="width:50;margin-top:5px"></s:submit> 
    		</td>
    	  </tr>
    	</table>
  	</s:form>
  </body>
</html>
