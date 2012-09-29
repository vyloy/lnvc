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
  			<s:text name="page.display.text.useradmin" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<s:param name="editUrl">app/userAction_toEditUserPage_edit_user.action*600*320*edit</s:param>
  		<s:param name="addUrl">app/userAction_toAddUser_edit_user.action*600*320*add</s:param>
  		<s:param name="deleteUrl">app/userAction_deleteUser_user_list.action*delete</s:param>
  		<s:param name="searchUrl">search</s:param>
  		<s:param name="importUserUrl">app/userAction_toImportUser_importuser.action*600*320*import</s:param>
  	</s:include>
  	<div class="tab_content_container">
  	<div class="search_container user_list_search">
  		<s:form action="app/userAction_searchUser_user_list.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.display.text.username" />
  			<s:textfield name="user.username" theme="simple" />
  			<s:text name="page.display.text.realname" />
  			<s:textfield name="user.realName" theme="simple" />
  			<security:authorize ifAllGranted="ROLE_ADMIN">
  				<s:text name="page.user.belongs" />
  				<s:textfield name="user.customer.customerName" theme="simple" />
  				<!--<s:text name="page.user.usertype" />
  				<s:select list="userTypeList" headerValue=""
  					 name="user.userType" headerKey="">
  				</s:select>-->
  			</security:authorize>
  			<s:text name="page.user.locked" />
  			<s:select list="yesNoList" name="searchLocked" 
  				headerKey="" headerValue="" theme="simple">
    		</s:select>
    		<s:text name="page.user.expired" />
  			<s:select list="yesNoList" name="searchExpired" 
  				headerKey="" headerValue="" theme="simple">
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
  	<form action="app/userAction_deleteUser_user_list.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:8%" />
    			<col style="width:8%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:4%">
    			<col style="width:4%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/userAction_sortUser_user_list.action"/>
    			</div></td>
    			<td sortby="username"><div><s:text name="page.display.text.username" /></div></td>
    			<td sortby="realName"><div><s:text name="page.display.text.realname" /></div></td>
    			<!-- <td sortby="userEnabled"><div><s:text name="page.user.enable" /></div></td> -->
    			<td sortby="customer"><div><s:text name="page.user.belongs" /></div></td>
    			<td sortby="mobile"><div><s:text name="page.display.text.mobile" /></div></td>
    			<td sortby="phone"><div><s:text name="page.display.text.phone" /></div></td>
    			<td sortby="email"><div><s:text name="page.display.text.email" /></div></td>
    			<td sortby="lccAccount"><div><s:text name="page.display.text.lccaccount" /></div></td>
    			<td sortby="userAccountNonExpired"><div><s:text name="page.user.expired" /></div></td>
    			<td sortby="userAccountNonLocked"><div><s:text name="page.user.locked" /></div></td>
    			<!--<td sortby="userType"><div><s:text name="page.user.usertype" /></div></td>-->
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="user">
    			<tr class="tab_data_tr">
    				<td>
    					<input type="checkbox" name="choose"
    					 	value="<s:property value='#user.id' />" />
    				</td>
    				<td title="<s:property value="#user.username" />">
    					<s:property value="#user.username" />
    				</td>
    				<td title="<s:property value="#user.realName" />">
    					<s:property value="#user.realName" />
    				</td>
    				
    				<!--<s:if test="#user.userEnabled">
    					<td title="<s:text name='page.display.yes' />">
    						<s:text name="page.display.yes" />
    					</td>
    				</s:if>
    				<s:else>
    					<td title="<s:text name='page.display.no' />">
    						<s:text name="page.display.no" />
    					</td>
    				</s:else>-->
    				
    				<td title="<s:property value="#user.customer.customerName" />">
    					<s:property value="#user.customer.customerName" />
    				</td>
    				
    				<td title="<s:property value="#user.mobile" />">
    					<s:property value="#user.mobile" />
    				</td>
    				<td title="<s:property value="#user.phone" />">
    					<s:property value="#user.phone" />
    				</td>
    				<td title="<s:property value="#user.email" />">
    					<s:property value="#user.email" />
    				</td>
    				<td title="<s:property value="#user.lccAccount" />">
    					<s:property value="#user.lccAccount" />
    				</td>
    				<s:if test="#user.userAccountNonExpired" >
    					<td title="<s:text name='page.display.no' />">
    						<s:text name='page.display.no' />
    					</td>
    				</s:if>
    				<s:else>
    					<td title="<s:text name='page.display.yes' />">
    						<s:text name='page.display.yes' />
    					</td>
    				</s:else>
    				<s:if test="#user.userAccountNonLocked" >
    					<td title="<s:text name='page.display.no' />">
    						<s:text name='page.display.no' />
    					</td>
    				</s:if>
    				<s:else>
    					<td title="<s:text name='page.display.yes' />">
    						<s:text name='page.display.yes' />
    					</td>
    				</s:else>
    				<!--<s:if test="#user.userType==0">
    					<td title="<s:text name='page.usertype.admin' />">
    						<s:text name="page.usertype.admin" />
    					</td>
    				</s:if>
    				<s:elseif test="#user.userType==1">
    					<td title="<s:text name='page.usertype.inneruser' />">
    						<s:text name="page.usertype.inneruser" />
    					</td>
    				</s:elseif>
    				<s:elseif test="#user.userType==2">
    					<td title="<s:text name='page.usertype.memberuser' />">
    						<s:text name="page.usertype.memberuser" />
    					</td>
    				</s:elseif>
    				<s:else>
    					<td>&nbsp;</td>
    				</s:else>-->
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
