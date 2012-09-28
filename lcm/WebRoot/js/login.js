$(document).ready(function(){
	$("#imageCode").click(function(){
		$(this).attr("src","imageCode?time="+(new Date().getTime()));
	});
	$("#changeLan").change(function(){
		if(this.value==="")return;
		var fm = $(document.loginForm);
		fm.attr("action","login/loginPage_changeLanguage.action");
		fm.submit();
	});
});
$(window).load(function(){
	if(window.parent!=window)
  				window.parent.document.location.href = "login/loginPage_toLoginPage.action";
});