<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sd" uri="/struts-dojo-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'mcu_edit.jsp' starting page</title>
    <sd:head/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/dialogue.js" charset="utf-8"></script>
  </head>
  <script>
  var basepath = '<%=basePath%>';
  var typeValue = 0;
  function typeChange(val){
  	if(val>0){
  		typeValue = val;
  		$.ajax({
		    url: basepath + 'app/conferenceNewAction_toSearchTypeRoleUserInfo_userlist.action?conferenceType='+val+'&rnd='+ Math.random(),
		    type: 'GET',
		    dataType: 'html',
		    timeout: 5000000,
		    error: function(){
		        alert('Error loading XML document');
		    },
		    success: showuser
		});
  		
  	}
  }
  
  function showuser(str){
    //alert(str);
  	$('#userinfo').html(str);
  	$.ajax({
	    url: basepath + 'app/conferenceNewAction_toSearchTypeRoleUserInfo_conferenceroles.action?conferenceType='+ typeValue + '&rnd='+ Math.random(),
	    type: 'GET',
	    dataType: 'html',
	    timeout: 5000000,
	    error: function(XMLHttpRequest, textStatus, errorThrown){
	        alert(textStatus);
	        alert(errorThrown);
	    },
	    success: showrole
	});
  }
  
  function showrole(data){
  	//alert(data);
  	$('#dialogue_form_conferenceNew_defaultRoleId').find('option').remove();
  	$('#dialogue_form_conferenceNew_defaultRoleId').append("<option value=''>请选择</option>");
  	
  	var list = eval('('+data+')').list;
  	for(i=0;i<list.length;i++){
  		if(list[i]!=null){
  			//alert(list[i].key + '==' + list[i].rolevalue);
  			$('#dialogue_form_conferenceNew_defaultRoleId').append("<option value='"+list[i].key+"'>"+list[i].rolevalue+"</option>");
  		}
  	}
  	
  }
  </script>
  <body class="dialogue_body">
  	<s:form id="dialogue_form" namespace="/app" action="conferenceNew_doEditConferenceNew_edit_conferencenew" validate="true">
  		<s:token />
  		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    			<col style="width: 20%;"  />
    			<col style="width: 30%;" />
    		</colgroup>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.conferencenewname" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="conferenceNew.conferenceName" theme="simple" />
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.conferencetypename" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="conferenceTypeMap!=null?conferenceTypeMap:#{}" name="conferenceNew.conferenceTypeId" onchange="javascript:typeChange(this.value);" theme="simple" headerKey="" headerValue="请选择"></s:select>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.confnewneedapply" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="#{1:'是',0:'否'}" theme="simple" label="是否需要申请" name="conferenceNew.needApply" headerKey="" headerValue="请选择"></s:select>
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.text.defaultconferencerole" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="conferenceRoles!=null?conferenceRoles:#{}" name="conferenceNew.defaultRoleId" theme="simple" headerKey="" headerValue="请选择"></s:select>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.text.confpass" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:password name="conferenceNew.password" theme="simple" showPassword="true"></s:password>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.conf.desc" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textarea name="conferenceNew.description" theme="simple" cssClass="dialogue_textarea" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				为选择参会者：
    			</td>
    			<td class="dialogue_td_input">
    				&nbsp;
    			</td>
    			<td class="dialogue_td_text">
    				&nbsp;
    			</td>
    			<td class="dialogue_td_input">
    				&nbsp;
    			</td>
    		</tr>
    	</table>
    	<div style="overflow-y:auto; height: 160px;width: 80%; margin-left: auto; margin-right: auto;" id="userinfo">
    			<jsp:include flush="true" page="/WEB-INF/pages/conferenceNew/userlist.jsp"/>
		</div>
  	</s:form>
  	
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  </body>
</html>
