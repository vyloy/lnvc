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
import org.jivesoftware.openfire.plugin.groupchat.ApplyInGroupChatAction.ApplyInGroupChatInfoBean;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class FetchGroupChatAuthorityAction extends BaseAction{

	private static final Logger log = LoggerFactory.getLogger(FetchGroupChatAuthorityAction.class);
	
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
			if(checkList(bean.getRoomJids())){
				Set<Map.Entry<String,String>> set = bean.getRoomMap().entrySet();
				for(Map.Entry<String,String> entry: set){
					String roomJid = entry.getKey();
					String roomName = entry.getValue();
					bean.addAuthority(null, roomJid);
					pstmt = con.prepareStatement("select ia.mark mark from im_groupchat_authority iga," +
							"im_authority ia,ofmucroom mr,sys_user su where iga.groupchat_id=mr.roomid " +
							"and mr.name=? " +
							"and iga.im_authority_id=ia.id and su.lcc_account=? and iga.user_id=su.user_id");
					pstmt.setString(1, roomName);
					pstmt.setString(2, bean.getUser());
					rs = pstmt.executeQuery();
					while(rs.next()){
						String mark = rs.getString("mark");
						bean.addAuthority(mark, roomJid);
					}
					rs.close();
					pstmt.close();
				}
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
        operate.addAttribute("name", ActionFactory.FETCH_GROUPCHAT_AUTHORITY);
        operate.addElement("result").addText(Constants.FAIL);
        operate.addElement("message").addText(message);
        reply.setChildElement(queryResult);
		return reply;
	}
	
	public IQ getSuccessReplyIQ(IQ request,InfoBean bean){
		IQ reply = IQ.createResultIQ(request);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addAttribute("name", ActionFactory.FETCH_GROUPCHAT_AUTHORITY);
        Set<Map.Entry<String,List<String>>> set = bean.getAuthorityMap().entrySet();
        for(Map.Entry<String,List<String>> entry: set){
        	String roomJid = entry.getKey();
        	List<String> marks = entry.getValue();
        	if(checkList(marks)){
        		Element roomJidElement = operate.addElement("roomjid");
        		roomJidElement.addAttribute("name", roomJid);
        		for(String mark:marks){
        			roomJidElement.addElement("mark").addText(mark);
        		}
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
        List list = operate.elements("roomjid");
        if(checkList(list)){
        	for(int i=0;i<list.size();i++){
        		Element e = (Element)list.get(0);
        		bean.addRoomJid(e.getText());
        	}
        }
        return bean;
	}
	
	class InfoBean{
		private String user;
		private List<String> roomJids = new ArrayList<String>();;
		private String userJid;
		private Map<String,List<String>> authorityMap = new HashMap<String,List<String>>();
		private Map<String,String> roomMap = new HashMap<String,String>();
		
		
		public void addAuthority(String mark,String roomJid){
			List<String> authoritys = authorityMap.get(roomJid);
			if(authoritys==null){
				authoritys = new ArrayList<String>();
				authorityMap.put(roomJid, authoritys);
			}
			if(mark != null){
				authoritys.add(mark);
			}
		}
		
		public Map<String, String> getRoomMap() {
			return roomMap;
		}

		public void setRoomMap(Map<String, String> roomMap) {
			this.roomMap = roomMap;
		}

		public void addRoomJid(String roomJid){
			getRoomJids().add(roomJid);
			if(roomJid!=null && !"".equals(roomJid)){
				int idx = roomJid.indexOf("@");
				if(idx>0){
					roomMap.put(roomJid, roomJid.substring(0, idx));
				}else{
					roomMap.put(roomJid,roomJid);
				}
			}
		}
		
		public Map<String, List<String>> getAuthorityMap() {
			return authorityMap;
		}
		public void setAuthorityMap(Map<String, List<String>> authorityMap) {
			this.authorityMap = authorityMap;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public List<String> getRoomJids() {
			return roomJids;
		}
		public void setRoomJids(List<String> roomJids) {
			this.roomJids = roomJids;
		}
		public String getUserJid() {
			return userJid;
		}
		public void setUserJid(String userJid) {
			this.userJid = userJid;
		}
		
		
		
	}

	
}
