$(document).ready(function(){
	var conHeight = $(".index_container").height();
	var conWidth = $(".index_container").width();
	var topContainerHeight = $(".top_container").height();
	var bottomContainerHeight = $(".index_bottom_container").height();
	var middleHeight = conHeight - topContainerHeight - bottomContainerHeight;
	var leftMenuHeight = conHeight-120;
	var menuLen = 0;
	initPage();
	function initPage(){
		$(".index_iframe").css("width",conWidth-185);
		$(".index_iframe").css("height",middleHeight);
		$(".index_middle_container").css("height",middleHeight);
		if(!$.browser.msie){
			$(".index_middle_container").css("padding-top",topContainerHeight);
		}
		$(".index_left_menu").css("height",middleHeight);
		$(".left_menu_img").css("top",0);
		$(".left_menu_img").css("left",0);
		var menus = $(".menu");
		var lis = $(".menu_list");
		menus.each(function(){menuLen++;});
		lis.css("height",middleHeight-(22*menuLen) - 33);
		$(".left_menu_hide").css("height",middleHeight);
		$(".menu_list div").css("backgroundImage","url(images/left_menulist_icon.gif)");
		lis.hide();
		$(lis.get(0)).show();
		$(menus.get(0)).addClass("menu_on opened");
		menus.click(function(){
			var index = menus.index($(this));
			var item = $(lis.get(index));
			var flag = item.css("display")=="none";
			lis.hide();
			if(flag){
				item.show();
				menus.removeClass("menu_on opened");
				$(this).addClass("menu_on opened");
				var temp = item.find("a").attr("href");
				$(".index_iframe").attr("src", temp);
			}else{
				$(this).removeClass("opened");
			}

		});
		$(".left_hide_img").click(function(){
			var flag = $(".index_left_menu").css("display")=="none";
			if(flag){
				$(".left_hide_img").attr("src","images/index_left_hide_button.gif");
				$(".index_iframe").css("width",conWidth-185);
				$(".index_iframe").css("left",172);
				$(".index_left_menu").show();
			}else{
				$(".left_hide_img").attr("src","images/index_left_hide_button_off.gif");
				$(".index_left_menu").hide();
				$(".index_iframe").css("width",conWidth-28);
				$(".index_iframe").css("left",16);
			}
		});
		menus.mouseover(function(){
			$(this).addClass("menu_on");
		});
		menus.mouseout(function(){
			if($(this).attr("class").indexOf("opened")<0)
				$(this).removeClass("menu_on");
		});
		$(".index_iframe").load(function(){
			var frameSrc = $(window.frames["index_iframe"].document).attr("location");
			$(".index_iframe").contents().find(".toplink").click(function(){
				doClick($(this), $(".index_iframe"));
			});
			$(".index_iframe").contents().find(".tab_top_search").click(function(){
				$(".index_iframe").contents().find(".search_container").slideToggle(200);
			});
			$(".index_iframe").contents().find(".selectAll").click(function(){
				var value = $(this).attr("checked");
				var choose = $(".index_iframe").contents().find("input:checkbox[name=choose]");
				choose.each(function(){
					$(this).attr("checked",value);
				})
			});
			$(".index_iframe").contents().find(".tab_top_submit").click(function(){
				var choose = $(".index_iframe").contents().find("input:checkbox[name=choose]:checked").val();
				if((!choose)){
					openConfirm(conTitle,choosRequired,true,false);
					return;
				}
				$(".index_iframe").contents().find(".content_form").attr("action",$(this).attr("id"));
				$(".index_iframe").contents().find(".content_form").submit();
			});
			$(".index_iframe").contents().find(".tab_search_button").click(function(){
				$(".index_iframe").contents().find(".tab_search_form").submit();
			});
			$(".index_iframe").contents().find(".tab_reset_button").click(function(){
				var formElements = $(".index_iframe").contents().find(".tab_search_form")[0].elements;
				for (var i=0; i<formElements.length; i++){
					var element = formElements[i];
					if (element.type == "submit") { continue; }
					if (element.type == "reset") { continue; }
					if (element.type == "button") { continue; }
					if (element.type == "hidden") { continue; }
					if (element.type == "text") { element.value =""; }
					if (element.type == "textarea") { element.value =""; }
					if (element.type == "checkbox") { element.checked = false; }
					if (element.type == "radio") { element.checked = false; }
					if (element.type == "select-multiple") { element.selectedIndex = 0; }
					if (element.type == "select-one") { element.selectedIndex = 0; }
				}
			});
			$(".index_iframe").contents().find(".data_title").click(function(){
				var msg = $(this).attr("alt")||"";
				msg = msg.split("*");
				var id = msg[0];
				var width = msg[1];
				var height = msg[2];
				var check = msg[3]==2;
				var src = $.trim(msg[4]);
				var commitUrl = msg[5];
				src = src+"?choose="+id;
				if(check){
					openPassConfirm("app/toPage_pages_include_dialogue_passconfirm.action",id,function(){
						$(".index_dialogue_iframe").attr("src","");
						openDialogue(src,$(".dialogue_hide_text .desc_dialogue").html(),null,null,width,height,false);
					},commitUrl,320,150);
				}else{
					$(".index_dialogue_iframe").attr("src","");
					openDialogue(src,$(".dialogue_hide_text .desc_dialogue").html(),null,null,width,height,false);
				}
			});
			$(".index_iframe").contents().find(".tab_data_table_header").find("td").click(function(){
				var sortUrl = $(".index_iframe").contents().find(".tab_data_table_header").find("#sortUrl")[0].value;
				var sortby = $(this).attr("sortby");
				if(sortby==undefined || sortUrl==undefined){//td没有定义sortby,sortUrl则退出
					return;
				}
				var url = sortUrl + "?sortby=" + sortby;
				$(".index_iframe").attr("src",url);
			}
			);
			$(".index_iframe").contents().find(".tab_data_table_header td").each(function(){
				if($(this).attr("sortby")){
					var tip = $(".dialogue_hide_text .sort_tip").html();
					$(this).attr("title",tip);
				}
			});
			$(".index_iframe").contents().find(".tab_pagejump").click(function(){
				doClickJumpTo($(this), $(".index_iframe"));
			});
			$(".index_iframe").contents().find(".user_frame").load(function(){
				$(this).contents().find(".tab_pagejump").click(function(){
					doClickJumpTo($(this), $(".index_iframe").contents().find(".user_frame"));
				});
			});
			$(".index_iframe").contents().find(".top_statistics").click(function(){
				var url = $(this).attr("id");
				window.open(url);
			});
			$(".index_iframe").contents().find(".user_frame").load(function(){
				$(this).contents().find(".toplink").click(function(){
					doClick($(this), $(".index_iframe").contents().find(".user_frame"));
				});
				$(this).contents().find(".selectAll").click(function(){
				var value = $(this).attr("checked");
				var choose = $(".index_iframe").contents().find(".user_frame").contents().find("input:checkbox[name=choose]");
				choose.each(function(){
					$(this).attr("checked",value);
				})
			});
			});

		});
	}
	$("a[href='javascript:void(0)']").live("click",function(){
		var src = "";
		if($(this).attr("action")==="top_link_useredit"){
			src = "app/organAction_toEditMyInfo_edit_user.action?currentTime="+$(this).attr("alt");
			openDialogue(src,$(this).attr("title"),false,false,500,300, false);
		}else if($(this).attr("action")==="refresh"){
			src = $(window.frames["index_iframe"].document).attr("location");
			$(".index_iframe").attr("src",src);
		}
	});
});

