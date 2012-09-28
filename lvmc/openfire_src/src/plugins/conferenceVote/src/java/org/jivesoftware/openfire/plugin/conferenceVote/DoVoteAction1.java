package org.jivesoftware.openfire.plugin.conferenceVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.plugin.conferenceVote.util.Constants;
import org.jivesoftware.openfire.plugin.conferenceVote.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

import com.lorent.lvmc.bean.ConferenceOptionBean;
import com.lorent.lvmc.bean.ConferenceSelectBean;
import com.lorent.lvmc.bean.ConferenceVoteBean;

public class DoVoteAction1 extends BaseAction {

	private static final Logger Log = LoggerFactory
			.getLogger(DoVoteAction1.class);

	@Override
	public List execute(IQ request) {
		final IQ rq = request;
		List replys = execute(rq, new CallBackAction(){

			@Override
			public List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
				ConferenceVoteBean voteBean = message.getConferenceVoteBean().get(0);
				parseBean(con,voteBean);
		        List replys = new ArrayList();
		        pstmt = con.prepareStatement("select count(1) ct from conference_vote_record where conference_vote_id=? and user_id=?");
				pstmt.setInt(1, voteBean.getId());
				pstmt.setInt(2, voteBean.getUserId());
				// pstmt.setString(1, voteBean.getTitle());
				// pstmt.setString(2, voteBean.getTitleRemark());
				// pstmt.setInt(3, voteBean.getEffectiveTime());
				// pstmt.setInt(4, voteBean.getConferenceId());
				// pstmt.setDate(5, voteBean.getBeginTime()!=null?new
				// java.sql.Date(voteBean.getBeginTime().getTime()):null);
				// pstmt.setBoolean(6, voteBean.isStart());
				// pstmt.setBoolean(7, voteBean.isClose());
				// pstmt.setInt(8, voteBean.getId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					int ct = rs.getInt("ct");
					if(ct>0){
						throw new ConferenceVoteException("已经投过票");
					}
				}
				DbConnectionManager.fastcloseStmt(pstmt);
				long voteTime = System.currentTimeMillis();
				List<ConferenceSelectBean> selectList = voteBean.getSelects();
				pstmt = con.prepareStatement("insert into conference_vote_record (conference_id,conference_vote_id,conference_select_id,conference_option_id,user_id,vote_time) values (?,?,?,?,?,?)");
				for(ConferenceSelectBean selectBean:selectList){
					List<ConferenceOptionBean> optionList = selectBean.getOptions();
					for(ConferenceOptionBean optionBean:optionList){
						
						pstmt.setInt(1, voteBean.getConferenceId());
						pstmt.setInt(2, voteBean.getId());
						pstmt.setInt(3, selectBean.getId());
						pstmt.setInt(4, optionBean.getId());
						pstmt.setInt(5, voteBean.getUserId());
						pstmt.setTimestamp(6, new java.sql.Timestamp(voteTime));
						pstmt.addBatch();
					}
				}
				pstmt.executeBatch();
				pstmt.clearBatch();
				DbConnectionManager.fastcloseStmt(pstmt);
				replys.add(getReplyIQ(rq, message));
				return replys;
			}
			
		});
		
		return replys;
	}

	

	public ConferenceVoteBean parseBean(Connection con,ConferenceVoteBean voteBean)
			throws Exception {
		PreparedStatement pstmt = null;
		boolean abortTransaction = false;
		ResultSet rs = null;
		message.setResultMessage("success");
//		com.lorent.lvmc.bean.ConferenceVoteBean voteBean = message.getConferenceVoteBean().get(0);

		if(voteBean.getId() == null || voteBean.getId()<1){
        	throw new ConferenceVoteException("会议投票ID为空");
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
	        	throw new ConferenceVoteException("会议ID不存在");
	        }
	        DbConnectionManager.fastcloseStmt(pstmt);
        	
        }
		
		if (voteBean.getUserName() == null || voteBean.getUserName().length() < 1) {
			throw new ConferenceVoteException("投票人ID为空");
		} else {
			pstmt = con.prepareStatement("select user_id from sys_user where lcc_account=?");
	        pstmt.setString(1, voteBean.getUserName().split("@")[0]);
	        rs = pstmt.executeQuery();
			if (rs.next()) {
				int uid = rs.getInt("user_id");
				voteBean.setUserId(uid);
			} else {
				throw new ConferenceVoteException("投票人ID不存在");
			}
			DbConnectionManager.fastcloseStmt(pstmt);

		}
		
        List<ConferenceSelectBean> selectList = voteBean.getSelects();
        if(selectList != null){
        	
        	for(ConferenceSelectBean select:selectList){
        		
        		if(select.getId() == null || select.getId()<1){
        			throw new ConferenceVoteException("表决项ID为空");
        		}
        		
        		List<ConferenceOptionBean> optionList = select.getOptions();
        		if(optionList != null){
//        			List<ConferenceOptionBean> list = new ArrayList<ConferenceOptionBean>();
        			for(ConferenceOptionBean option:optionList){
        				
        				if(option.getId() == null || option.getId()<1){
                			throw new ConferenceVoteException("选项ID为空");
                		}
        			}
        		}
        	}
        }

		return voteBean;
	}

}
