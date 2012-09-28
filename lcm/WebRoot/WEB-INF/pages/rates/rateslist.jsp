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
  			<s:text name="page.display.text.costadmin.tariffconf" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN">
			<s:param name="editUrl">app/ratesAction_toEditRates_edit_rates.action*440*250*edit</s:param>
  			<s:param name="addUrl">app/ratesAction_addRates_edit_rates.action*440*250*add</s:param>
  			<s:param name="deleteUrl">app/ratesAction_deleteRates_rateslist.action*delete</s:param>  		
  		</security:authorize>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
    <div class="tab_content_container">
    <div class="search_container user_list_search">
  		<s:form action="app/ratesAction_searchRates_rateslist.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.billing.ratesname" />
  			<s:textfield name="rates.ratesName" theme="simple" />
  			<s:text name="page.billing.ratestype" />
			<s:select list="ratesTypeList" name="rates.ratesType" headerKey="" cssClass="long" headerValue="" ></s:select>
  			<s:text name="page.billing.status" />
  			<s:select list="statusList" name="rates.ratesStatus" headerKey=""
  				 headerValue="" theme="simple">
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
  	<form action="app/ratesAction_deleteRates_rateslist.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:8%" />
    			<col style="width:10%" />
    			<col style="width:6%" />
    			<col style="width:8%" />
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/ratesAction_sortRates_rateslist.action"/>
    			</div></td>
    			<td sortby="ratesName"><div><s:text name="page.billing.ratesname" /></div></td>
    			<td sortby="ratesType"><div><s:text name="page.billing.ratestype" /></div></td>
    			<td sortby="ratesTarriff"><div><s:text name="page.billing.ratestarriff" /></div></td>
    			<td sortby="ratesStatus"><div><s:text name="page.billing.status" /></div></td>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="rates">
    			<tr class="tab_data_tr">
        		<td><div><input type="checkbox" name="choose" value="<s:property value='#rates.id' />"/></div></td>
        		<td title="<s:property value='#rates.ratesName' />"><div>
        			<s:property value="#rates.ratesName" />
        		</div></td>
        		<s:if test="#rates.ratesType==0">
        			<td title="<s:text name='page.billing.ratestype.bytime' />">
        				<s:text name='page.billing.ratestype.bytime' />
        			</td>
        		</s:if>
        		<s:elseif test="#rates.ratesType==1">
        			<td title="<s:text name='page.billing.ratestype.byhour' />">
        				<s:text name='page.billing.ratestype.byhour' />
        			</td>
        		</s:elseif>
        		<s:elseif test="#rates.ratesType==2">
        			<td title="<s:text name='page.billing.ratestype.bymin' />">
        				<s:text name='page.billing.ratestype.bymin' />
        			</td>
        		</s:elseif>
        		<td title="<s:property value='#rates.ratesTarriff' />"><div>
        			<s:property value="#rates.ratesTarriff" />
        		</div></td>
        		<s:if test="#rates.ratesStatus==0">
        			<td title="<s:text name='page.record.status.notactivated' />">
        				<s:text name='page.record.status.notactivated' />
        			</td>
        		</s:if>
        		<s:elseif test="#rates.ratesStatus==1">
        			<td title="<s:text name='page.record.status.activated' />">
        				<s:text name='page.record.status.activated' />
        			</td>
        		</s:elseif>
        		<s:elseif test="#rates.ratesStatus==2">
        			<td title="<s:text name='page.record.status.stop' />">
        				<s:text name='page.record.status.stop' />
        			</td>
        		</s:elseif>
        		<s:elseif test="#rates.ratesStatus==-1">
        			<td title="<s:text name='page.record.status.delete' />">
        				<s:text name='page.record.status.delete' />
        			</td>
        		</s:elseif>
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