function doClick(obj, frame){
	var actionUrl = obj.attr("id");
	var conTitle = $(".dialogue_hide_text .title_confirm").html();
	var choosRequired = $(".dialogue_hide_text .chooser_equired").html();
	actionUrl = actionUrl.split("*");
	//如果是编辑或添加操作
	if(actionUrl[3]){
		var src = actionUrl[0];
		var width = actionUrl[1];
		var height = actionUrl[2];
		var checkBox = frame.contents().find("input:checkbox[name=choose]:checked")
		var choose = checkBox.val();
		var isedit = actionUrl[3]=="edit";
		if((!choose)&&isedit){		
			openConfirm(conTitle,choosRequired,true,false);
			return;
		}
		if((checkBox.length!=1)&&isedit){
			var multichooseedit = $(".dialogue_hide_text .multichooseedit").html();
			openConfirm(conTitle,multichooseedit,true,false);
			return;
		}
		var check = false;
		var commitUrl = "";
		if(isedit){
			var alt = checkBox.attr("alt");
			alt = alt.split("*");
			check = alt[0]==2;
			commitUrl = alt[1];
		}
		src = isedit?(src+"?choose="+choose):src;
		if(isedit&&check){
			openPassConfirm("app/toPage_pages_include_dialogue_passconfirm.action",choose,function(){
				$(".index_dialogue_iframe").attr("src","");
				openDialogue(src,$(".dialogue_hide_text .desc_dialogue").html(),null,null,width,height);
			},commitUrl,320,150);						
		}else{
			openDialogue(src,obj.attr("title"),null,null,width,height);
		}
	}
	//如果是其他操作
	else if(actionUrl[1]){
		var choose = frame.contents().find("input:checkbox[name=choose]:checked").val();
		if((!choose)){
			openConfirm(conTitle,choosRequired,true,false);
			return;
		}
		var text = "";
		var commitUrl = actionUrl[0];
		switch(actionUrl[1]){
			//删除操作
			case "delete":
				text = $(".dialogue_hide_text .delete_confirm").html();
				break;
			//改变账单的支付状态
			case "closeBill":
				text = $(".dialogue_hide_text .closebill_confirm").html();
				break;
			//终止会议
			case "stopConf":
				text = $(".dialogue_hide_text .stopconf_confirm").html();
//					closeBilling = "app/conferenceAction_stopConf_conflist.action";
				break;
		}
		//alert(commitUrl);
		openConfirm(conTitle,text,true,true,null,commitUrl,frame);
	}
}

