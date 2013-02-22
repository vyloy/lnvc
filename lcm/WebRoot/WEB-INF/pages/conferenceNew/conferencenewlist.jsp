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
	<!--<script src="js/deployJava.js"></script>
	<script type="text/javascript">
		function enterConf(url){
			/*
			if (deployJava.versionCheck('1.6.0_30+') == false) {
				//alert("你需要先安装java");
				
				//deployJava.installJRE("1.6.0_29");
				var download_jdk_url = '<%=basePath%>' + 'lvmc/lvmc4.jar';
				window.open(download_jdk_url);
				
				//return;
			}else{
				//alert(1111);
				window.open(url);
				//deployJava.createWebStartLaunchButton(url, '1.6.0_30');
			}
			*/
			window.open(url);
		}
	</script>-->
  </head>
  <body>
  	<s:include value="/WEB-INF/pages/include/tab_top.jsp">
  		<s:param name="title">
  			<s:text name="page.display.text.conferencenew" />
  		</s:param>
  		<s:param name="selectAll">1</s:param>
  		<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN,ROLE_CUSTOMER_USER,ROLE_CUSTOMER_DEP_ADMIN">
			<s:param name="editUrl">app/conferenceNewAction_toEditConferenceNew_edit_conferencenew.action*600*500*edit</s:param>
  			<s:param name="addUrl">app/conferenceNewAction_addConferenceNew_edit_conferencenew.action*600*500*add</s:param>
  			<s:param name="deleteUrl">app/conferenceNewAction_deleteConferenceNew_conferencenewlist.action*delete</s:param>  		
  		</security:authorize>
  		<s:param name="searchUrl">search</s:param>
  	</s:include>
    <div class="tab_content_container">
    <div class="search_container user_list_search">
  		<s:form action="app/conferenceNewAction_searchConferenceNew_conferencenewlist.action" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.text.conferencenewname" />
  			<s:textfield name="conferenceNewSearchModel.conferenceName" theme="simple" />
  			<s:text name="page.text.conferencetypename" />
  			<s:select list="conferenceTypeMap" name="conferenceNewSearchModel.conferenceTypeId" headerKey="" cssClass="long" headerValue="" ></s:select>
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset"/>
    		</button>
    	</s:form>
  	</div>
    <s:if test="errorMsg.equals('')">
  	<form action="app/conferenceNewAction_deleteConferenceNew_conferencenewlist.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:4%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<col style="width:12%" />
    			<!-- <col style="width:12%" /> -->
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/conferenceNewAction_sortConferenceNew_conferencenewlist.action"/>
    			</div></td>
    			<td sortby="conferenceName"><div><s:text name="page.text.conferencenewname" /></div></td>
    			<td sortby="conferenceTypeId"><div><s:text name="page.text.conferencetypename" /></div></td>
    			<td sortby="creator"><div><s:text name="page.text.conferencenewcreator" /></div></td>
    			<td sortby="confNo"><div><s:text name="page.text.conferencenewno" /></div></td>
    			<td sortby="defaultRoleId"><div><s:text name="page.text.defaultconferencerole" /></div></td>
    			<!--<td><div><s:text name="page.text.operator" /></div></td>-->
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="conferenceNew">
    			<tr class="tab_data_tr">
        		<td><div><input type="checkbox" name="choose" value="<s:property value='#conferenceNew[0]' />"/></div></td>
        		<td title="<s:property value='#conferenceNew[1]' />">
        			<s:property value="#conferenceNew[1]" />
        		</td>
        		<td title="<s:property value='#conferenceNew[3]' />">
        			<s:property value="#conferenceNew[3]" />
        		</td>
        		<td title="<s:property value='#conferenceNew[4]' />">
        			<s:property value="#conferenceNew[4]" />
        		</td>
        		<td title="<s:property value='#conferenceNew[5]' />">
        			<s:property value="#conferenceNew[5]" />
        		</td>
        		<td title="<s:property value='#conferenceNew[2]' />">
        			<s:property value="#conferenceNew[2]" />
        		</td>
        		<!--<td>
        			<a onclick='enterConf("app/conferenceNewAction_enterConf_conferencenewlist.action?confno=<s:property value='#conferenceNew[5]' />")' href="javascript:void(-1)">
        				<s:text name="page.text.enterconf" />
					</a>
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
