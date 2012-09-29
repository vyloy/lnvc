$(document).ready(function(){
	$(".dialogue_button_close").mouseover(function(){
		$(this).attr("src","images/dialogue_button_close_on.gif");
	});
	$(".dialogue_button_close").mouseout(function(){
		$(this).attr("src","images/dialogue_button_close.gif");
	});
	$(".dialogue_button_close").click(function(){
		closeDialogue();
	});
	$(".index_dialogue_iframe").load(function(){
		var frameSrc = $(window.frames["index_iframe"].document).attr("location");
		$(".index_dialogue_iframe").contents().find("#dialogue_form").submit(function(){return false;});
		$(".dialogue_cancel_button").unbind("click");
		$(".dialogue_cancel_button").click(function(){
			closeDialogue();
		});
//		$(".index_dialogue_iframe").contents().find(".dialogue_click_text").toggle(function(){
//			$(".index_dialogue_iframe").contents().find("."+$(this).attr("openobj")).fadeIn(300);
//			$(".index_dialogue_iframe").contents().find(".hideValue").attr("value",1);
//		},function(){
//			$(".index_dialogue_iframe").contents().find("."+$(this).attr("openobj")).fadeOut(300);
//			$(".index_dialogue_iframe").contents().find(".hideValue").attr("value",0);
//		});
		$(".dialogue_ok_button").unbind("click");
		$(".dialogue_ok_button").click(function(){
			var msg = validBeforeSubmit();
			trimInput();
			if(msg == ""){
				var fm = $(".index_dialogue_iframe").contents().find("#dialogue_form");
				fm[0].submit();
			}
		});
		$(".index_dialogue_iframe").contents().find(".click_to_submit").click(function(){
				var fm = $(".index_dialogue_iframe").contents().find(".dialogue_inner_form");
				var url = fm.attr("action");
				$.getJSON(url,fm.serialize(),function(data){
					data = eval("["+data+"]")[0];
					if(data["error"]){
						$(".index_dialogue_iframe").contents().find(".dialog_msg_div").html(data["error"]);
					}else if(data["success"]){
						$(".index_dialogue_iframe").contents().find(".dialogue_click_text").prepend(data["success"]+"<br/>");
						var disableDiv = $(".index_dialogue_iframe").contents().find(".disable_tr");
						disableDiv.fadeOut(300);
					}				
				});
		});
		$(".index_dialogue_iframe").contents().find(".click_to_cancel").click(function(){
			$(".index_dialogue_iframe").contents().find(".disable_tr").fadeOut(300);
		});
	});
});

$(document).ready(function(){
	var win = $(".model_dialogue");
	var border = $(".model_dialogue .dialogue_top_border");
	var docHeight = $(".index_container").height();
	var docWidth = $(".index_container").width();
	border.mousedown(function(even){
		even = even||window.event;
		var pos = win.position();
		var x = even.clientX-pos.left;
		var y = even.clientY-pos.top;
		var winHeight = win.height();
		var winWidth = win.width();
		$(document).mousemove(function(even){
			win.css("left",even.clientX-x);
			win.css("top",even.clientY-y);
			if(parseInt(win.css("left"))<=0){
				win.css("left",0);
			}
			if(parseInt(win.css("top"))>=(docHeight-winHeight)){
				win.css("top",docHeight-winHeight);
			}
			if(parseInt(win.css("top"))<=0){
				win.css("top",0);
			}
			if(parseInt(win.css("left"))>=(docWidth-winWidth)){
				win.css("left",docWidth-winWidth);
			}
		});
		$(document).mouseup(function(even){
			$(document).unbind("mousemove");
		});
	});
});

function trimInput(){
	$(".index_dialogue_iframe").contents().find(".dialogue_td_input input").each(function(){
		$(this).val($.trim($(this).val()));
	});
	$(".index_dialogue_iframe").contents().find(".dialogue_td_input textarea").each(function(){
		$(this).val($.trim($(this).val()));
	});
}

