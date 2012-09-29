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
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/dialogue.js" charset="utf-8"></script>
  </head>
  
  <body class="dialogue_body">
  	<s:form id="dialogue_form" namespace="/app" action="department_doEditDepartment_department_edit" validate="true">
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
    				<s:text name="page.department.departmentname" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textfield name="department.departmentName" theme="simple" />
    			</td>
    			<!-- <td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.department.parentname" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="parentList" listKey="id" listValue="departmentName" name="department.parent.id" />
    			</td> -->
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.department.departmentdesc" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textarea name="department.departmentDesc" theme="simple" cssClass="dialogue_textarea" />
    			</td>
    		</tr>
    	</table>
  	</s:form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  </body>
</html>
