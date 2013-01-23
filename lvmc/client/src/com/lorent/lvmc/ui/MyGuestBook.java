package com.lorent.lvmc.ui;

import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lorent.common.guestbook.GuestBookDialog;
import com.lorent.common.util.HttpUtil;
import com.lorent.common.util.HttpUtil.ResponseEntity;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;

public class MyGuestBook extends GuestBookDialog {

	private static final Logger log = Logger.getLogger(MyGuestBook.class);
	
	public MyGuestBook(Frame parent, Boolean modal) {
		super(parent, modal);
	}

	@Override
	protected void beforeSubmit(String text) throws Exception {
		if(text.length() == 0){
			throw new Exception(StringUtil.getErrorString("input.isempty"));
		}
		if (text.length() > Constants.GUEST_BOOK_MAX) {
			throw new Exception(StringUtil.getFormatString(
					StringUtil.getErrorString("guestbook.maxlength"),
					Constants.GUEST_BOOK_MAX));
		}
		text = text.replaceAll("\\n", "<br/>");
		Map<String, String> map = new HashMap<String, String>();
		map.put("sender", DataUtil.getLoginInfo().getUsername());
		map.put("content", text);
		try{
			ResponseEntity re = HttpUtil.doPost("http://" + DataUtil.getLoginInfo().getServerIP() + ":6090/lcm/ajax/guestbook_sendAdvice.action", map);
			if(re.statusCode != 200 || !re.content.equals("SUCCESS")){
				throw new Exception("senderror statusCode = " + re.statusCode + " & content = " + re.content);
			}
		}catch(Exception e){
			log.error("beforeSubmit", e);
			throw new Exception(StringUtil.getErrorString("guestbook.senderror"));
		}
	}
}
