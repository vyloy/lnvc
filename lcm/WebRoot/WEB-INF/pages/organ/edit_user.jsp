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
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.2.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.2.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.exedit-3.2.js"></script>
		<script type="text/javascript">
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				onClick: handleClick
			}
		};		
		
		function handleClick(event, treeId, treeNode){
			$("#pNode").val(treeNode.name);
			$("#pNodeId").val(treeNode.id);
			hideMenu();
			//$(".user_frame").attr("src", "app/organAction_toInnerUserList_user_list.action");
		}

		$.getJSON("ajax/organ_getDeptList.action?rmd=" + Math.random(), function(json){
  			//alert("JSON Data: " + json);
  			var temp = eval('('+json+')');
  			//alert(temp.fail);
  			$(document).ready(function(){
  				if(temp.fail!=undefined){
  					$("#orgtree").append(temp.fail);
  				}else{
					var z = $.fn.zTree.init($("#orgSel"), setting, temp);
					var parentNode = z.getNodeByParam("id", $("#pNodeId").val(), null);
					z.selectNode(parentNode);
					//z.expandNode(parentNode, true, false, true);
  				}
			});
		});

		function showMenu() {
			var nodeObj = $("#pNode");
			var nodeOffset = $("#pNode").offset();
			$("#selOrg").slideDown("fast");//show();
			$("#selOrg").css({left:nodeOffset.left + "px", top:nodeOffset.top + nodeObj.height() + 4 + "px",overflow:'auto'});
			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#selOrg").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "selBtn" || event.target.id == "selOrg" || $(event.target).parents("#selOrg").length>0)) {
				hideMenu();
			}
		}
		function hidePassDiv(){
			$(".disable_tr.user_disable_dialogue").fadeOut(300);
		}
		function showPassDiv(){
			$("#oldpasswd").val("");
			$("#newpasswd").val("");
			$("#reptpasswd").val("");
			$(".disable_tr.user_disable_dialogue").fadeIn(300);
		}
		function changePasswd(){
			var src = "ajax/organ_changePassword.action?oldpasswd=" + $("#oldpasswd").val() + "&newpasswd=" + $("#newpasswd").val() + "&reptpasswd=" + $("#reptpasswd").val();
			$.getJSON(encodeURI(src), function(json){
	  			//alert("JSON Data: " + json);
	  			var temp = eval('('+json+')');
	  			//alert(temp.fail);
	  			$(document).ready(function(){
	  				if(temp.fail!=undefined){
	  					alert(temp.fail);
	  					//parent.openConfirm2($(".dialogue_hide_text .confirmtitle").html(), temp.fail, true, null,false, null);
	  				}else{
	  					alert($(".dialogue_hide_text .changePasswdSuccess").html());
	  					hidePassDiv();
						//parent.openConfirm2($(".dialogue_hide_text .confirmtitle").html(), $(".dialogue_hide_text .changePasswdSuccess").html(), true, null,false, null);
	  				}
				});
			});
		}
		</script>
		<style type="text/css">
			.selOrg{
				display: none;
				position: absolute; 
				background-color: white; 
				overflow-x: auto; 
				overflow-y: scroll;
				border: 1px solid black; 
			}
		</style>
  </head>
  <body class="dialogue_body">
  	<s:form id="dialogue_form" namespace="/app" action="organ_doAddAndModifyUser_edit_user" validate="true">
  		<s:token />
  		<table border="0" cellspacing="0" cellpadding="0" class="dialogue_table">
    		<colgroup>
    			<col style="width: 18%;"  />
    			<col style="width: 32%;" />
    			<col style="width: 18%;"  />
    			<col style="width: 32%;" />
    		</colgroup>
    	<s:if test="userModel.userType==0">
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.display.text.username" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:if test="userModel.id==null">
    					<s:textfield name="userModel.username" />
    				</s:if>
    				<s:else>
    					<s:textfield name="userModel.username" disabled="true" />
    				</s:else>
    			</td>
    		</tr>
   			<tr>
   				<td class="dialogue_td_text">
   					<s:text name="page.display.text.password" />
   				</td>
   				<td class="dialogue_td_input" colspan="3">
   					<div openobj="disable_tr" class="dialogue_click_text" onclick="showPassDiv();">
   						<s:text name="page.display.text.changepassword" />
   					</div>
   					<s:hidden name="hideValue" cssClass="hideValue" value="0" />
   				</td>
   			</tr>
    	</s:if>
    	<s:else>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.display.text.username" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:if test="userModel.id==null">
    					<s:textfield name="userModel.username" />
    				</s:if>
    				<s:else>
    					<s:textfield name="userModel.username" disabled="true" />
    				</s:else>
    			</td>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.display.text.realname" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="userModel.realName" theme="simple"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.display.text.email" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="userModel.email" theme="simple"/>
    			</td>
    			<td class="dialogue_td_text">
    				<s:text name="page.display.text.mobile" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="userModel.mobile" theme="simple"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.display.text.phone" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="userModel.phone" theme="simple" />
    			</td>
    			<td class="dialogue_td_text">
    				<s:text name="page.display.text.lccaccount" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:if test="userModel.id==null"><s:textfield name="userModel.lccAccount" theme="simple" /></s:if>
    				<s:else><s:textfield name="userModel.lccAccount" theme="simple" disabled="true" /></s:else>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.user.parentdept" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="userModel.department.departmentName" theme="simple" id="pNode" cssStyle="float: left;" readonly="true"/>
    				<s:hidden name="userModel.department.id" id="pNodeId"></s:hidden>			
					<a href="#" onclick="showMenu(); return false;" id="selBtn" style="float: left; margin-left: 5px;">选择</a>
					<div id="selOrg" class="selOrg" style="margin-top: 0px; width: 150px;overflow: auto;height: 100px;">
						<ul id="orgSel" class="ztree"></ul>
					</div>
    			</td>
    			<td class="dialogue_td_text">
    				<s:text name="page.user.position" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="userModel.position" theme="simple" />
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.user.gender" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:select list="genderMap" name="userModel.gender" theme="simple"></s:select>
    			</td>
    			<td class="dialogue_td_text">
    				<s:text name="page.user.code" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="userModel.code" theme="simple" />
    			</td>
    		</tr>
    		<s:if test="userModel.id==null">
    			<tr>
    				<td class="dialogue_td_text">
    					<span class="required">*</span>
    					<s:text name="page.display.text.password" />
    				</td>
    				<td class="dialogue_td_input">
    					<s:password name="userModel.newPassword" value="" theme="simple" />
    				</td>
    				<td class="dialogue_td_text">
    					<span class="required">*</span>
    					<s:text name="page.display.text.repeatpassword" />
    				</td>
    				<td class="dialogue_td_input">
    					<s:password name="userModel.repeatPassword" theme="simple" />
    				</td>
    			</tr>
    		</s:if>
    		<s:else>
    			<tr>
    				<td class="dialogue_td_text">
    					<s:text name="page.display.text.password" />
    				</td>
    				<td class="dialogue_td_input" colspan="3">
    					<div openobj="disable_tr" class="dialogue_click_text" onclick="showPassDiv();">
    						<s:text name="page.display.text.changepassword" />
    					</div>
    					<s:hidden name="hideValue" cssClass="hideValue" value="0" />
    				</td>
    			</tr>
    		</s:else>	
    	</s:else>

    	</table>
    	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
    	<s:if test="userModel.id!=null">
	    	<div class="disable_tr user_disable_dialogue" style="top: 70px;">
	    		<span><s:text name="page.display.text.oldpassword" /></span>
	    		<input type="password" id="oldpasswd"/><br/>
	    		<span><s:text name="page.display.text.newpassword" /></span>
	    		<input type="password" id="newpasswd"/><br/>
	    		<span><s:text name="page.display.text.repeatpassword" /></span>
	    		<input type="password" id="reptpasswd"/><br/>
	    		<button onclick="changePasswd();"><s:text name="page.text.ok" /></button>
	    		<button onclick="hidePassDiv();"><s:text name="page.text.cancel" /></button>
	    	</div>
    	</s:if>
  	</s:form>
	<div class="dialogue_hide_text">
	   	<p class="changePasswdSuccess"><s:text name="page.user.change_passwd_success" /></p>
	   	<p class="confirmtitle"><s:text name="page.general.confirmTitle" /></p>
  	</div>
  </body>
</html>
