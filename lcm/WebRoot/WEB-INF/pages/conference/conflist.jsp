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
	  			<s:property value="pageTitle" />
	  		</s:param>
	  		<s:param name="selectAll">1</s:param>
	  		<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN,ROLE_CUSTOMER_USER,ROLE_CUSTOMER_DEP_ADMIN">
				<s:param name="editUrl">app/conferenceAction_toEditConf_edit_conf.action*620*540*edit</s:param>
	  			<s:param name="addUrl">app/conferenceAction_addConf_edit_conf.action*620*540*add</s:param>
	  			<s:param name="deleteUrl">app/conferenceAction_deleteConf_conflist.action*delete</s:param>  		
	  		</security:authorize>
	  		<s:param name="searchUrl">search</s:param>
	  		<s:param name="stopConfUrl">app/conferenceAction_stopConf_conflist.action*stopConf</s:param>
  		</s:include>

    <div class="tab_content_container">
    <div class="search_container user_list_search">
  		<s:form action="conferenceAction_searchConfs_conflist.action" namespace="/app" theme="simple" cssClass="tab_search_form" onsubmit="return false">
  			<s:hidden name="isSearch" value="true" />
  			<s:text name="page.text.confname" />
  			<s:textfield name="conf.confSubject" theme="simple" />
  			<s:text name="page.text.callno" />
  			<s:textfield name="conf.confno"></s:textfield>
			<%--<s:select list="noList" name="conf.conferenceNo.id" listKey="id" listValue="noCode" cssClass="long" ></s:select>--%>
  			<%--<s:text name="page.text.starttime" />
  			<sd:datetimepicker name="dateStrMap.startTimeDate" displayFormat="yyyy-MM-dd" />
  			<s:text name="page.text.endtime" />
  			<sd:datetimepicker name="dateStrMap.endTimeDate" displayFormat="yyyy-MM-dd" />--%>
  			<s:text name="page.text.owner" />
  			<s:textfield name="conf.owner.username" theme="simple" />
  			<!--<s:text name="page.text.host" />
  			<s:textfield name="conf.confHost.username" theme="simple" />-->
  			<%--<s:text name="page.text.conftype" />
  			<s:select list="confTypeList" name="conf.confType" headerKey="" headerValue=""></s:select>--%>
  			<s:text name="page.text.status" />
  			<s:select list="confStatusList" name="conf.confStatus" cssClass="medium" headerKey="" headerValue="" />
  			<!--<s:text name="page.text.confmode" />
  			<s:select list="confModeList" name="conf.confMode" cssClass="long" headerKey="" headerValue=""></s:select>-->
  			<security:authorize ifAllGranted="ROLE_ADMIN">
  				<s:text name="page.text.belongs" />
  				<s:textfield name="conf.customer.customerName" value="" />
  			</security:authorize>
    		<button class="tab_search_button">
    			<s:text name="page.text.ok" />
    		</button>
    		<button class="tab_reset_button">
    			<s:text name="page.text.reset" />
    		</button>
    	</s:form>
  	</div>
  	<s:if test="errorMsg.equals('')">
  	<form action="app/conferenceAction_deleteConf_conflist.action" class="content_form">
    	<table class="tab_data_table"  cellpadding="0" cellspacing="1" border="0"  bordercolor="#A8C7CE">
    		<colgroup>
    			<col style="width:3%" />
    			<col style="width:8%" />
    			<col style="width:8%" />
    			<col style="width:8%" />
    			<col style="width:8%" />
    			<col style="width:6%" />
    			<col style="width:8%" />
    			<col style="width:8%" />
    			<col style="width:6%" />
    			<col style="width:8%" />
    			<s:if test="conf.confStatus==1">
    				<col style="width:6%" />
    			</s:if>
    		</colgroup>
    		<tr class="tab_data_table_header">
    			<td><div>
    				<s:text name="page.text.choose" />
    				<s:hidden id="sortUrl" name="sortUrl" value="app/conferenceAction_sortConf_conflist.action"/>
    			</div></td>
    			<td sortby="confSubject"><div><s:text name="page.text.confname" /></div></td>
    			<td sortby="startTime"><div><s:text name="page.text.starttime" /></div></td>
    			<td sortby="endTime"><div><s:text name="page.text.endtime" /></div></td>
    			<td sortby="confno"><div><s:text name="page.text.callno" /></div></td>
    			<td sortby="confStatus"><div><s:text name="page.text.status" /></div></td>
    			<td sortby="owner"><div><s:text name="page.text.owner" /></div></td>
    			<td sortby="confType"><div><s:text name="page.text.conftype" /></div></td>
    			<td sortby="customer"><div><s:text name="page.text.belongs" /></div></td>
    			<td sortby="ismix"><div><s:text name="page.text.ismix" /></div></td>
    			<s:if test="conf.confStatus==1">
    				<td><div><s:text name="page.text.mediaplay" /></div></td>
    			</s:if>
    		</tr>
    		<s:iterator value="#request.pageMap.pageResult" id="conf">
    			<tr class="tab_data_tr">
        		<td><div>
        			<input type="checkbox" name="choose" alt="<s:property value='#conf.confPublic' />*ajax/conference_checkPass.action"
        				 value="<s:property value='#conf.id' />" />
        		</div></td>
        		<td title="<s:property value='#conf.confSubject' />">
        			<div class="data_title" 
        				alt="<s:property value='#conf.id' />*560*485*<s:property value='#conf.confPublic' />
        													*app/conferenceAction_showDesc_conf_desc.action
        													*ajax/conference_checkPass.action"
        				title="<s:text name='page.display.title.tip'/>" >
        				<s:property value="#conf.confSubject" />
        			</div>
        		</td>
        		<td title="<s:date name="#conf.startTime" format="yyyy-MM-dd HH:mm" />">
        			<div><s:date name="#conf.startTime" format="yyyy-MM-dd HH:mm" /></div>
        		</td>
        		<td title="<s:date name="#conf.endTime"/>">
        			<div><s:date name="#conf.endTime" format="yyyy-MM-dd HH:mm" /></div>
        		</td>
        		<td title="<s:property value='#conf.confno' />"><div>
        			<s:if test="#conf.confPublic==1">
        				<s:property value="#conf.confno" />
        			</s:if>
        			<s:else>
        				<s:text name="page.text.encryption" />
        			</s:else>
        		</div></td>
        		<td title="<s:property value='#conf.confStatus' />"><div>
        			<s:if test="#conf.confStatus==0">
        				<s:text name="page.conf.notstart" />
        			</s:if>
        			<s:elseif test="#conf.confStatus==1">
        				<s:text name="page.conf.ongoing" />
        			</s:elseif>
        			<s:elseif test="#conf.confStatus==2">
        				<s:text name="page.conf.finished" />
        			</s:elseif>
        			<s:elseif test="#conf.confStatus==3">
        				<s:text name="page.conf.deleted" />
        			</s:elseif>
        			<s:elseif test="#conf.confStatus==4">
        				<s:text name="page.conf.stoped"></s:text>
        			</s:elseif>
        		</div></td>
        		<td title="<s:property value='#conf.owner.username' />"><div>
        			<s:if test="#conf.confPublic==1">
        				<s:property value="#conf.owner.username" />
        			</s:if>
        			<s:else>
        				<s:text name="page.text.encryption" />
        			</s:else>
        		</div></td>
        		<!-- <td title="<s:property value='#conf.confHost.username' />"><div>
        			<s:if test="#conf.confPublic==1">
        				<s:property value="#conf.confHost.username" />
        			</s:if>
        			<s:else>
        				<s:text name="page.text.encryption" />
        			</s:else>
        		</div></td> 
        		<td title="<s:property value='#conf.confDesc' />"><div>
        			<s:property value="#conf.confDesc" />
        		</div></td>-->
        		<td title="<s:property value='#conf.confType' />"><div>
        			<s:if test="#conf.confType==1">
        				<s:text name="page.display.text.impconf" />
        			</s:if>
        			<s:elseif test="#conf.confType==2">
        				<s:text name="page.display.text.appconf" />
        			</s:elseif>
        			<s:elseif test="#conf.confType==3">
        				<s:text name="page.display.text.perconf" />
        			</s:elseif>
        		</div></td>
        		<!--<td title="<s:property value='#conf.confMode' />"><div>
        			<s:if test="#conf.confMode==1">
        				<s:text name="page.conf.mode.hostmode" />
        			</s:if>
        			<s:elseif test="#conf.confMode==2">
        				<s:text name="page.conf.mode.freemode" />
        			</s:elseif>
        		</div></td>-->
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
        		
        		<s:if test="conf.confStatus==1">
        			<s:if test="#conf.mediaId!=null">
		        		<td><div>
		        			<a href="app/conferenceAction_mediaOperate_conflist.action?play=true&confid=<s:property value='#conf.id' />">
		        				<s:text name="page.text.play" />
		        			</a>
		        			<a href="app/conferenceAction_mediaOperate_conflist.action?play=false&confid=<s:property value='#conf.id' />">
		        				<s:text name="page.text.stop" />
		        			</a>
		        		</div></td>
	        		</s:if>
	        		<s:else>
	        			<td></td>
	        		</s:else>
        		</s:if>
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
