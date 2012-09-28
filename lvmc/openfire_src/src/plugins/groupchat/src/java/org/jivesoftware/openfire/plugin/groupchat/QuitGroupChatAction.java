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
import org.jivesoftware.openfire.muc.MUCRole;
import org.jivesoftware.openfire.muc.MUCRoom;
import org.jivesoftware.openfire.muc.spi.MultiUserChatServiceImpl;
import org.jivesoftware.openfire.plugin.groupchat.SetGroupChatMembersAuthority.InfoBean;
import org.jivesoftware.openfire.plugin.groupchat.model.GroupChatBean;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.xmpp.packet.IQ;

public class QuitGroupChatAction extends BaseAction {

	private String roomjid;
	private String room;
	private String userjid;
	private String user;
	private boolean confirmed;

	@Override
	public List execute(IQ request) {
		parseXML(request);
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean abortTransaction = false;
		ResultSet rs = null;
		List replys = new ArrayList();
		try {
			con = DbConnectionManager.getTransactionConnection();
			pstmt = con
					.prepareStatement("select * from im_groupchat_owner go "
							+ "where go.user_id=(select user_id from sys_user where lcc_account=?)"
							+ " and go.groupchat_id in (select mr.roomid from ofmucroom mr,ofmucservice ms "
									+ "where mr.serviceid=ms.serviceid and ms.subdomain='groupchat' and mr.name =?)");
			pstmt.setString(1, user);
			pstmt.setString(2, room);
			rs = pstmt.executeQuery();
			PreparedStatement prepareStatement = con.prepareStatement("select mr.roomid from ofmucroom mr,ofmucservice ms where mr.serviceid=ms.serviceid and ms.subdomain='groupchat' and mr.name =?");
			prepareStatement.setString(1, room);
			ResultSet result = prepareStatement.executeQuery();
			if(!result.next())
				throw new RuntimeException("Failed to execute select mr.roomid from ofmucroom mr,ofmucservice ms where mr.serviceid=ms.serviceid and ms.subdomain='groupchat' and mr.name ="+room);
			long roomId = result.getLong(1);
			boolean creator = rs.next();
			MultiUserChatServiceImpl muc = (MultiUserChatServiceImpl) XMPPServer
					.getInstance().getMultiUserChatManager()
					.getMultiUserChatService("groupchat");
			MUCRoom chatRoom = muc.getChatRoom(room);
			MUCRole role = chatRoom.getOccupant(user);
			if (creator) {
				if(confirmed){
//					chatRoom.leaveRoom(role);
//					chatRoom.destroyRoom(roomjid, "群主退出，该群被解散。");
					pstmt = con.prepareStatement("delete from ofmucaffiliation omc where omc.roomid = ?");
					pstmt.setLong(1, roomId);
					pstmt.execute();
					pstmt = con.prepareStatement("delete from im_groupchat_owner igo where igo.groupchat_id =?");
					pstmt.setLong(1, roomId);
					pstmt.execute();
//					replys.add(getSuccessReplyIQ(request));
				}else{
					replys.add(getConfirmReplyIQ(request));
				}
			}else{
				/*chatRoom.leaveRoom(role);
				pstmt = con.prepareStatement("delete from ofmucaffiliation omc where omc.jid=? "
						+ "and omc.roomid = ?");
				pstmt.setString(1, userjid);
				pstmt.setLong(2, roomId);
				pstmt.execute();*/
				replys.add(getSuccessReplyIQ(request));
			}
		} catch (Exception e) {
			e.printStackTrace();
			abortTransaction = true;
			// try {
			// con.rollback();
			// } catch (SQLException e1) {
			// e1.printStackTrace();
			// }
			String message = null;

			replys.add(getFailReplyIQ(request, e.getMessage()));
		} finally {
			DbConnectionManager.closeStatement(pstmt);
			DbConnectionManager.closeTransactionConnection(con,
					abortTransaction);
		}

		return replys;
	}

	public IQ getFailReplyIQ(IQ request, String message) {
		IQ reply = IQ.createResultIQ(request);
		Element queryResult = DocumentHelper.createElement(QName.get(
				Constants.PREFIX_NAME, Constants.SPACE_NAME));
		Element operate = queryResult.addElement("operate");
		operate.addAttribute("name", ActionFactory.QUIT_GROUPCHAT);
		operate.addElement("result").addText(Constants.FAIL);
		operate.addElement("message").addText(message);
		reply.setChildElement(queryResult);
		return reply;
	}

	public IQ getConfirmReplyIQ(IQ request) {
		IQ reply = IQ.createResultIQ(request);
		Element queryResult = DocumentHelper.createElement(QName.get(
				Constants.PREFIX_NAME, Constants.SPACE_NAME));
		Element operate = queryResult.addElement("operate");
		operate.addAttribute("name", ActionFactory.QUIT_GROUPCHAT);
		operate.addElement("result").addText(Constants.SUCCESS);
		operate.addElement("roomjid").addText(roomjid);
		operate.addElement("confirm");
		reply.setChildElement(queryResult);
		return reply;
	}
	
	public IQ getSuccessReplyIQ(IQ request) {
		IQ reply = IQ.createResultIQ(request);
		Element queryResult = DocumentHelper.createElement(QName.get(
				Constants.PREFIX_NAME, Constants.SPACE_NAME));
		Element operate = queryResult.addElement("operate");
		operate.addAttribute("name", ActionFactory.QUIT_GROUPCHAT);
		operate.addElement("result").addText(Constants.SUCCESS);
		operate.addElement("roomjid").addText(roomjid);
		reply.setChildElement(queryResult);
		return reply;
	}

	public void parseXML(IQ request) {
		Element query = request.getChildElement();
		Element operate = query.element("operate");
		Element roomJid = operate.element("roomjid");
		this.roomjid = roomJid.getText();
		this.room=this.roomjid.substring(0,this.roomjid.indexOf('@'));
		Element applicant = operate.element("user");
		this.userjid = request.getFrom().toBareJID();
		this.user=applicant.getText();
		Element element = operate.element("confirm");
		if(element!=null)
			confirmed=true;
	}
}
