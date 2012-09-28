<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sd" uri="/struts-dojo-tags" %>
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
	<sd:head/>
  </head>
  <body>
  	<s:include value="/WEB-INF/pages/include/tab_top.jsp">
  		<s:param name="title">
  			<s:text name="page.display.text.logadmin.oplogadmin" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
  	<div class="tab_content_container">
  	<div class="search_container user_list_search">
  		<s:form action="app/loggerAction_searchLogger_loggerlist.action" theme="simple" cssClass="tab_search_form" >
  			<s:hidden name="isSearch" value="true" />
  			<!--<s:text name="page.display.text.optime" />
    	    <sd:datetimepicker name="searchModel.optime" displayFormat="yyyy-MM-dd" />-->
  			<s:text name="page.display.text.opdisc" />
  			<s:textfield name="searchModel.opdisc" theme="simple" />
  			<s:text name="page.display.text.operator" />
  			<s:textfield name="searchModel.userName" theme="simple" />
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
  	<s:if test="errorMsg.equals('')">
  	<form action="" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/loggerAction_sortLogger_loggerlist.action"/>
    			</div></td>
    			<td sortby="optime"><div><s:text name="page.display.text.optime" /></div></td>
    			<td sortby="opdisc"><div><s:text name="page.display.text.opdisc" /></div></td>
    			<td sortby="userName"><div><s:text name="page.display.text.operator" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" var="logger">
    			<tr class="tab_data_tr">
    				<td>
    					<input type="checkbox" name="choose"
    					 	value="<s:property value='#logger.id' />" />
    				</td>
    				<td title="<s:property value="#logger.optime" />">
    					<s:date name="#logger.optime" format="yyyy-MM-dd HH:mm:ss" />
    				</td>
    				<td title="<s:property value="#logger.opdisc" />">
    					<s:property value="#logger.opdisc" />
    				</td>
    				<td title="<s:property value="#logger.user.username" />">
    					<s:property value="#logger.userName" />
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
