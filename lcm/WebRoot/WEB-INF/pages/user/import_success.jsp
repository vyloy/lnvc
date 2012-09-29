<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type='text/javascript'>
var cancel_button=$('.dialogue_cancel_button',parent.document);
var ok_button=$('.dialogue_ok_button',parent.document);
var close_button=$('.dialogue_button_close',parent.document);
cancel_button.hide();
var ok_b=ok_button.clone();
ok_b.insertBefore(ok_button);
ok_button.hide();
var close_b=close_button.clone();
close_b.insertBefore(close_button);
close_button.hide();
close_b.unbind("click");
close_b.click(function(){
	ok_b.click();
});
ok_b.unbind("click");
ok_b.click(function(){
	var index_iframe=$('.index_iframe',window.top.document);
	index_iframe.attr("src", "app/organAction_showOrgan_organ_list.action");
	ok_b.remove();
	close_b.remove();
	$(".model_dialogue", window.top.document).fadeOut(300);
	$(".dialogue_over_bg", window.top.document).fadeOut(300);
	window.setTimeout(function(){
		ok_button.show();
		cancel_button.show();
		close_button.show();
		$(".index_dialogue_iframe", window.top.document).attr("src","");
	},400);
});
</script>
</head>
<body>
<p style='color:orange;text-align:center'>有记录${count}条，成功导入记录${success}条，<a href="users?id=${id}">点击</a>下载详细结果</p>
</body>
</html>