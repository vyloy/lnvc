package com.lorent.web.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.lorent.common.util.StringUtil;
import com.lorent.model.UserBean;
import com.lorent.util.MailUtil;
import com.lorent.util.PropertiesUtil;

public class GuestBookAction extends BaseAction{

	private static final Logger log = Logger.getLogger(GuestBookAction.class); 
	
	private static final long serialVersionUID = 1L;

	@Override
	public Object getModel() {
		return null;
	}
	
	public void sendAdvice()throws Exception{
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String sender = request.getParameter("sender");
			String subject = "Lvmc Problem & Advice";
			String content = request.getParameter("content");
			String sendTo = PropertiesUtil.getConstant("guestbook.email.address");
			UserBean user = serviceFacade.getUserService().getByLccAccount(sender);
			String contact = StringUtil.getFormatString(PropertiesUtil.getMsgResource("page.guestbook.contact"), 
					user.getUsername(), user.getRealName(), user.getLccAccount(), user.getEmail());
			MailUtil.sendEmail(new String[]{sendTo}, subject, content + contact);
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.write("SUCCESS");
			writer.flush();
			writer.close();
		}catch(Exception e){
			log.error("sendAdvice", e);
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.write("FAIL:" + e.getMessage());
			writer.flush();
			writer.close();
		}
	}

}
