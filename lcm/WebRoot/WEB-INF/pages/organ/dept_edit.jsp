<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'mcu_edit.jsp' starting page</title>
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
	<script type="text/javascript" src="js/dialogue.js" charset="utf-8"></script>
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

		$.getJSON("ajax/organ_getDeptList.action", function(json){
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
			$("#selOrg").slideDown("fast");
			$("#selOrg").css({left:nodeOffset.left + "px", top:nodeOffset.top + nodeObj.height() + 4 + "px",overflow:'auto'}).slideDown("fast");
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
  	<s:form id="dialogue_form" namespace="/app" action="organ_doAddAndModifyDept_dept_edit" validate="true">
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
    				<s:text name="page.organ.dept_name" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="deptModel.departmentName" theme="simple"/>
    				<s:hidden name="deptModel.id" id="mNodeId"></s:hidden>
    			</td>
    			<td class="dialogue_td_text">
    				<s:text name="page.organ.dept_code" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="deptModel.code" theme="simple"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<span class="required">*</span>
    				<s:text name="page.organ.dept_parent" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="deptModel.parentName" theme="simple" id="pNode" cssStyle="float: left;" readonly="true"/>
    				<s:hidden name="deptModel.parentId" id="pNodeId"></s:hidden>			
					<a href="#" onclick="showMenu(); return false;" id="selBtn" style="float: left; margin-left: 5px;">选择</a>
					<div id="selOrg" class="selOrg" style="margin-top: 0px; width: 150px;height:150px;">
						<ul id="orgSel" class="ztree" ></ul>
					</div>
    			</td>
    			<td class="dialogue_td_text">
    				<s:text name="page.organ.dept_order" />
    			</td>
    			<td class="dialogue_td_input">
    				<s:textfield name="deptModel.order" theme="simple"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="dialogue_td_text">
    				<s:text name="page.department.departmentdesc" />
    			</td>
    			<td class="dialogue_td_input" colspan="3">
    				<s:textarea name="deptModel.departmentDesc" theme="simple" cssClass="dialogue_textarea" />
    			</td>
    		</tr>
    	</table>
  	</s:form>
  	<jsp:include flush="true" page="/WEB-INF/pages/include/dialogue_msg.jsp" />
  </body>
</html>
