package org.jivesoftware.openfire.plugin.groupchat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.SessionManager;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.jivesoftware.openfire.session.ClientSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class ApplyInGroupChatAction extends BaseAction{

	private static final Logger Log = LoggerFactory.getLogger(ApplyInGroupChatAction.class);
		
		@Override
		public List execute(IQ request) {
			Connection con = null;
	        PreparedStatement pstmt = null;
	        boolean abortTransaction = false;
	        ResultSet rs = null;
	        ApplyInGroupChatInfoBean bean = new ApplyInGroupChatInfoBean();
	        List replys = new ArrayList();
			try {
				con = DbConnectionManager.getTransactionConnection();
				//con.setAutoCommit(false);
				parseXML(request,con,bean);
				pstmt = con.prepareStatement("select ma.jid,mr.naturalname,su.lcc_account from ofmucaffiliation ma,ofmucroom mr,ofmucservice ms,im_groupchat_owner igo,sys_user su " +
						"where ma.roomid=mr.roomid and ma.affiliation=10 and " +
						"mr.serviceid=ms.serviceid and ms.subdomain='groupchat' and mr.name=? and igo.user_id=su.user_id " +
						"and su.lcc_account=substring(ma.jid,1,position('@' in ma.jid)-1) and igo.groupchat_id=mr.roomid");
				pstmt.setString(1, bean.getRoomJidPrefix());
				rs = pstmt.executeQuery();
//				MultiUserChatServiceImpl muc = (MultiUserChatServiceImpl)XMPPServer.getInstance().getMultiUserChatManager().getMultiUserChatService("groupchat");
//				UserManager userManager = XMPPServer.getInstance().getUserManager();
				SessionManager sessionManager = XMPPServer.getInstance().getSessionManager();
				if(rs.next()){
					String name = rs.getString(1);
					String resource = "Spark 2.6.3";
//					String userName = "";
					Collection<ClientSession> sessions = sessionManager.getSessions(name.split("@")[0]);
//					Collection<ClientSession> sessions = sessionManager.getSessions();
					if(sessions!=null && sessions.size()>0){
						for(ClientSession session:sessions){
							resource = session.getAddress().getResource();
//							userName = session.getUsername();
							break;
						}
					}
//					String account = rs.getString("lcc_account");
//					User user = userManager.getUser(account);
//					String roomName = rs.getString(2);
//					bean.setRoomName(roomName);
//					MUCRoom room = muc.getChatRoom(name);
//					roomjids.add(room.getJID().toFullJID());
					replys.add(this.getSuccessReplyIQ(request, name + "/" + resource, bean));
				}
	            
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            abortTransaction = true;
//	            try {
//					con.rollback();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
	            String message = null;
	            
	            replys.add(this.getFailReplyIQ(request, e.getMessage()));
	        }
	        finally {
	            DbConnectionManager.closeStatement(pstmt);
	            DbConnectionManager.closeTransactionConnection(con, abortTransaction);
	        }
			
			return replys;
		}

		
		public IQ getFailReplyIQ(IQ request,String message){
			IQ reply = IQ.createResultIQ(request);
	        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
	        Element operate = queryResult.addElement("operate");
	        operate.addAttribute("name", ActionFactory.APPLYIN_GROUPCHAT);
	        operate.addElement("result").addText(Constants.FAIL);
	        operate.addElement("message").addText(message);
	        reply.setChildElement(queryResult);
			return reply;
		}
		
		public IQ getSuccessReplyIQ(IQ request,String ownerJid,ApplyInGroupChatInfoBean bean){
			IQ reply = IQ.createResultIQ(request);
			reply.setTo(ownerJid);
	        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
	        Element operate = queryResult.addElement("operate");
	        operate.addElement("result").addText(Constants.SUCCESS);
	        operate.addAttribute("name", ActionFactory.APPLYIN_GROUPCHAT);
	        operate.addElement("applicant").addText(bean.getApplicantJid());
	        operate.addElement("applicantname").addText(bean.getApplicantName());
	        operate.addElement("roomjid").addText(bean.getRoomJid());
//	        operate.addElement("roomname").addText(bean.getRoomName());
	        reply.setChildElement(queryResult);
			return reply;
		}
		
		
		public ApplyInGroupChatInfoBean parseXML(IQ request, Connection con,ApplyInGroupChatInfoBean bean) throws Exception{
			String userJid = request.getFrom().toBareJID();
			bean.setApplicantJid(userJid);
			Element query = request.getChildElement();
			Element operate = query.element("operate");
//	        String command = operate.attributeValue("name");
	        Element roomJid = operate.element("roomjid");
	        String roomJidStr = roomJid.getText();
	        bean.setRoomJid(roomJidStr);
	        Element applicant = operate.element("applicant");
	        String applicantName = applicant.getText();
	        bean.setApplicantName(applicantName);
	        return bean;
		}
		
		class ApplyInGroupChatInfoBean{
			private String applicantJid;
			private String applicantName;
			private String roomJid;
			private String ownerJid;
			private String roomName;
			private String roomJidPrefix;
			
			
			
			public String getRoomJidPrefix() {
				return roomJidPrefix;
			}
			public void setRoomJidPrefix(String roomJidPrefix) {
				this.roomJidPrefix = roomJidPrefix;
			}
			public String getRoomName() {
				return roomName;
			}
			public void setRoomName(String roomName) {
				this.roomName = roomName;
			}
			public String getOwnerJid() {
				return ownerJid;
			}
			public void setOwnerJid(String ownerJid) {
				this.ownerJid = ownerJid;
			}
			public String getApplicantJid() {
				return applicantJid;
			}
			public void setApplicantJid(String applicantJid) {
				this.applicantJid = applicantJid;
			}
			public String getApplicantName() {
				return applicantName;
			}
			public void setApplicantName(String applicantName) {
				this.applicantName = applicantName;
			}
			public String getRoomJid() {
				return roomJid;
			}
			public void setRoomJid(String roomJid) {
				if(roomJid!=null){
					int idx = roomJid.indexOf("@");
					if(idx>0){
						roomJidPrefix = roomJid.substring(0, idx);
					}
				}
				this.roomJid = roomJid;
			}
			
			
		}

}
