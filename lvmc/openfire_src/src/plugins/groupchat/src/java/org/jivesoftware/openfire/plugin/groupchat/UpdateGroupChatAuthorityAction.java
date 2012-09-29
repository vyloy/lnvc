package org.jivesoftware.openfire.plugin.groupchat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class UpdateGroupChatAuthorityAction extends BaseAction{

	private static final Logger log = LoggerFactory.getLogger(UpdateGroupChatAuthorityAction.class);
	
	@Override
	public List execute(IQ request) {
		Connection con = null;
        PreparedStatement pstmt = null;
        boolean abortTransaction = false;
        ResultSet rs = null;
        InfoBean bean = new InfoBean();
        List replys = new ArrayList();
		try {
			con = DbConnectionManager.getTransactionConnection();
			//con.setAutoCommit(false);
			parseXML(request,con,bean);
			pstmt = con.prepareStatement("delete from im_groupchat_authority where user_id=(select user_id from sys_user where lcc_account=?) and groupchat_id=(select roomid from ofmucroom where name=? and serviceid=(select serviceid from ofmucservice where subdomain='groupchat'))");
			pstmt.setString(1, bean.getRoomName());
			pstmt.setString(2, bean.getRoomName());
			pstmt.execute();
			pstmt.close();
			
			if(checkList(bean.getMarks())){
				pstmt = con.prepareStatement("insert into im_groupchat_authority(user_id,im_authority_id,groupchat_id) " +
						"select (select user_id from sys_user where lcc_account=?) user_id,id," +
						"(select roomid from ofmucroom where name=? and serviceid=(select serviceid from ofmucservice " +
						"where subdomain='groupchat')) groupchat_id from im_authority where mark=? and " +
						"authority_type=(select id from im_authority_type where type_name='groupchat')");
				for(String mark: bean.getMarks()){
					pstmt.setString(1, bean.getUser());
					pstmt.setString(2, bean.getRoomName());
					pstmt.setString(3, mark);
					pstmt.addBatch();
				}
				pstmt.executeBatch();
				pstmt.close();
			}
			pstmt = con.prepareStatement("select mark from im_authority where user_id=(select user_id from sys_user where lcc_account=?)" +
					" and authority_type=(select id from im_authority_type where type_name='groupchat') " +
					"and groupchat_id=(select roomid from ofmucroom where name=? and " +
					"serviceid=(select serviceid from ofmucservice where subdomain='groupchat'))");
			pstmt.setString(1, bean.getUser());
			pstmt.setString(2, bean.getRoomName());
			rs = pstmt.executeQuery();
			bean.getMarks().clear();
			while(rs.next()){
				bean.addAuthority(rs.getString("mark"));
			}
			replys.add(this.getSuccessReplyIQ(request, bean));
        }
        catch (Exception e) {
        	log.error("FetchGroupChatAuthority error", e);
            abortTransaction = true;
//            try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
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
        operate.addAttribute("name", ActionFactory.UPDATE_GROUPCHAT_AUTHORITY);
        operate.addElement("result").addText(Constants.FAIL);
        operate.addElement("message").addText(message);
        reply.setChildElement(queryResult);
		return reply;
	}
	
	public IQ getSuccessReplyIQ(IQ request,InfoBean bean){
		IQ reply = IQ.createResultIQ(request);
		reply.setTo(bean.getUserJid());
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addAttribute("name", ActionFactory.UPDATE_GROUPCHAT_AUTHORITY);
        List<String> marks = bean.getMarks();
    	
		Element roomJidElement = operate.addElement("roomjid");
		roomJidElement.addAttribute("name", bean.getRoomJid());
		if(checkList(marks)){
			for(String mark:marks){
				roomJidElement.addElement("mark").addText(mark);
			}
		}
        reply.setChildElement(queryResult);
		return reply;
	}
	
	
	public InfoBean parseXML(IQ request, Connection con,InfoBean bean) throws Exception{
		String userJid = request.getFrom().toBareJID();
		bean.setUserJid(userJid);
		Element query = request.getChildElement();
		Element operate = query.element("operate");
//        String command = operate.attributeValue("name");
        Element userElement = operate.element("user");
        String user = userElement.getText();
        bean.setUser(user);
        bean.setUserJid(user);
        List list = operate.elements("mark");
        if(checkList(list)){
        	for(int i=0;i<list.size();i++){
        		Element e = (Element)list.get(0);
        		bean.addAuthority(e.getText());
        	}
        }
        Element roomJidElement = operate.element("roomjid");
        bean.setRoomJid(roomJidElement.getText());
        bean.setRoomName(roomJidElement.getText());
        return bean;
	}
	
	class InfoBean{
		private String roomJid;
		private List<String> marks = new ArrayList<String>();;
		private String userJid;
		private String user;
		private String roomName;
		
		
		
		public String getRoomName() {
			return roomName;
		}

		public void setRoomName(String roomName) {
			if(roomName!=null){
				int idx = roomName.indexOf("@");
				if(idx>0){
					roomName = roomName.substring(1, idx);
				}
			}
			this.roomName = roomName;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			if(user!=null){
				int idx = user.indexOf("@");
				if(idx>0){
					user = user.substring(1, idx);
				}
			}
			this.user = user;
		}

		public void addAuthority(String mark){
			marks.add(mark);
		}
		
		public String getUserJid() {
			return userJid;
		}
		
		public void setUserJid(String userJid) {
			this.userJid = userJid;
		}

		public String getRoomJid() {
			return roomJid;
		}

		public void setRoomJid(String roomJid) {
			this.roomJid = roomJid;
		}

		public List<String> getMarks() {
			return marks;
		}

		public void setMarks(List<String> marks) {
			this.marks = marks;
		}
		
		
		
	}

	
}
