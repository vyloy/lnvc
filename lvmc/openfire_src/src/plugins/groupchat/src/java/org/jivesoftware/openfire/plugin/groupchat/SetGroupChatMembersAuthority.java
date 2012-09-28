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
import org.jivesoftware.openfire.plugin.groupchat.ApplyInGroupChatAction.ApplyInGroupChatInfoBean;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class SetGroupChatMembersAuthority extends BaseAction{

	private static final Logger Log = LoggerFactory.getLogger(SetGroupChatMembersAuthority.class);
		
		@Override
		public List execute(IQ request) {
			Connection con = null;
	        PreparedStatement pstmt = null;
	        boolean abortTransaction = false;
	        ResultSet rs = null;
	        InfoBean bean = new InfoBean();
//	        List replys = new ArrayList();
			try {
				con = DbConnectionManager.getTransactionConnection();
				//con.setAutoCommit(false);
				parseXML(request,con,bean);
				/*insert into im_groupchat_authority (creator,create_time,user_id,im_authority_id,groupchat_id) select su.user_id,now(),su.user_id,ia.id,mr.roomid  
				from im_authority ia,im_authority_type iat,sys_user su,ofmucroom mr,ofmucservice ms where ia.authority_type=iat.id and iat.type_name='groupchat' and 
				su.lcc_account='' and mr.name='' and mr.serviceid=ms.serviceid and ms.subdomain='groupchat'*/
				pstmt = con.prepareStatement("insert into im_groupchat_authority " +
						"(creator,create_time,user_id,im_authority_id,groupchat_id) " +
						"select su.user_id,now(),su.user_id,ia.id,mr.roomid from im_authority ia," +
						"im_authority_type iat,sys_user su,ofmucroom mr,ofmucservice ms " +
						"where ia.authority_type=iat.id and iat.type_name='groupchat' and " +
						"su.lcc_account=? and mr.name=? and mr.serviceid=ms.serviceid " +
						"and ms.subdomain='groupchat'");
				pstmt.setString(1, bean.getCreator());
				pstmt.setString(2, bean.getRoomName());
				pstmt.execute();
				pstmt.close();
				pstmt = con.prepareStatement("insert into im_groupchat_owner (groupchat_id,user_id) values " +
						"((select mr.roomid from ofmucroom mr,ofmucservice ms where mr.serviceid=ms.serviceid " +
						"and ms.subdomain='groupchat' and mr.name=?),(select user_id from sys_user where " +
						"lcc_account=?))");
				
				pstmt.setString(1, bean.getRoomName());
				pstmt.setString(2, bean.getCreator());
				pstmt.execute();
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
	            
//	            replys.add(this.getFailReplyIQ(request, e.getMessage()));
	        }
	        finally {
	            DbConnectionManager.closeStatement(pstmt);
	            DbConnectionManager.closeTransactionConnection(con, abortTransaction);
	        }
			
			return null;
		}

		
//		
		
		
		public InfoBean parseXML(IQ request, Connection con,InfoBean bean) throws Exception{
			
			Element query = request.getChildElement();
			Element operate = query.element("operate");
//	        String command = operate.attributeValue("name");
	        Element roomJid = operate.element("roomjid");
	        String roomJidStr = roomJid.getText();
	        bean.setRoomJid(roomJidStr);
	        Element applicant = operate.element("creator");
	        String creator = applicant.getText();
	        bean.setCreator(creator);
	        List members = operate.elements("member");
	        if(members != null && members.size()>0){
	        	
	        	for(int i=0;i<members.size();i++){
		        	Element e = (Element)members.get(i);
		        	bean.addMember(e.getText());
		        }
	        }
	        
	        
	        return bean;
		}
		
		class InfoBean{
			private String creator;
			private List<String> members = new ArrayList<String>();;
			private String roomJid;
			
			public String getRoomName(){
				String name = null;
				if(roomJid!=null && !"".equals(roomJid.trim())){
					int idx = roomJid.indexOf("@");
					if(idx>0){
						name = roomJid.substring(0, idx);
					}
				}
				return name;
			}
			
			public String getRoomJid() {
				return roomJid;
			}
			public void setRoomJid(String roomJid) {
				this.roomJid = roomJid;
			}
			public String getCreator() {
				return creator;
			}
			public void setCreator(String creator) {
				this.creator = creator;
			}
			public List<String> getMembers() {
				return members;
			}
			public void setMembers(List<String> members) {
				this.members = members;
			}
			
			public void addMember(String member){
				getMembers().add(member);
			}
			
		}
}
