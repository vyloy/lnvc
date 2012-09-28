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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

import com.lorent.lvmc.bean.ConferenceOptionBean;
import com.lorent.lvmc.bean.ConferenceSelectBean;

public class InsertSelectAction1 extends BaseAction{
	

	private static final Logger Log = LoggerFactory.getLogger(InsertSelectAction1.class);
	
	@Override
	public List execute(IQ request) {
		final IQ rq = request;
		List replys = execute(rq, new CallBackAction(){

			@Override
			public List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
				List<Object> replys = new ArrayList();
				ConferenceSelectBean selectBean = message.getConferenceVoteBean().get(0).getSelects().get(0);
				parseBean(con,selectBean);
				pstmt = con.prepareStatement("insert into conference_select (conference_vote_id,type,select_title,select_remark,select_num) values (?,?,?,?,?) returning conference_select.id");
				pstmt.setInt(1, selectBean.getConferenceVoteId());
				pstmt.setInt(2, selectBean.getType());
	            pstmt.setString(3, selectBean.getSelectTitle());
	            pstmt.setString(4, selectBean.getSelectRemark());
	            pstmt.setInt(5, selectBean.getSelectNum());
//	            pstmt.executeUpdate();
	            
	            rs = pstmt.executeQuery();
	        	int selectId = 0;
	        	if(rs.next()){
	        		selectId = rs.getInt(1);
	        	}
	            DbConnectionManager.fastcloseStmt(pstmt);
	            selectBean.setId(selectId);
//	            pstmt = con.prepareStatement("delete from conference_option where conference_select_id=?");
//	            pstmt.setInt(1, selectBean.getId());
//	            pstmt.executeUpdate();
//	            DbConnectionManager.fastcloseStmt(pstmt);
	            
	            List<ConferenceOptionBean> options = selectBean.getOptions();
	        	for(ConferenceOptionBean optionBean:options){
	        		pstmt = con.prepareStatement("insert into conference_option (conference_select_id,option_name) values (?,?) returning conference_option.id");
	        		pstmt.setInt(1, selectBean.getId());
	        		pstmt.setString(2, optionBean.getOptionName());
//	        		pstmt.executeUpdate();
	        		rs = pstmt.executeQuery();
	        		int optionId = 0;
	        		if(rs.next()){
	        			optionId = rs.getInt(1);
	        		}
	        		DbConnectionManager.fastcloseStmt(pstmt);
	        		optionBean.setId(optionId);
	        		optionBean.setConferenceSelectId(selectBean.getId());
	        	}
	            
	            replys.add(InsertSelectAction1.this.getReplyIQ(rq, message));
	            List<String> userAddress = InsertSelectAction1.this.getUserAddress(selectBean.getRoomJid());
	            replys.add(userAddress);
				return replys;
			}
			
		});
		
		
		return replys;
	}
	
	
	public ConferenceSelectBean parseBean(Connection con,ConferenceSelectBean selectBean) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		message.setResultMessage("success");
//		com.lorent.lvmc.bean.ConferenceSelectBean selectBean = message.getConferenceVoteBean().get(0).getSelects().get(0);
		if(selectBean.getConferenceVoteId()==null || selectBean.getConferenceVoteId() < 1){
			throw new Exception("投票主题ID为空");
		}else{
			pstmt = con.prepareStatement("select id from conference_vote where id=?");
			pstmt.setInt(1, selectBean.getConferenceVoteId());
			rs = pstmt.executeQuery();
			if(!rs.next()){
				throw new Exception("投票主题ID不存在");
			}
			DbConnectionManager.fastcloseStmt(pstmt);
		}
		if(selectBean.getType() == 0){
			selectBean.setType(null);
		}
		
		if(selectBean.getSelectNum() == 0 ){
			selectBean.setSelectNum(null);
		}
        return selectBean;
	}

}
