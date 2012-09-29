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
  			<s:text name="page.display.text.authoritymanager.roledefine" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN">
			<s:param name="editUrl">app/conferenceRoleAction_toEditConferenceRole_edit_conferencerole.action*600*400*edit</s:param>
  			<s:param name="addUrl">app/conferenceRoleAction_addConferenceRole_edit_conferencerole.action*600*400*add</s:param>
  			<s:param name="deleteUrl">app/conferenceRoleAction_deleteConferenceRole_conferencerolelist.action*delete</s:param>  		
  		</security:authorize>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
    <div class="tab_content_container">
    <div class="search_container user_list_search">
  		<s:form action="app/conferenceRoleAction_searchConferenceRole_conferencerolelist.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.text.conferencerolename" />
  			<s:textfield name="conferenceRoleSearchModel.roleName" theme="simple" />
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
    <s:if test="errorMsg.equals('')">
  	<form action="app/conferenceRoleAction_deleteConferenceRole_conferencerolelist.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:32%" />
    			<!-- <col style="width:10%" />
    			<col style="width:6%" />
    			<col style="width:8%" />-->
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/conferenceRoleAction_sortConferenceRole_conferencerolelist.action"/>
    			</div></td>
    			<td sortby="roleName"><div><s:text name="page.text.conferencerolename" /></div></td>
    			<!-- <td sortby="status"><div><s:text name="page.billing.status" /></div></td> -->
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="conferenceRole">
    			<tr class="tab_data_tr">
        		<td><div><input type="checkbox" name="choose" value="<s:property value='#conferenceRole.id' />"/></div></td>
        		<td title="<s:property value='#conferenceRole.roleName' />">
        			<s:property value="#conferenceRole.roleName" />
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
