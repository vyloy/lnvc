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
			<s:text name="page.display.text.confmeeting" />
		</s:param>
		<s:param name="selectAll">1</s:param>
		<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN,ROLE_CUSTOMER_USER,ROLE_CUSTOMER_DEP_ADMIN">
			<s:param name="editUrl">app/confmeetingAction_toEditConf_confmeeting_baseinfo.action*620*320*edit</s:param>
			<s:param name="addUrl">app/confmeetingAction_toAddConf_confmeeting_baseinfo.action*620*320*add</s:param>
			<s:param name="deleteUrl">app/confmeetingAction_deleteConf_confmeeting_list.action*delete</s:param>  		
		</security:authorize>
		<s:param name="searchUrl">search</s:param>
	</s:include>

    <div class="tab_content_container">
    <div class="search_container user_list_search">
  		<s:form action="app/confmeetingAction_searchConf_confmeeting_list.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			
  			<s:text name="page.text.confname" />
  			<s:textfield name="searchModel.confSubject" theme="simple" />
  			
  			<s:text name="page.text.callno" />
  			<s:textfield name="searchModel.confno" />
  			
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
  	<s:if test="errorMsg.equals('')">
  	<form action="app/confmeetingAction_deleteConf_confmeeting_list.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:2%" />
    			<col style="width:8%" />
    			<col style="width:5%" />
    			<col style="width:5%" />
    			<col style="width:8%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/confmeetingAction_sortConf_confmeeting_list.action"/>
    			</div></td>
    			<td sortby="confSubject"><div><s:text name="page.text.confname" /></div></td>
    			<td sortby="confno"><div><s:text name="page.text.callno" /></div></td>
    			<td sortby="customer"><div><s:text name="page.text.belongs" /></div></td>
    			<td sortby="ismix"><div><s:text name="page.text.ismix" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="conf">
    			<tr class="tab_data_tr">
        		<td><div>
        			<input type="checkbox" name="choose" alt="<s:property value='#conf.confPublic' />*ajax/confmeeting_checkPass.action"
        				 value="<s:property value='#conf.id' />" />
        		</div></td>
        		<td title="<s:property value='#conf.confSubject' />">
        				<s:property value="#conf.confSubject" />
        		</td>
        		<td title="<s:property value='#conf.confno' />"><div>
        			<s:if test="#conf.confPublic==1">
        				<s:property value="#conf.confno" />
        			</s:if>
        			<s:else>
        				<s:text name="page.text.encryption" />
        			</s:else>
        		</div></td>
        		<td title="<s:property value='#conf.customer.customerName' />"><div>
        			<s:property value="#conf.customer.customerName" />
        		</div></td>
        		
        		<td title="<s:property value='#conf.ismix' />"><div>
        		    <s:if test="#conf.ismix==1">
        			   <s:text name="page.conf.mix" />
        		    </s:if>
        		    <s:if test="#conf.ismix==2">
        			   <s:text name="page.conf.notmix" />
        		    </s:if>
        		</div></td>
        		
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
