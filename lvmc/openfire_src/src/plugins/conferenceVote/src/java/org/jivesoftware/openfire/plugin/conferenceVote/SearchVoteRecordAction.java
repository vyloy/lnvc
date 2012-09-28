package org.jivesoftware.openfire.plugin.conferenceVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.plugin.conferenceVote.model.ConferenceOptionBean;
import org.jivesoftware.openfire.plugin.conferenceVote.model.ConferenceSelectBean;
import org.jivesoftware.openfire.plugin.conferenceVote.model.ConferenceVoteBean;
import org.jivesoftware.openfire.plugin.conferenceVote.model.ConferenceVoteRecordBean;
import org.jivesoftware.openfire.plugin.conferenceVote.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class SearchVoteRecordAction extends BaseAction {
	

	private static final Logger Log = LoggerFactory.getLogger(SearchVoteRecordAction.class);
	
	@Override
	public List execute(IQ request) {
		Connection con = null;
        PreparedStatement pstmt = null;
        boolean abortTransaction = false;
        ResultSet rs = null;
        ConferenceVoteBean voteBean = new ConferenceVoteBean();
        List replys = new ArrayList();
		try {
			con = DbConnectionManager.getTransactionConnection();
			parseXML(request,con,voteBean);
			pstmt = con.prepareStatement("select conference_select_id,conference_option_id from conference_vote_record where conference_id=? and user_id=? and conference_vote_id=?");
			pstmt.setInt(1, voteBean.getConferenceId());
			pstmt.setInt(2, voteBean.getUserId());
			pstmt.setInt(3, voteBean.getId());
			rs = pstmt.executeQuery();
			List<ConferenceVoteRecordBean> datas = new ArrayList<ConferenceVoteRecordBean>();
			while(rs.next()){
				ConferenceVoteRecordBean record = new ConferenceVoteRecordBean();
				record.setConferenceSelectId(rs.getInt("conference_select_id"));
				record.setConferenceOptionId(rs.getInt("conference_option_id"));
				datas.add(record);
			}
            replys.add(this.getSuccessReplyIQ(request, voteBean, datas));
//            List<String> userAddress = this.getUserAddress(selectBean.getRoomJid());
//            replys.add(userAddress);
        }
        catch (Exception e) {
        	e.printStackTrace();
            abortTransaction = true;
            String message = null;
            if(e instanceof ConferenceVoteException){
            	message = e.getMessage();
            }else{
            	message = "操作出错";
            }
            replys.add(this.getFailReplyIQ(request, voteBean, message));
        }
        finally {
            DbConnectionManager.closeStatement(pstmt);
            DbConnectionManager.closeTransactionConnection(con, abortTransaction);
        }
		
		return replys;
	}

	
	public IQ getFailReplyIQ(IQ request,ConferenceVoteBean voteBean, String message){
		IQ reply = IQ.createResultIQ(request);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addAttribute("name", ActionFactory.SEARCH_VOTE_RECORD);
        operate.addElement("result").addText(Constants.FAIL);
        operate.addElement("roomjid").addText(voteBean.getRoomJid());
        operate.addElement("message").addText(message);
        reply.setChildElement(queryResult);
		return reply;
	}
	
	public IQ getSuccessReplyIQ(IQ request,ConferenceVoteBean voteBean,List<ConferenceVoteRecordBean> datas){
		IQ reply = IQ.createResultIQ(request);
//		reply.setTo(userJid);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addAttribute("name", ActionFactory.SEARCH_VOTE_RECORD);
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addElement("roomjid").addText(voteBean.getRoomJid());
        operate.addElement("id").addText(String.valueOf(voteBean.getId()));
        
        Element searchs = operate.addElement("searchs");
//        List<ConferenceOptionBean> optionList = selectBean.getOptions();
        for(ConferenceVoteRecordBean record:datas){
        	Element search = searchs.addElement("search");
        	search.addElement("select_id").addText(String.valueOf(record.getConferenceSelectId()));
        	search.addElement("option_id").addText(String.valueOf(record.getConferenceOptionId()));
        }
        

        
        reply.setChildElement(queryResult);
		return reply;
	}
	
	
	public ConferenceVoteBean parseXML(IQ request, Connection con, ConferenceVoteBean voteBean) throws Exception{
		PreparedStatement pstmt = null;
        boolean abortTransaction = false;
        ResultSet rs = null;
//        ConferenceSelectBean selectBean = new ConferenceSelectBean();
//		List<ConferenceSelectBean> selectBeans = new ArrayList<ConferenceSelectBean>();
//		Map<ConferenceSelectBean,List<ConferenceOptionBean>> optionMap = new HashMap<ConferenceSelectBean,List<ConferenceOptionBean>>();
		Element query = request.getChildElement();
		Element operate = query.element("operate");
        String command = operate.attributeValue("name");
        Element roomJid = operate.element("roomjid");
        String roomJidStr = roomJid.getText();
        voteBean.setRoomJid(roomJidStr);
        Element id = operate.element("id");
        String idStr = id.getText();
        if(idStr == null || idStr.trim().length()<1){
        	throw new ConferenceVoteException("投票主题为空");
        }else{
        	try{
        		voteBean.setId(Integer.parseInt(idStr));
        		pstmt = con.prepareStatement("select id from conference_vote where id=?");
        		pstmt.setInt(1, voteBean.getId());
        		rs = pstmt.executeQuery();
        		if(!rs.next()){
        			throw new ConferenceVoteException("投票主题不存在");
        		}
        		DbConnectionManager.fastcloseStmt(pstmt);
        	}catch(Exception e){
        		throw new ConferenceVoteException("投票主题参数错误");
        	}
        }
        String confNo = roomJidStr.split("@")[0];
        if(confNo == null || confNo.trim().length()<1){
        	throw new ConferenceVoteException("会议ID为空");
        }else{
        	pstmt = con.prepareStatement("select id from conference where confno=? and del = 1");
	        pstmt.setString(1, confNo);
	        rs = pstmt.executeQuery();
	        if(rs.next()){
	        	int conferenceId = rs.getInt("id");
	        	voteBean.setConferenceId(conferenceId);
	        }else{
	        	throw new ConferenceVoteException("会议ID不存在");
	        }
	        DbConnectionManager.fastcloseStmt(pstmt);
        	
        }
        Element userId = operate.element("user_id");
		String userIdStr = userId.getText();
		
		if (userIdStr == null || userIdStr.trim().length() < 1) {
			throw new ConferenceVoteException("投票人ID为空");
		} else {
			pstmt = con.prepareStatement("select user_id from sys_user where lcc_account=?");
	        pstmt.setString(1, userIdStr.split("@")[0]);
	        rs = pstmt.executeQuery();
			if (rs.next()) {
				int uid = rs.getInt("user_id");
				voteBean.setUserId(uid);
			} else {
				throw new ConferenceVoteException("投票人ID不存在");
			}
			DbConnectionManager.fastcloseStmt(pstmt);

		}
        return voteBean;
	}

}
