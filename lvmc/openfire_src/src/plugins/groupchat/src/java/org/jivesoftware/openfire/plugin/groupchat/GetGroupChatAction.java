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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;

public class GetGroupChatAction extends BaseAction{

private static final Logger Log = LoggerFactory.getLogger(GetGroupChatAction.class);
	
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
//			pstmt = con.prepareStatement("select mr.name from ofmucmember mm,ofmucroom mr," +
//					"ofmucservice ms where mm.roomid=mr.roomid and mm.jid=? and " +
//					"mr.serviceid=ms.serviceid and ms.subdomain='groupchat' union " +
//					"select mr.name from ofmucaffiliation ma,ofmucroom mr,ofmucservice ms " +
//					"where ma.roomid=mr.roomid and ma.jid=? and ma.affiliation=10 and " +
//					"mr.serviceid=ms.serviceid and ms.subdomain='groupchat'");
			pstmt = con.prepareStatement("select mr.name from ofmucaffiliation ma,ofmucroom mr,ofmucservice ms " +
					"where ma.roomid=mr.roomid and ma.jid=? and ma.affiliation=10 and " +
					"mr.serviceid=ms.serviceid and ms.subdomain='groupchat'");
			pstmt.setString(1, bean.getUserJid());
//			pstmt.setString(2, bean.getUserJid());
			List<String> roomjids = new ArrayList<String>();
			rs = pstmt.executeQuery();
			MultiUserChatServiceImpl muc = (MultiUserChatServiceImpl)XMPPServer.getInstance().getMultiUserChatManager().getMultiUserChatService("groupchat");
			while(rs.next()){
				String name = rs.getString(1);
				MUCRoom room = muc.getChatRoom(name);
				roomjids.add(room.getJID().toBareJID());
			}
//			pstmt = con.prepareStatement("insert into conference_vote (title,title_remark,effective_time,conference_id,begin_time,creator,is_start,is_close) values (?,?,?,?,?,?,?,?) returning conference_vote.id");
//            pstmt.setString(1, voteBean.getTitle());
//            pstmt.setString(2, voteBean.getTitleRemark());
//            pstmt.setInt(3, voteBean.getEffectiveTime());
//            pstmt.setInt(4, voteBean.getConferenceId());
//            pstmt.setTimestamp(5, voteBean.getBeginTime()!=null?new java.sql.Timestamp(voteBean.getBeginTime().getTime()):null);
//            pstmt.setInt(6, voteBean.getCreator());
//            pstmt.setBoolean(7, voteBean.isStart());
//            pstmt.setBoolean(8, voteBean.isClose());
//            rs = pstmt.executeQuery();
////            rs = pstmt.getGeneratedKeys(); //这里返回ResultSet ，rs里就是我们要的主键
//            int voteId = 0;
//            if(rs.next()){
//            	voteId = rs.getInt(1);
//            }
//            DbConnectionManager.fastcloseStmt(pstmt);
//            voteBean.setId(voteId);      
//            List<ConferenceSelectBean> selects = voteBean.getSelects();
//            for(ConferenceSelectBean selectBean:selects){
//            	pstmt = con.prepareStatement("insert into conference_select (conference_vote_id,type,select_title,select_remark,select_num) values (?,?,?,?,?) returning conference_select.id");
//            	pstmt.setInt(1, voteId);
//            	pstmt.setInt(2, selectBean.getType());
//            	pstmt.setString(3, selectBean.getSelectTitle());
//            	pstmt.setString(4, selectBean.getSelectRemark());
//            	pstmt.setInt(5, selectBean.getSelectNum());
//            	rs = pstmt.executeQuery();
////            	rs = pstmt.getGeneratedKeys();
//            	int selectId = 0;
//            	if(rs.next()){
//            		selectId = rs.getInt(1);
//            	}
//            	DbConnectionManager.fastcloseStmt(pstmt);
//            	selectBean.setId(selectId);
//            	selectBean.setConferenceVoteId(voteId);
//            	List<ConferenceOptionBean> options = selectBean.getOptions();
//            	for(ConferenceOptionBean optionBean:options){
//            		pstmt = con.prepareStatement("insert into conference_option (conference_select_id,option_name) values (?,?) returning conference_option.id");
//            		pstmt.setInt(1, selectId);
//            		pstmt.setString(2, optionBean.getOptionName());
////            		pstmt.executeUpdate();
//            		rs = pstmt.executeQuery();
//            		int optionId = 0;
//            		if(rs.next()){
//            			optionId = rs.getInt(1);
//            		}
//            		DbConnectionManager.fastcloseStmt(pstmt);
//            		optionBean.setId(optionId);
//            		optionBean.setConferenceSelectId(selectId);
//            	}
//            }
            
            replys.add(this.getSuccessReplyIQ(request, roomjids));
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
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
		String userJid = request.getFrom().toBareJID();
		bean.setUserJid(userJid);
        return bean;
	}

}
