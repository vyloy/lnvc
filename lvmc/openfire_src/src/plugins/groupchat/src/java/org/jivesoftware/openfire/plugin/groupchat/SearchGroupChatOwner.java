package org.jivesoftware.openfire.plugin.groupchat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.muc.MUCRoom;
import org.jivesoftware.openfire.muc.spi.MultiUserChatServiceImpl;
import org.jivesoftware.openfire.plugin.groupchat.model.GroupChatBean;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class SearchGroupChatOwner extends BaseAction{

	private static final Logger Log = LoggerFactory.getLogger(ApplyInGroupChatAction.class);
		
		@Override
		public List execute(IQ request) {
			Connection con = null;
	        PreparedStatement pstmt = null;
	        boolean abortTransaction = false;
	        ResultSet rs = null;
	        GroupChatBean bean = new GroupChatBean();
	        List replys = new ArrayList();
			try {
				con = DbConnectionManager.getTransactionConnection();
				//con.setAutoCommit(false);
				parseXML(request,con,bean);
				pstmt = con.prepareStatement("select ma.jid from ofmucaffiliation ma,ofmucroom mr,ofmucservice ms " +
						"where ma.roomid=mr.roomid and ma.affiliation=10 and " +
						"mr.serviceid=ms.serviceid and ms.subdomain='groupchat' and mr.name=?");
				pstmt.setString(1, bean.getRoomJid());
				List<String> roomjids = new ArrayList<String>();
				rs = pstmt.executeQuery();
				MultiUserChatServiceImpl muc = (MultiUserChatServiceImpl)XMPPServer.getInstance().getMultiUserChatManager().getMultiUserChatService("groupchat");
				while(rs.next()){
					String name = rs.getString(1);
					MUCRoom room = muc.getChatRoom(name);
					roomjids.add(room.getJID().toFullJID());
				}
	            replys.add(this.getSuccessReplyIQ(request, roomjids));
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
	        operate.addAttribute("name", ActionFactory.GET_GROUPCHAT);
	        operate.addElement("result").addText(Constants.FAIL);
	        operate.addElement("message").addText(message);
	        reply.setChildElement(queryResult);
			return reply;
		}
		
		public IQ getSuccessReplyIQ(IQ request,List<String> roomjids){
			IQ reply = IQ.createResultIQ(request);
			//reply.setTo(userJid);
	        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
	        Element operate = queryResult.addElement("operate");
	        operate.addElement("result").addText(Constants.SUCCESS);
	        operate.addAttribute("name", ActionFactory.GET_GROUPCHAT);
	        if(roomjids!=null && roomjids.size()>0){
	        	for(String roomjid:roomjids){
	        		operate.addElement("roomjid").addText(roomjid);
	            }
	        }
	        reply.setChildElement(queryResult);
			return reply;
		}
		
		
		public GroupChatBean parseXML(IQ request, Connection con,GroupChatBean bean) throws Exception{
//			String userJid = request.getFrom().toBareJID();
//			bean.setUserJid(userJid);
			Element query = request.getChildElement();
			Element operate = query.element("operate");
//	        String command = operate.attributeValue("name");
	        Element roomJid = operate.element("roomjid");
	        String roomJidStr = roomJid.getText();
	        bean.setRoomJid(roomJidStr);
	        return bean;
		}

}
