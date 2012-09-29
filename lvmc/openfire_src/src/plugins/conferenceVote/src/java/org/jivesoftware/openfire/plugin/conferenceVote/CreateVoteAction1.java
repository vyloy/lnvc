package org.jivesoftware.openfire.plugin.conferenceVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;

import com.lorent.lvmc.bean.*;

import org.jivesoftware.openfire.plugin.conferenceVote.util.Constants;
import org.jivesoftware.openfire.plugin.conferenceVote.util.DateUtil;
import org.jivesoftware.openfire.plugin.conferenceVote.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class CreateVoteAction1 extends BaseAction {

	private static final Logger Log = LoggerFactory.getLogger(CreateVoteAction1.class);
	public CreateVoteAction1(){
		
	}
	
	public CreateVoteAction1(ConferenceVoteMessage message){
		super(message);
	}
	
	@Override
	public List execute(IQ request) {
		final IQ rq = request;
		List replys = execute(rq, new CallBackAction(){

			@Override
			public List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
				ConferenceVoteBean voteBean = message.getConferenceVoteBean().get(0);
				parseBean(con,voteBean);
		        List replys = new ArrayList();
		        pstmt = con.prepareStatement("insert into conference_vote (title,title_remark,effective_time,conference_id,begin_time,creator,is_start,is_close) values (?,?,?,?,?,?,?,?) returning conference_vote.id");
	            pstmt.setString(1, voteBean.getTitle());
	            pstmt.setString(2, voteBean.getTitleRemark());
	            pstmt.setInt(3, voteBean.getEffectiveTime());
	            pstmt.setInt(4, voteBean.getConferenceId());
	            pstmt.setTimestamp(5, voteBean.getBeginTime()!=null?new java.sql.Timestamp(voteBean.getBeginTime().getTime()):null);
	            pstmt.setInt(6, voteBean.getCreator());
	            pstmt.setBoolean(7, voteBean.isStart());
	            pstmt.setBoolean(8, voteBean.isClose());
	            rs = pstmt.executeQuery();
//	            rs = pstmt.getGeneratedKeys(); //这里返回ResultSet ，rs里就是我们要的主键
	            int voteId = 0;
	            if(rs.next()){
	            	voteId = rs.getInt(1);
	            }
	            DbConnectionManager.fastcloseStmt(pstmt);
	            voteBean.setId(voteId);      
	            List<ConferenceSelectBean> selects = voteBean.getSelects();
	            for(ConferenceSelectBean selectBean:selects){
	            	pstmt = con.prepareStatement("insert into conference_select (conference_vote_id,type,select_title,select_remark,select_num) values (?,?,?,?,?) returning conference_select.id");
	            	pstmt.setInt(1, voteId);
	            	pstmt.setInt(2, selectBean.getType());
	            	pstmt.setString(3, selectBean.getSelectTitle());
	            	pstmt.setString(4, selectBean.getSelectRemark());
	            	pstmt.setInt(5, selectBean.getSelectNum());
	            	rs = pstmt.executeQuery();
//	            	rs = pstmt.getGeneratedKeys();
	            	int selectId = 0;
	            	if(rs.next()){
	            		selectId = rs.getInt(1);
	            	}
	            	DbConnectionManager.fastcloseStmt(pstmt);
	            	selectBean.setId(selectId);
	            	selectBean.setConferenceVoteId(voteId);
	            	List<ConferenceOptionBean> options = selectBean.getOptions();
	            	for(ConferenceOptionBean optionBean:options){
	            		pstmt = con.prepareStatement("insert into conference_option (conference_select_id,option_name) values (?,?) returning conference_option.id");
	            		pstmt.setInt(1, selectId);
	            		pstmt.setString(2, optionBean.getOptionName());
//	            		pstmt.executeUpdate();
	            		rs = pstmt.executeQuery();
	            		int optionId = 0;
	            		if(rs.next()){
	            			optionId = rs.getInt(1);
	            		}
	            		DbConnectionManager.fastcloseStmt(pstmt);
	            		optionBean.setId(optionId);
	            		optionBean.setConferenceSelectId(selectId);
	            	}
	            }
	            
	            replys.add(getReplyIQ(rq, message));
	            List<String> userAddress = getUserAddress(voteBean.getRoomJid());
	            replys.add(userAddress);
				return replys;
			}
			
		});
		return replys;
	}

	
	
	
	public ConferenceVoteBean parseBean(Connection con,ConferenceVoteBean voteBean) throws Exception{
		PreparedStatement pstmt = null;
//        boolean abortTransaction = false;
        ResultSet rs = null;
        message.setResultMessage("success");
//        ConferenceVoteBean voteBean = message.getConferenceVoteBean().get(0);
        if(voteBean == null){
        	throw new ConferenceVoteException("没有任何投票信息");
        }
		String roomJidStr = voteBean.getRoomJid();
		if(roomJidStr==null || roomJidStr.length()<1){
			throw new ConferenceVoteException("会议JID为空");
		}
        String confNo = roomJidStr.split("@")[0];
        if(confNo == null || confNo.trim().length()<1){
        	throw new ConferenceVoteException("传输会议信息为空");
        }else{
        	pstmt = con.prepareStatement("select id from conference where confno=?");
	        pstmt.setString(1, confNo);
	        rs = pstmt.executeQuery();
	        if(rs.next()){
	        	int conferenceId = rs.getInt("id");
	        	voteBean.setConferenceId(conferenceId);
	        }else{
	        	throw new ConferenceVoteException("该会议信息不存在");
	        }
	        DbConnectionManager.fastcloseStmt(pstmt);
        	
        }
        
        
        String creatorStr = voteBean.getCreatorStr();
        int userId = 0;
        if(creatorStr == null || creatorStr.trim().length()<1){
        	throw new ConferenceVoteException("创建者的信息为空");
        }else{
        	pstmt = con.prepareStatement("select user_id from sys_user where lcc_account=?");
	        pstmt.setString(1, creatorStr.split("@")[0]);
	        rs = pstmt.executeQuery();
	        if(rs.next()){
	        	userId = rs.getInt("user_id");
	        	voteBean.setCreator(userId);
	        }else{
	        	throw new ConferenceVoteException("创建者不存在");
	        }
	        DbConnectionManager.fastcloseStmt(pstmt);
        }
        
        return voteBean;
	}

}
