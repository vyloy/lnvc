<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
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
    <title>My JSP 'edit_user.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js"></script>
  </head>
  <body class="dialogue_body">
  	<s:form id="dialogue_form" namespace="/app" action="user_doEditUser_edit_user" validate="true">
  		<s:token />
  		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 18%;"  />
    			<col style="width: 32%;" />
    			<col style="width: 18%;"  />
    			<col style="width: 32%;" />
    		</colgroup>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.display.text.username" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:if test="user.id==null">
    					<s:textfield name="user.username" />
    				</s:if>
    				<s:else>
    					<s:textfield name="user.username" disabled="true" />
    				</s:else>
    			</td>
    			<td class="dialogue_td_text"><s:text name="page.display.text.realname" /></td>
    			<td class="dialogue_td_input"><s:textfield name="user.realName" theme="simple"/></td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.display.text.email" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="user.email" theme="simple"/>
    			</td>
    			<td class="dialogue_td_text"><s:text name="page.display.text.mobile" /></td>
    			<td class="dialogue_td_input"><s:textfield name="user.mobile" theme="simple"/></td>
    		</tr>
    		<s:if test="!changeSelf">
    			<tr>
    				<td class="dialogue_td_text">
    					<s:text name="page.user.locked" />
    				</td>
    				<td class="dialogue_td_input">
    					<s:select list="yesNoList" name="locked">
    					</s:select>
    				</td>
    				<td class="dialogue_td_text"><s:text name="page.user.expired" /></td>
    				<td class="dialogue_td_input">
						<s:select list="yesNoList" name="expired">
    					</s:select>
					</td>
    			</tr>
    		</s:if>
    		<tr>
    			<td class="dialogue_td_text"><s:text name="page.display.text.phone" /></td>
    			<td class="dialogue_td_input">
    				<s:textfield name="user.phone" theme="simple" />
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.user.belongs" />
    			</td>
    			<td class="dialogue_td_input">	
    				<security:authorize ifAllGranted="ROLE_ADMIN">
    					<s:if test="changeSelf">
    						<input type="text" value="<s:text name='page.display.sysetem' />" disabled="true" />
    					</s:if>
    					<s:else>
    						<s:select list="customerList" name="user.customer.id" listKey="id"
    						 	 listValue="customerName">
    						</s:select>
    					</s:else>
    				</security:authorize>
    				<security:authorize ifNotGranted="ROLE_ADMIN">
    					<s:textfield name="user.customer.customerName" disabled="true" />
    				</security:authorize>
    			</td>
    		</tr>
    		<s:if test="user.id==null">
    			<tr>
    				<td class="dialogue_td_text">
    					<span class="required">*</span>
    					<s:text name="page.display.text.password" />
    				</td>
    				<td class="dialogue_td_input">
    					<s:password name="user.realPassword" value="" theme="simple" />
    				</td>
    				<td class="dialogue_td_text">
    					<span class="required">*</span>
    					<s:text name="page.display.text.repeatpassword" />
    				</td>
    				<td class="dialogue_td_input">
    					<s:password name="user.repeatPassword" theme="simple" />
    				</td>
    			</tr>
    		</s:if>
    		<s:else>
    			<s:if test="user.userType==0">
	    			<tr>
	    				<td class="dialogue_td_text">
	    					<s:text name="page.display.text.password" />
	    				</td>
	    				<td class="dialogue_td_input" colspan="3">
	    					<div openobj="disable_tr" class="dialogue_click_text"><s:text name="page.display.text.changepassword" /></div>
	    					<s:hidden name="hideValue" cssClass="hideValue" value="0" />
	    				</td>
	    			</tr>
    			</s:if>
    			<s:else>
	    			<tr>
	    				<td class="dialogue_td_text">
	    					<s:text name="page.display.text.password" />
	    				</td>
	    				<td class="dialogue_td_input">
	    					<div openobj="disable_tr" class="dialogue_click_text"><s:text name="page.display.text.changepassword" /></div>
	    					<s:hidden name="hideValue" cssClass="hideValue" value="0" />
	    				</td>
	    				<td class="dialogue_td_text">
	    					<span class="required">*</span>
	    					<s:text name="page.display.text.lccaccount" />
	    				</td>
	    				<td class="dialogue_td_input">
	    					<s:textfield name="user.lccAccount" theme="simple" />
	    				</td>
	    			</tr>
    			</s:else>
    		</s:else>
    		<s:if test="!changeSelf">
				<s:if test="roleList!=null and roleList.size()!=0">
    				<tr>
    					<td class="dialogue_td_text">
    						<span class="required">*</span>
    						<s:text name="page.text.userrole" />
    					</td>
    					<td class="dialogue_td_input">
    						<s:select list="roleList" name="roleCode" listKey="roleCode" listValue="roleName"  onchange="if(this.value=='ROLE_CUSTOMER_ADMIN'){selectdept.disabled='true'}else{selectdept.disabled=''}">
    						</s:select>
    					</td>
    					<td colspan="2">&nbsp;</td>
    					<!--<security:authorize ifAllGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN">
 							<td colspan="2">&nbsp;</td>   					
    					</security:authorize>
    					<!--<security:authorize ifNotGranted="ROLE_ADMIN">
    						<security:authorize ifAllGranted="ROLE_CUSTOMER_ADMIN">
	    							<td class="dialogue_td_text">
	    								<s:text name="page.user.department" />
	    							</td>
	    							<s:if test="roleCode=='ROLE_CUSTOMER_ADMIN'">
		    							<td class="dialogue_td_input">
		    								<s:select id="selectdept" list="departmentList" name="user.department.id" listKey="id" listValue="departmentName" headerKey="-1" headerValue="" disabled="true">
		    								</s:select>
		    							</td>
	    							</s:if>
	    							<s:else>
		    							<td class="dialogue_td_input">
		    								<s:select id="selectdept" list="departmentList" name="user.department.id" listKey="id" listValue="departmentName" headerKey="-1" headerValue="">
		    								</s:select>
		    							</td>	    								
	    							</s:else>
    						</security:authorize>
    					</security:authorize>-->
    				</tr>		
    			</s:if>
				<s:if test="user.id==null">
					<td class="dialogue_td_text">
						<s:text name="page.display.text.lccaccount" />
					</td>
					<td class="dialogue_td_input" colspan="3">
						<s:textfield name="user.lccAccount" theme="simple" />
					</td>
				</s:if>
				</s:if> 
    	</table>
    	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
    	<div class="disable_tr user_disable_dialogue">
    		<span><s:text name="page.display.text.oldpassword" /></span>
    		<s:password name="oldPassword" theme="simple" /><br/>
    		<span><s:text name="page.display.text.newpassword" /></span>
    		<s:password name="newPassword" theme="simple" /><br/>
    		<span><s:text name="page.display.text.repeatpassword" /></span>
    		<s:password name="rePass" theme="simple" /><br/>
    	</div>
  	</s:form>
  </body>
</html>
