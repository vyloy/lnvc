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
	<script type="text/javascript" src="js/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/tab.js" charset="utf-8"></script>
  </head>
  <body>
  	<s:include value="/WEB-INF/pages/include/tab_top.jsp">
  		<s:param name="title">
  			<s:text name="page.display.text.mediaadmin" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<s:param name="editUrl">app/mediaAction_toEditMedia_media_edit.action*460*250*edit</s:param>
  		<s:param name="addUrl">app/mediaAction_toAddMedia_media_edit.action*460*250*add</s:param>
  		<s:param name="deleteUrl">app/mediaAction_deleteMedia_media_list.action*delete</s:param>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
  	<div class="tab_content_container">
  	<div class="search_container">
  		<s:form action="app/mediaAction_searchMedia_media_list.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
    		<s:hidden name="isSearch" value="true" theme="simple" />
  			<s:text name="page.media.medianame" />
  			<s:textfield name="searchMedia.mediaName" theme="simple" />
  			<s:text name="page.media.mediadesc" />
  			<s:textfield name="searchMedia.mediaDesc" theme="simple" />
  			
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
  	<s:if test="errorMsg.equals('')" >
  	<form action="app/mediaAction_deleteMedia_media_list.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:12%" />
    			<col style="width:13%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/mediaAction_sortMedia_media_list.action"/>
    			</div></td>
    			<td sortby="mediaName"><div><s:text name="page.media.medianame" /></div></td>
    			<td sortby="mediaDesc"><div><s:text name="page.media.mediadesc" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="media">
    			<tr class="tab_data_tr">
    				<td>
    					<input type="checkbox" name="choose"
    					 	value="<s:property value='#media.id' />" />
    				</td>
    				<td title="<s:property value="#media.mediaName" />">
    					<s:property value="#media.mediaName" />
    				</td>
    				<td title="<s:property value="#media.mediaDesc" />">
    					<s:property value="#media.mediaDesc" />
    				</td>
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
