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
  			<s:text name="page.display.text.authoritymanager.authoritydefine" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN">
			<s:param name="editUrl">app/authorityAction_toEditAuthority_edit_authority.action*440*150*edit</s:param>
  			<s:param name="addUrl">app/authorityAction_addAuthority_edit_authority.action*440*150*add</s:param>
  			<s:param name="deleteUrl">app/authorityAction_deleteAuthority_authoritylist.action*delete</s:param>  		
  		</security:authorize>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
    <div class="tab_content_container">
    <div class="search_container user_list_search">
  		<s:form action="app/authorityAction_searchAuthority_authoritylist.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.text.authorityname" />
  			<s:textfield name="authoritySearchModel.authorityName" theme="simple" />
  			<s:text name="page.text.authoritymark" />
  			<s:textfield name="authoritySearchModel.mark" theme="simple" />
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
    <s:if test="errorMsg.equals('')">
  	<form action="app/authorityAction_deleteAuthority_authoritylist.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:16%" />
    			<col style="width:16%" />
    			<!-- <col style="width:6%" />
    			<col style="width:8%" />-->
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/authorityAction_sortAuthority_authoritylist.action"/>
    			</div></td>
    			<td sortby="authorityName"><div><s:text name="page.text.authorityname" /></div></td>
    			<td sortby="mark"><div><s:text name="page.text.authoritymark" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="authority">
    			<tr class="tab_data_tr">
        		<td><div><input type="checkbox" name="choose" value="<s:property value='#authority.id' />"/></div></td>
        		<td title="<s:property value='#authority.authorityName' />">
        			<s:property value="#authority.authorityName" />
        		</td>
        		<td title="<s:property value='#authority.mark' />">
        			<s:property value="#authority.mark" />
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
