package org.jivesoftware.openfire.plugin.conferenceVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.plugin.conferenceVote.util.Constants;
import org.jivesoftware.openfire.plugin.conferenceVote.util.DateUtil;
import org.jivesoftware.openfire.plugin.conferenceVote.util.NumberUtil;
import org.xmpp.packet.IQ;

import com.lorent.lvmc.bean.*;

public class LoadVoteAction1 extends BaseAction {

	@Override
	public List execute(IQ request) {
		final IQ rq = request;
		List replys = execute(rq, new CallBackAction(){

			@Override
			public List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
				ConferenceVoteBean voteBean = message.getConferenceVoteBean().get(0);
				parseBean(con,voteBean);
		        List replys = new ArrayList();
		        List<ConferenceVoteBean> voteList = new ArrayList<ConferenceVoteBean>();
				Map<Integer,ConferenceVoteBean> voteMap = new HashMap<Integer,ConferenceVoteBean>();
				Map<Integer,ConferenceSelectBean> selectMap = new HashMap<Integer,ConferenceSelectBean>();
				pstmt = con.prepareStatement("select cv.begin_time,cv.id vid,cv.title_remark,cv.title,cv.effective_time," +
						"cv.is_close,cv.is_start,cs.id sid,cs.type,cs.select_title,cs.select_remark,cs.select_num," +
						"co.id oid,co.option_name,su.lcc_account from conference_vote cv,conference_select cs," +
						"conference_option co,sys_user su where cv.conference_id=? "+ 
				"and cv.id=cs.conference_vote_id and cs.id=co.conference_select_id and su.user_id=cv.creator");
	            pstmt.setInt(1, voteBean.getConferenceId());
	            rs = pstmt.executeQuery();
	            while(rs.next()){
	            	Integer vid = new Integer(rs.getInt("vid"));
	            	ConferenceVoteBean vb = null;
	            	ConferenceSelectBean sb = null;
	            	if(!voteMap.containsKey(vid)){
	            		vb = new ConferenceVoteBean();
	            		vb.setId(vid);
	            		vb.setTitle(rs.getString("title"));
	            		vb.setTitleRemark(rs.getString("title_remark"));
	            		vb.setEffectiveTime(rs.getInt("effective_time"));
	            		vb.setConferenceId(voteBean.getConferenceId());
	            		vb.setCreatorStr(rs.getString("lcc_account"));
	            		vb.setClose(rs.getBoolean("is_close"));
	            		vb.setStart(rs.getBoolean("is_start"));
	            		vb.setBeginTime(rs.getTimestamp("begin_time"));
	            		voteMap.put(vid, vb);
	            	}else{
	            		vb = voteMap.get(vid);
	            	}
	            	Integer sid = new Integer(rs.getInt("sid"));
	            	if(!selectMap.containsKey(sid)){
	            		sb = new ConferenceSelectBean();
	            		sb.setId(sid);
	            		sb.setConferenceVoteId(vid);
	            		sb.setType(rs.getInt("type"));
	            		sb.setSelectTitle(rs.getString("select_title"));
	            		sb.setSelectRemark(rs.getString("select_remark"));
	            		sb.setSelectNum(rs.getInt("select_num"));
	            		selectMap.put(sid, sb);
	            		vb.getSelects().add(sb);
	            	}else{
	            		sb = selectMap.get(sid);
	            	}
	            	Integer oid = new Integer(rs.getInt("oid"));
	            	ConferenceOptionBean ob = new ConferenceOptionBean();
	            	ob.setId(oid);
	            	ob.setConferenceSelectId(sid);
	            	ob.setOptionName(rs.getString("option_name"));
	            	sb.getOptions().add(ob);
	            }
	            DbConnectionManager.fastcloseStmt(pstmt);
	            Iterator iter = voteMap.entrySet().iterator(); 
	            while (iter.hasNext()) { 
	                Map.Entry entry = (Map.Entry) iter.next(); 
//	                Object key = entry.getKey(); 
	                ConferenceVoteBean val = (ConferenceVoteBean)entry.getValue();
	                voteList.add(val);
	            } 
	            message.setConferenceVoteBean(voteList);
	            replys.add(LoadVoteAction1.this.getReplyIQ(rq, message));
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
        return voteBean;
	}

}