function validBeforeSubmit(){
	var errorMsg = "";
	//验证输入的time是否正确
	$(".index_dialogue_iframe").contents().find(".timepicker").each(function(){
		var temp = $(this).val();
		var isok = true;
		if(temp.length != 5){
			isok = false;
		}else{
			temp = temp.match(/^(\d{2}):(\d{2})$/);
			if(temp == null){
				isok = false;
			}else if(temp[1]>24 || temp[2]>60 ){
				isok = false;
			}
		}
		if(!isok){
			errorMsg = $(".dialogue_hide_text .timeinputerror").html();
			alert(errorMsg);
			return false;
		}
	});
	return errorMsg;
}

//打开编辑对话框
function openDialogue(src,title,openCallback,closeCallback,width,height,okButton){
	closeCallback = closeCallback||false;
	initDialogue(width,height,title);
	var ok = false;
	if(okButton||okButton===undefined){
		ok = true;
	}
	if(!ok){
		$(".dialogue_ok_button", window.top.document).css("display","none");
	}else{
		$(".dialogue_ok_button", window.top.document).css("display","");
	}
	var frame = $(".index_dialogue_iframe", window.top.document);
	frame.attr("src",src);
	if(closeCallback){
		$(".dialogue_button_close", window.top.document).live("click",function(){
			closeDialogue(closeCallback);
		});
	}
}
//关闭对话框
function closeDialogue(callback){
	$(".model_dialogue", window.top.document).fadeOut(300);
	$(".dialogue_over_bg", window.top.document).fadeOut(300);
	$(".index_dialogue_iframe", window.top.document).attr("src","");
	if(callback){
		callback();
	}
}
//打开提示确认对话框
function openConfirm(title,content,okBtn,cancelBtn,closeCallback,commitUrl,frame){
	var width = 300;
	var height = 120;
	title = title||"";
	content = content||"";
	okBtn = okBtn||false;
	cancelBtn = cancelBtn||false;
	closeCallback = closeCallback||false;
	commitUrl = commitUrl||false;
	initDialogue(width,height,title,null,content,150);
	var okButton = $(".confirm_ok_button", window.top.document);
	var temp = okButton.data("events");
	if(temp){
		//alert("openConfirm:" + temp["click"].length);
	}
	var closeButton = $(".confirm_cancel_button", window.top.document);
	okButton.unbind("click");
	closeButton.unbind("click");
	okButton.click(function(){
		if(cancelBtn){
			var fm;
			if(frame){
				fm = frame.contents().find(".content_form");
			}else{
				fm = $(".index_iframe").contents().find(".content_form");
			}
			if(commitUrl){
				fm[0].action = commitUrl;
			}
			//alert(fm[0].action + '?isdel=yes');
			fm[0].submit();
		}
		closeDialogue(closeCallback);
	});
	if(cancelBtn){
		closeButton.css("display","");
		closeButton.click(function(){
			closeDialogue(closeCallback);
		});
	}else{
		closeButton.css("display","none");
	}
	$(".dialogue_button_close", window.top.document).unbind("click");
	$(".dialogue_button_close", window.top.document).click(function(){
		closeDialogue(closeCallback);
	});
}

function openConfirm2(title,content,okBtn,okCallback,cancelBtn,closeCallback){
	var width = 300;
	var height = 120;
	initDialogue(width,height,title,null,content,150);
	var okButton = $(".confirm_ok_button", window.top.document);
	var temp = okButton.data("events");
	if(temp){
		//alert("openConfirm2:" + temp["click"].length);
	}
	var closeButton = $(".confirm_cancel_button", window.top.document);
	okButton.unbind("click");
	closeButton.unbind("click");
	if(okBtn){
		okButton.css("display","");
		okButton.click(function(){
			closeDialogue2(okCallback);
		});
	}else{
		okButton.css("display","none");
	}
	if(cancelBtn){
		closeButton.css("display","");
		closeButton.click(function(){
			closeDialogue2(closeCallback);
		});
	}else{
		closeButton.css("display","none");
	}
	$(".dialogue_button_close", window.top.document).unbind("click");
	$(".dialogue_button_close", window.top.document).click(function(){
		closeDialogue2(closeCallback);
	});
	
}

