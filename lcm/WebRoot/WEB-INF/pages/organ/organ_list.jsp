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
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/tab.css">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.2.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.2.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.exedit-3.2.js"></script>
	<script type="text/javascript" src="js/dialogue.js"></script>
		<script type="text/javascript" charset="utf8">
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				showLine: false,
				showIcon: true,
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom
				//addDiyDom: addDiyDom
			}
		};
		
		function addHoverDom(treeId, treeNode) {
			var aObj = $("#" + treeNode.tId + "_a");
			if ($("#orgBtn_add_"+treeNode.id).length>0) return;
			var editStr = "<span id='orgBtn_add_"+treeNode.id+"' class='button add' ></span>";
			if(treeNode.pId!=null){
				editStr = editStr + "<span id='orgBtn_modify_"+treeNode.id+"' class='button modify' ></span>" + 
				"<span id='orgBtn_delete_"+treeNode.id+"' class='button delete' ></span>";
			}
			aObj.append(editStr);
			$("#orgBtn_add_"+treeNode.id).bind("click", function(){
				var src = "app/organAction_toAddDept_dept_edit.action?parentId=" + treeNode.id + "&parentName=" + treeNode.name;
				//alert(encodeURI(src));
				openDialogue(encodeURI(src), $(".dialogue_hide_text .dept_add").html(),null,null,600,320);
			});
			$("#orgBtn_modify_"+treeNode.id).bind("click", function(){
				var parentNode = treeNode.getParentNode();
				var src = "app/organAction_toModifyDept_dept_edit.action?nodeId=" + treeNode.id + "&parentId=" + parentNode.id +  "&parentName=" + parentNode.name;
				//alert(encodeURI(src));
				openDialogue(encodeURI(src), $(".dialogue_hide_text .dept_modify").html(),null,null,600,320);
			});
			$("#orgBtn_delete_"+treeNode.id).bind("click", function(){
				openConfirm2($(".dialogue_hide_text .confirmtitle").html(), $(".dialogue_hide_text .dept_delete_confirm").html(), true, deleteDept,true, null);
			});
			$("#" + treeNode.tId + "_span").bind("click", function(){
				handleClick(treeNode);
			});
		};
		
		function deleteDept(){
			var treeObj = $.fn.zTree.getZTreeObj("orgtree");
			var selectNode = treeObj.getSelectedNodes()[0];
			$.getJSON("ajax/organ_deleteDept.action?nodeId=" + selectNode.id + "&rmd=" + Math.random(), function(json){
	  			//alert("JSON Data: " + json);
	  			var temp = eval('('+json+')');
	  			//alert(temp.fail);
	  			$(document).ready(function(){
	  				if(temp.fail!=undefined){
	  					openConfirm2($(".dialogue_hide_text .confirmtitle").html(), temp.fail, true, null, false, null);
	  				}else{
						openConfirm2($(".dialogue_hide_text .confirmtitle").html(), $(".dialogue_hide_text .dept_delete_ok").html(), true, refreshNode, false, null);
	  				}
				});
			});
		}
		
		function removeHoverDom(treeId, treeNode) {
			$("#orgBtn_add_"+treeNode.id).unbind("click").remove();
			$("#orgBtn_modify_"+treeNode.id).unbind("click").remove();
			$("#orgBtn_delete_"+treeNode.id).unbind("click").remove();
			$("#" + treeNode.tId + "_span").unbind("click");
		};
		
		function handleClick(treeNode){
			//alert(treeNode.name);
			var src = "app/organAction_showUserList_user_list.action?nodeId=" + treeNode.id;
			$(".user_frame").attr("src", src);
		}
		
		function getData(){
			//alert("");
			//var treeObj = $.fn.zTree.getZTreeObj("orgtree");
			//var selectNode = treeObj.getSelectedNodes()[0];
			//var parentNode = selectNode.getParentNode();
			//treeObj.expandNode(parentNode, true, false, true);
			$.getJSON("ajax/organ_getDeptList.action?rmd=" + Math.random(), function(json){
	  			//alert("JSON Data: " + json);
	  			var temp = eval('('+json+')');
	  			//alert(temp.fail);
	  			$(document).ready(function(){
	  				if(temp.fail!=undefined){
	  					openConfirm2($(".dialogue_hide_text .confirmtitle").html(), temp.fail, true, null, false, null);
	  				}else{
						$.fn.zTree.init($("#orgtree"), setting, temp);
	  				}
				});
			});
		}
		var selectNodepId;
		var selectNodeId;
		function refreshNode(){
			var treeObj = $.fn.zTree.getZTreeObj("orgtree");
			var selectNode = treeObj.getSelectedNodes()[0];
			selectNodeId = selectNode.id;
			selectNodepId = selectNode.pId;
			var url = "ajax/organ_getDeptList.action?rmd=" + Math.random();
			$.getJSON(url, function(json){
	  			//alert("JSON Data: " + json);
	  			var temp = eval('('+json+')');
	  			//alert(temp.fail);
	  			$(document).ready(function(){
	  				if(temp.fail!=undefined){
	  					openConfirm2($(".dialogue_hide_text .confirmtitle").html(), temp.fail, true, null, false, null);
	  				}else{
						var z = $.fn.zTree.init($("#orgtree"), setting, temp);
						var selectNode = z.getNodeByParam("id", selectNodeId, null);
						if(selectNode){
							z.selectNode(selectNode);
							//z.expand
						}else{
							var parentNode = z.getNodeByParam("id", selectNodepId, null);	
							z.selectNode(parentNode);
						}
	  				}
				});
			});
		}
		getData();
		</script>
		<style type="text/css">
			.ztree li span.button.add{margin-left:5px; margin-right:0px; background: url(images/org_add.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
			.ztree li span.button.modify{margin-left:5px; margin-right:0px; background: url(images/org_modify.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
			.ztree li span.button.delete{margin-left:5px; margin-right:0px; background: url(images/org_delete.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
		</style>
  </head>
  <body style="margin: 0px;width: 100%;height: 100%;">
  		<table style="width: 100%; height: 100%;" border="1" cellpadding="0" cellspacing="0">
  			<colgroup>
  				<col style="width:30%" />
  				<col style="width:70%" />
  			</colgroup>
  			<tr>
  				<td>
  					<div style="width: 100%; height: 100%;">
	  					<div class="tab_top_container">
	  						<div style="font-size: 12px">组织结构</div>
	  					</div>
						<ul id="orgtree" class="ztree"></ul>
					</div>
  				</td>
				<td>
					<iframe style='width:100%;height:100%;background-color: white;' class='user_frame' name='user_frame' frameborder="0"></iframe>
  				</td>
  			</tr>
  		</table>
  		<div class="dialogue_hide_text">
	    	<p class="dept_add"><s:text name="page.organ.dept_add" /></p>
	    	<p class="dept_modify"><s:text name="page.organ.dept_modify" /></p>
	    	<p class="dept_delete"><s:text name="page.organ.dept_delete" /></p>
	    	<p class="dept_delete_confirm"><s:text name="page.organ.dept_delete_confirm" /></p>
	    	<p class="dept_delete_ok"><s:text name="page.organ.dept_delete_ok" /></p>
	    	<p class="confirmtitle"><s:text name="page.general.confirmTitle" /></p>
    	</div>
  </body>
</html>
