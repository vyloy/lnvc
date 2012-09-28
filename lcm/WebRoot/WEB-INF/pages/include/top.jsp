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
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function quitconfirm(){
			var ret = window.confirm('<s:text name="logout.confirm" />');
			if(ret==true){
				window.location = '<%=basePath %>security_logout';
			}
		}

	</script>
  </head>
  
  <body class="top_body">
    <div class="top_container">
    	<div class="top_top_border">
    		<img src="images/top_top_boder_img_left.gif" id="top_boder_img_left">
    		<img src="images/top_top_border_img_right.gif" id="top_boder_img_right">
    	</div>
    	<div class="top_menu_container">
    		<div class="top_role_msg">
    			<img src="images/top_role.gif">
    			<s:text name="page.top.roleMsg">
    				<s:param><security:authentication property="principal.username"/></s:param>
    				<s:param>
    					<security:authorize ifAllGranted="ROLE_ADMIN">
    						<s:text name="page.role.admin" />
    					</security:authorize>
    					<security:authorize ifNotGranted="ROLE_ADMIN">
    						<security:authorize ifAllGranted="ROLE_CUSTOMER_ADMIN">
    							<s:text name="page.role.customer.admin" />
    						</security:authorize>
    						<security:authorize ifNotGranted="ROLE_CUSTOMER_ADMIN">
    							<security:authorize ifAllGranted="ROLE_CUSTOMER_DEP_ADMIN">
    								<s:text name="page.role.customer.depadmin" />
    							</security:authorize>
    							<security:authorize ifNotGranted="ROLE_CUSTOMER_DEP_ADMIN">
    								<security:authorize ifAllGranted="ROLE_CUSTOMER_USER">
    									<s:text name="page.role.customer.user" />
    								</security:authorize>
    								<security:authorize ifAllGranted="ROLE_MEMBER_USER">
    									<s:text name="page.role.memberuser" />
    								</security:authorize>
    							</security:authorize>
    						</security:authorize>
    					</security:authorize>	
    				</s:param>
    			</s:text>
    			<security:authorize ifNotGranted="ROLE_ADMIN">
	    			<s:text name="page.top.lccno">
	    				<s:param>
			   				<security:authentication property="principal.lccAccount"/>
	   					</s:param>
	   				</s:text>
   				</security:authorize>
    		</div>
    		<ul class="top_op_menu_list">
    			<!-- <li>
    				<img src="images/top_home.gif" />
    				<a href="<%=basePath %>"><s:text name="page.top.home" /></a>
    			</li>
    			<li>
    				<img src="images/top_pre.gif" />
    				<a href="javascript:history.go(-1)"><s:text name="page.top.pre" /></a>
    			</li>
    			<li>
    				<img src="images/top_next.gif" />
    				<a href="javascript:history.go(+1)"><s:text name="page.top.next" /></a>
    			</li> -->
    			<li>
    				<img src="images/top_refresh.gif" />
    				<a href="javascript:void(0)" action="refresh">
    					<s:text name="page.top.refresh" />
    				</a>
    			</li>
    			<li>
    				<img src="images/top_edit.gif" />
    				<a href="javascript:void(0)" title="<s:text name="page.top.useredit" />" 
    					action="top_link_useredit" alt="<s:property value='currentTime' />">
    					<s:text name="page.top.useredit" />
    				</a>
    			</li>
    			<li>
    				<img src="images/top_quit.gif" />
    				<a href="javascript:void(0)" onclick="quitconfirm();">
    					<s:text name="page.top.quit" />
    				</a>
    			</li>
    			<!-- <li>
    				<img src="images/top_role.gif" />
	    				<a href="javascript:void(0);" onclick="javascript:window.open('<s:text name="helpfilepath" />');" ><s:text name="page.top.help" /></a>
    			</li> -->
    		</ul>
    	</div>
    	<div class="customer_msg_container">&nbsp;</div>
    	<div class="top_msg_border_right" >&nbsp;</div>
    	<div class="top_msg_border_left" >&nbsp;</div>
		<img src="images/top_logo.gif" class="top_logo_img" />
    </div>
  </body>
</html>
