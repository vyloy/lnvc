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
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class SearchGroupChatAction extends BaseAction{

	private static final Logger Log = LoggerFactory.getLogger(SearchGroupChatAction.class);
		
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
				StringBuffer sqlBf = new StringBuffer();
				sqlBf.append("select name,naturalname,description from ofmucroom mr " +
						"where serviceid=(select serviceid from ofmucservice where subdomain='groupchat') " +
						"and roomid not in (select roomid from ofmucmember where jid=? union" +
						" select roomid from ofmucaffiliation where jid=? and affiliation!=40) ");
				if(bean.getSearchStr()!=null && !"".equals(bean.getSearchStr().trim())){
					sqlBf.append(" and naturalname=?");
				}
				pstmt = con.prepareStatement(sqlBf.toString());
				pstmt.setString(1, bean.getUserJid());
				pstmt.setString(2, bean.getUserJid());
				if(bean.getSearchStr()!=null && !"".equals(bean.getSearchStr().trim())){
					pstmt.setString(3, bean.getSearchStr());
				}
				List<String[]> roomInfos = new ArrayList<String[]>();
				rs = pstmt.executeQuery();
				MultiUserChatServiceImpl muc = (MultiUserChatServiceImpl)XMPPServer.getInstance().getMultiUserChatManager().getMultiUserChatService("groupchat");
				while(rs.next()){
					String name = rs.getString(1);
					MUCRoom room = muc.getChatRoom(name);
					roomInfos.add(new String[]{room.getJID().toBareJID(),rs.getString(2),rs.getString(3)});
				}
	            replys.add(this.getSuccessReplyIQ(request, roomInfos));
	            
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            abortTransaction = true;
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
	        operate.addAttribute("name", ActionFactory.SEARCH_GROUPCHAT);
	        operate.addElement("result").addText(Constants.FAIL);
	        operate.addElement("message").addText(message);
	        reply.setChildElement(queryResult);
			return reply;
		}
		
		public IQ getSuccessReplyIQ(IQ request,List<String[]> roomInfos){
			IQ reply = IQ.createResultIQ(request);
			//reply.setTo(userJid);
	        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
	        Element operate = queryResult.addElement("operate");
	        operate.addElement("result").addText(Constants.SUCCESS);
	        operate.addAttribute("name", ActionFactory.SEARCH_GROUPCHAT);
	        if(roomInfos!=null && roomInfos.size()>0){
	        	for(String[] info:roomInfos){
	        		Element infoElement = operate.addElement("info");
	        		infoElement.addElement("roomjid").addText(info[0]);
	        		infoElement.addElement("groupname").addText(info[1]);
	        		infoElement.addElement("groupdesc").addText(info[2]);
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
			Element groupName = operate.element("groupname");
	        bean.setSearchStr(groupName.getText());
	        return bean;
		}
		
		class InfoBean{
			String userJid;
			String searchStr;
			public String getUserJid() {
				return userJid;
			}
			public void setUserJid(String userJid) {
				this.userJid = userJid;
			}
			public String getSearchStr() {
				return searchStr;
			}
			public void setSearchStr(String searchStr) {
				this.searchStr = searchStr;
			}
			
		}

	}
