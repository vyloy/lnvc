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
  			<s:text name="page.department.depadmin" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<s:param name="editUrl">app/departmentAction_toEditDepartment_department_edit.action*500*240*edit</s:param>
  		<s:param name="addUrl">app/departmentAction_addDepartment_department_edit.action*500*240*add</s:param>
  		<s:param name="deleteUrl">app/departmentAction_deleteDepartment_departmentlist.action*delete</s:param>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
  	<div class="tab_content_container">
  	<div class="search_container">
  		<s:form action="app/departmentAction_searchDepartment_departmentlist.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.department.departmentname" />
  			<s:textfield name="department.departmentName" theme="simple" />
  			<!--<s:text name="page.department.parentname" />
  			<s:textfield name="department.parent.departmentName" theme="simple" />-->
  			
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
  	<s:if test="errorMsg.equals('')">
  	<form action="app/departmentAction_deleteDepartment_departmentlist.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:20%" />
    			<col style="width:50%" />
    			
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/departmentAction_sortDept_departmentlist.action"/>
    			</div></td>
    			<td sortby="departmentName"><div><s:text name="page.department.departmentname" /></div></td>
    			<td sortby="departmentDesc"><div><s:text name="page.department.departmentdesc" /></div></td>
    			<!--  <td sortby="parent"><div><s:text name="page.department.parentname" /></div></td>-->
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="department">
    			<tr class="tab_data_tr">
    				<td>
    					<input type="checkbox" name="choose"
    					 	value="<s:property value='#department.id' />" />
    				</td>
    				<td title="<s:property value='#department.departmentName' />">
    					<s:property value="#department.departmentName" />
    				</td>
    				<td title="<s:property value='#department.departmentDesc' />">
    					<s:property value="#department.departmentDesc" />
    				</td>
    				<!--<td title="<s:property value='#department.parent.departmentName' />">
    					<s:property value="#department.parent.departmentName" />
    				</td>--> 
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
