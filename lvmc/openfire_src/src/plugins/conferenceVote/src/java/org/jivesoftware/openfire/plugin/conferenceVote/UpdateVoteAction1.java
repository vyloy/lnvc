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
import com.lorent.lvmc.bean.ConferenceVoteMessage;

public class UpdateVoteAction1 extends BaseAction {
	
	public UpdateVoteAction1(){}
	
	public UpdateVoteAction1(ConferenceVoteMessage message){
		super(message);
	}

	private static final Logger Log = LoggerFactory.getLogger(UpdateVoteAction1.class);
	
	@Override
	public List execute(IQ request) {
		final IQ rq = request;
		List replys = execute(rq, new CallBackAction(){

			@Override
			public List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
				ConferenceVoteBean voteBean = message.getConferenceVoteBean().get(0);
				parseBean(con,voteBean);
		        List replys = new ArrayList();
		        StringBuffer sql = new StringBuffer();
				sql.append("update conference_vote set id=id");
				if(voteBean.getTitle()!=null && voteBean.getTitle().length()>0){
					sql.append(",title='").append(voteBean.getTitle()).append("'");
				}
				if(voteBean.getTitleRemark()!=null && voteBean.getTitleRemark().length()>0){
					sql.append(",title_remark='").append(voteBean.getTitleRemark()).append("'");
				}
				if(voteBean.getEffectiveTime()>0){
					sql.append(",effective_time=").append(voteBean.getEffectiveTime().intValue());
				}
				if(voteBean.getConferenceId()>0){
					sql.append(",conference_id=").append(voteBean.getConferenceId());
				}
				if(voteBean.getBeginTime()!=null){
					sql.append(",begin_time=timestamp '").append(DateUtil.getDateStr(voteBean.getBeginTime(), "yyyy-MM-dd HH:mm:ss")).append("'");
				}
				if(voteBean.isStart() != null){
					sql.append(",is_start=").append(voteBean.isStart());
				}
				if(voteBean.isClose() != null){
					sql.append(",is_close=").append(voteBean.isClose());
				}
				sql.append(" where id=").append(voteBean.getId());
				pstmt = con.prepareStatement(sql.toString());
//	            pstmt.setString(1, voteBean.getTitle());
//	            pstmt.setString(2, voteBean.getTitleRemark());
//	            pstmt.setInt(3, voteBean.getEffectiveTime());
//	            pstmt.setInt(4, voteBean.getConferenceId());
//	            pstmt.setDate(5, voteBean.getBeginTime()!=null?new java.sql.Date(voteBean.getBeginTime().getTime()):null);
//	            pstmt.setBoolean(6, voteBean.isStart());
//	            pstmt.setBoolean(7, voteBean.isClose());
//	            pstmt.setInt(8, voteBean.getId());
	            pstmt.executeUpdate();
	            
	            DbConnectionManager.fastcloseStmt(pstmt);
	            
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
        ResultSet rs = null;
        message.setResultMessage("success");
//		com.lorent.lvmc.bean.ConferenceVoteBean voteBean = message.getConferenceVoteBean().get(0);
		
		if(voteBean == null){
        	throw new ConferenceVoteException("没有任何投票信息");
        }
//		List<ConferenceSelectBean> selectBeans = new ArrayList<ConferenceSelectBean>();
//		Map<ConferenceSelectBean,List<ConferenceOptionBean>> optionMap = new HashMap<ConferenceSelectBean,List<ConferenceOptionBean>>();
		
//        String idStr = voteBean.getId();
        if(voteBean.getId() == null || voteBean.getId()<1){
        	throw new ConferenceVoteException("会议投票ID为空");
        }
        String roomJidStr = voteBean.getRoomJid();
		if(roomJidStr==null || roomJidStr.length()<1){
			throw new ConferenceVoteException("会议JID为空");
		}
        String confNo = roomJidStr.split("@")[0];
        if(confNo == null || confNo.trim().length()<1){
        	throw new ConferenceVoteException("会议ID为空");
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
        
        return voteBean;
	}

}
