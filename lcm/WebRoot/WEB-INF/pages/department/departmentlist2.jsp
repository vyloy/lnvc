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
	<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
	<script type="text/javascript" src="js/dtree.js"></script>
  </head>
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
  	<div class="search_container">
  		<s:form action="app/departmentAction_searchDepartment_departmentlist.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.department.departmentname" />
  			<s:textfield name="department.departmentName" theme="simple" />
  			<s:text name="page.department.parentname" />
  			<s:textfield name="department.parent.departmentName" theme="simple" />
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
	<div>
		<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
	
		<script type="text/javascript">

			d = new dTree('d');
	
			<s:iterator value="#request.pageMap.tree" id="node">
			d.add(<s:property value="#node.id" />,<s:property value="#node.pid" />,'<s:property value="#node.name" />','javascript:alert("");');
			//alert("");
			</s:iterator>
	
			document.write(d);
			
		</script>
	</div>
  </body>
</html>