function closeDialogue2(callback){
	$(".model_dialogue", window.top.document).fadeOut(300);
	$(".dialogue_over_bg", window.top.document).fadeOut(300);
	if(callback){
		callback();
	}
}

//打开密码校验对话框
function openPassConfirm(src,id,callback,commitUrl,width,height){
	src = src||"";
	callback = callback||false;
	width = width||300;
	height = height||200;
	commitUrl = commitUrl||false;
	id = parseInt(id);
	title = $(".dialogue_hide_text .pass_confirm").html();
	initDialogue(width,height,title,null,null,200,true);
	var frame = $(".index_dialogue_iframe");
	frame.attr("src",src);
	frame.css("height",height-80);
	$(".index_dialogue_iframe").load(function(){
		var frame = $(".index_dialogue_iframe");
		frame.contents().find("#hiddenId").attr("value",id);
		var okButton = $(".pass_ok_button");
		var closeButton = $(".pass_cancel_button");
		okButton.unbind("click");
		closeButton.unbind("click");
		okButton.click(function(){
			var fm = frame.contents().find("#dialogue_form");
			$.getJSON(commitUrl,fm.serialize(),function(data){
				data = eval("["+data+"]")[0];
				if(data["error"]){
					$(".index_dialogue_iframe").contents().find(".dialog_msg_div").html(data["error"]);
				}else if(data["success"]){
					if(callback){
						callback();
					}
				}
			});
		});
		closeButton.click(function(){
			closeDialogue();
		});
	});
}

//初始化对话框
function initDialogue(width,height,title,openCallback,showText,speed,passCheck){
	height = height||400;
	width = width||600;
	openCallback = openCallback||false;
	showText = showText||false;
	speed = speed||300;
	var btnDiv = $(".dialogue_button_div", window.top.document).css("width",width-26);
	title = title||"";
	passCheck = passCheck||false;
	var dialogue =  $(".model_dialogue", window.top.document);
	dialogue.css("width",width);
	dialogue.css("height",height);
	var frame = $(".index_dialogue_iframe", window.top.document);
	var contentDiv = $(".dialogue_showText", window.top.document);
	var dialogueButtonDiv = $(".dialogue_button_div", window.top.document);
	var confirmButtonDiv = $(".confirm_button_div", window.top.document);
	var passButtonDiv = $(".pass_button_div", window.top.document);
	if(showText){
		contentDiv.css("width",width-26);
		contentDiv.css("display","block");
		contentDiv.html(showText);
		frame.css("width",0);
		frame.css("height",0);
		dialogueButtonDiv.css("visibility","hidden");
		passButtonDiv.css("visibility","hidden");
		confirmButtonDiv.css("visibility","visible");
	}else{
		frame.css("width",width-26);
		frame.css("height",height-95);
		frame.css("display","block");
		contentDiv.css("display","none");
		if(passCheck){
			passButtonDiv.css("visibility","visible");
			dialogueButtonDiv.css("visibility","hidden");
		}else{
			dialogueButtonDiv.css("visibility","visible");
			passButtonDiv.css("visibility","hidden");					
		}
		confirmButtonDiv.css("visibility","hidden");
	}
	var sWidth = $(window).width();
	var sHeight = $(window).height();
	dialogue.css("top",(sHeight-height)/2);
	dialogue.css("left",(sWidth-width)/2);
	$(".index_dialogue_title", window.top.document).html(title);
	dialogue.fadeIn(300,openCallback);
	$(".dialogue_over_bg", window.top.document).css("opacity",($.browser.msie?(0.2):(0.1)));
	$(".dialogue_over_bg", window.top.document).fadeIn(speed);
}

//document.onselectstart=function(){return false;}