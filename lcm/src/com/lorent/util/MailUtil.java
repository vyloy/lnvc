package com.lorent.util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.lorent.model.ConferenceBean;
import com.lorent.model.CronConferenceBean;
import com.lorent.model.UserBean;

public class MailUtil {
	private static Logger log = Logger.getLogger(MailUtil.class);
	private final static String MESSAGE_FILENAME = "messageResource";
	private final static String INVITE_PREFIX = PropertiesUtil.getConstant("page.mail.invite.prefix");
	private final static String INVITE_SUFIX = PropertiesUtil.getConstant("page.mail.invite.sufix");
	/**
	 * 初始化Session
	 * @return
	 */
	private static Session initSession() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:comp/env");
			return (Session)envContext.lookup("mail/Session");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("initSession", e);
			return null;
		}
	}
//	/**
//	 * 发送email
//	 * @param receiver
//	 * @param subject
//	 * @param content
//	 */
//	private static void sendMail(String receiver,String subject,String content) {
//		try {
//			Session session = initSession();
//			Message msg = initMsg(session,subject,content);
//			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
//			send(session, msg);
//		} catch (AddressException e) {
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
	/**
	 * 群发email
	 * @param receivers
	 * @param subject
	 * @param content
	 */
	public static void sendEmail(String[]receivers,String subject,String content) {
		try {
			Session session = initSession();
			Message msg = initMsg(session, subject, content);
			InternetAddress[]recs = new InternetAddress[receivers.length];
			for(int i=0;i<receivers.length;i++){
				recs[i] = new InternetAddress(receivers[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, recs);
			
			//发送邮件			
			new BaseThread(session, msg, receivers){

				public void run() {
					Session session = (Session)(this.args[0]);
					Message msg = (Message)(this.args[1]);
					String[] receivers = (String[])(this.args[2]);
					try{
						Transport transport = session.getTransport();
						transport.connect(session.getProperty("mail.smtp.host"),session.getProperty("mail.smtp.username"),session.getProperty("mail.smtp.password"));
						transport.sendMessage(msg, msg.getAllRecipients());
						System.out.println("send email to "+receivers.toString()+" success.........");
						log.info("send email to "+receivers.toString()+" success.........");
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("send email to "+receivers.toString()+" fail.........");
						log.error("send email to "+receivers.toString()+" fail.........",e);
					}
				}
				
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("sendEmail", e);
		}
	}
	/**
	 * 初始化邮件message
	 * @param session
	 * @param subject
	 * @param content
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private static Message initMsg(Session session,String subject,String content) throws Exception  {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(PropertiesUtil.getConstant("mail.smtp.username")));
		msg.setSentDate(new Date());
		msg.setSubject(subject);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setContent(content,"text/html;charset=utf8");
		multipart.addBodyPart(mbp);
		msg.setContent(multipart);
		return msg;
	}
	
	/**
	 * 发送会议邀请的email
	 * @param conference
	 * @param inviter
	 */
	private static void conferenceAdvice(ConferenceBean conference,UserBean inviter,String propKey,String subjectKey) {
		try {
			if(conference.getUsers()==null||conference.getUsers().size()==0)
				return;
			String subject = PropertiesUtil.getProperty(MESSAGE_FILENAME, subjectKey,true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			PasswordUtil pu = new PasswordUtil();
			String confPass = conference.getConfPass()==null?PropertiesUtil.getProperty(MESSAGE_FILENAME, "page.text.null",true)
															:pu.getDesString(conference.getConfPass());
			String startTime = "";
			if(conference.getStartTime()!=null){
				startTime = sdf.format(conference.getStartTime());
			}else {
//				CronConferenceBean conf = (CronConferenceBean)conference;
//				CronCalendar calendar = new CronCalendar(conf.getCronExpression());
//				startTime =
//				DailyCalendar calendar = new DailyCalendar(conf.());
			}
			String content = PropertiesUtil.getProperty(MESSAGE_FILENAME, propKey,true);
			String confno = "";
			if(conference instanceof CronConferenceBean){//周期会议
				confno = ((CronConferenceBean)conference).getConfno();
			}else{
				confno = conference.getConfno();
			}
			String[]infos = new String[]{inviter.getUsername(),conference.getConfSubject(),startTime,confPass,confno};
			content = MessageFormat.format(content, infos);
			content = INVITE_PREFIX+content+INVITE_SUFIX;
//			String content = "success...";
			String[]receivers = new String[conference.getUsers().size()];
			int i = 0;
			for (UserBean user:conference.getUsers()) {
				receivers[i] = user.getEmail();
				i++;
			}
			sendEmail(receivers, subject, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建新会议时发送会议邀请的email
	 * @param conference
	 * @param inviter
	 */
	public static void conferenceInviteAdvice(ConferenceBean conference,UserBean inviter) {
		conferenceAdvice(conference, inviter, "page.mail.conference.invite.text","page.mail.conference.invite.subject");
	}
	/**
	 * 更改会议信息时发送的email
	 * @param conference
	 * @param editer
	 */
	public static void conferenceChangeAdvice(ConferenceBean conference,UserBean editer) {
		conferenceAdvice(conference, editer, "page.mail.conference.change.text","page.mail.conference.change.subject");
	}
	
	public static void main(String[] args) {
		UserBean user = new UserBean();
		user.setUsername("user1");
		user.setEmail("xyg123_1979@163.com");
		ConferenceBean conference = new ConferenceBean();
		conference.setConfSubject("confSubject");
		conference.setStartTime(new Date());
		conference.setEndTime(new Date());
		conference.setConfHost(user);
		conference.setConfDesc("test conference invite ...");
		conference.getUsers().add(user);
		conferenceChangeAdvice(conference, user);
	}
}