<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dialogue.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dialogue.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/dialogue.js"></script>
  </head>
  <body>
  	<div class="dialogue_over_bg">&nbsp;</div>
   <div class="model_dialogue">
    	<div class="dialogue_container">
    		<div class="dialogue_top_border">
    			<img src="images/dialogue_top_border.gif" class="top_border_left">
    			<div class="index_dialogue_title">&nbsp;</div>
    			<img src="images/top_top_border_img_right.gif" class="top_boder_right" />
    			<img src="images/dialogue_button_close.gif" class="dialogue_button_close" />
    		</div>
    		<iframe name="index_dialogue_iframe" class="index_dialogue_iframe" frameborder="0" src=""></iframe>
    		<div class="dialogue_showText">&nbsp;</div>
    		<div class="dialogue_button_div">
  				<hr class="dialogue_content_hr" />
  				<button class="dialogue_ok_button">
  					<s:text name="page.text.ok" />
  				</button>
  				<button class="dialogue_cancel_button">
  					<s:text name="page.text.cancel" />
  				</button>
  			</div>
  			<div class="confirm_button_div">
  				<button class="confirm_ok_button">
  					<s:text name="page.text.ok" />
  				</button>
  				<button class="confirm_cancel_button">
  					<s:text name="page.text.cancel" />
  				</button>
  			</div>
  			<div class="pass_button_div">
  				<button class="pass_ok_button">
  					<s:text name="page.text.ok" />
  				</button>
  				<button class="pass_cancel_button">
  					<s:text name="page.text.cancel" />
  				</button>
  			</div>
    		<div class="dialogue_bottom">
    			<img src="images/dialogue_bottom_left_bg.gif" class="dialogue_bottom_left" />
    			<img src="images/dialogue_bottom_right_bg.gif" class="dialogue_bottom_right" />
    		</div>
    	</div>
    </div>
    <div class="dialogue_hide_text">
    	<p class="chooser_equired"><s:text name="page.errormsg.chooserequired"></s:text></p>
    	<p class="title_confirm"><s:text name="page.general.confirmTitle" /></p>
    	<p class="delete_confirm"><s:text name="page.general.deleteconfirm" /></p>
    	<p class="closebill_confirm"><s:text name="page.general.closebillconfirm" /></p>
    	<p class="stopconf_confirm"><s:text name="page.general.stopconfconfirm" /></p>
    	<p class="pass_confirm"><s:text name="page.general.passconfirm" /></p>
    	<p class="desc_dialogue"><s:text name="page.text.desc" /></p>
    	<p class="sort_tip"><s:text name="page.text.sorttip" /></p>
    	<p class="title_error"><s:text name="page.general.errorTitle" /></p>
    	<p class="intrequired"><s:text name="page.general.intrequired" /></p>
    	<p class="page_over"><s:text name="page.general.pageover" /></p>
    	<p class="multichooseedit"><s:text name="page.errormsg.multichooseedit" /></p>
    	<p class="timeinputerror"><s:text name="page.errormsg.timeinputerror" /></p>
    </div>
  </body>
</html>