function doClickJumpTo(obj, frame){
	var pageCont = obj.attr("alt");
	var inputCont = frame.contents().find("#currentPage").attr("value");
	var title = $(".dialogue_hide_text .title_error").html();
	var tip1 = $(".dialogue_hide_text .intrequired").html();
	var tip2 = $(".dialogue_hide_text .page_over").html();
	if(inputCont!=parseInt(inputCont)){
		openConfirm(title,tip1,true);
		frame.contents().find("#currentPage").attr("value","");
		return;				
	}
	inputCont = parseInt(inputCont);
	if(inputCont<1||inputCont>pageCont){
		openConfirm(title,tip2,true);
		frame.contents().find("#currentPage").attr("value","");
		return;
	}
	frame.contents().find(".tab_bottom_form").submit();
}

//document.onselectstart=function(){return false;}

window.onresize = function(){
	$(".index_container").css("width",document.body.clientWidth);
	$(".index_container").css("height",document.body.clientHeight);
	//alert(document.body.clientHeight);
	var conHeight = document.body.clientHeight;
	var conWidth = document.body.clientWidth;
	var topContainerHeight = $(".top_container").height();
	var bottomContainerHeight = $(".index_bottom_container").height();
	var middleHeight = conHeight - topContainerHeight - bottomContainerHeight;
	//alert(middleHeight);
	var leftMenuHeight = conHeight-120;
	if(!$.browser.msie){
		$(".index_middle_container").css("padding-top",topContainerHeight);
	}
	$(".index_middle_container").css("height",middleHeight);
	var middleDivHeight = $(".index_middle_container").height();
	var middleDivWidth = $(".index_middle_container").width();
	
	$(".index_iframe").css("width",conWidth-185);
	$(".index_iframe").css("height",middleHeight);
	
	$(".index_left_container").css("height",middleHeight);
	$(".index_left_menu").css("height",middleHeight);
	var menus = $(".menu");
	var menuLen = 0;
	menus.each(function(){menuLen++;});
	var lis = $(".menu_list");
	lis.css("height",middleHeight-(22*menuLen) - 33);
	$(".left_menu_hide").css("height",middleHeight);
	//$(".menu_list div").css("backgroundImage","url(images/left_menulist_icon.gif)");
}