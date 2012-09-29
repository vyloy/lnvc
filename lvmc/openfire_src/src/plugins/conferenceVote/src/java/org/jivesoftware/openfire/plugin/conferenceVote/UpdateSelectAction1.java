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

public class UpdateSelectAction1 extends BaseAction {

private static final Logger Log = LoggerFactory.getLogger(UpdateSelectAction1.class);
	
	@Override
	public List execute(IQ request) {
		final IQ rq = request;
		List replys = execute(rq, new CallBackAction(){

			@Override
			public List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
				ConferenceSelectBean selectBean = message.getConferenceVoteBean().get(0).getSelects().get(0);
				parseBean(con,selectBean);
		        List replys = new ArrayList();
		        StringBuffer sql = new StringBuffer();
				sql.append("update conference_select set id=id");
				if(selectBean.getType()!=null){
					sql.append(",type=").append(selectBean.getType());
				}
				if(selectBean.getSelectTitle()!=null){
					sql.append(",select_title='").append(selectBean.getSelectTitle()).append("'");
				}
				if(selectBean.getSelectRemark()!=null){
					sql.append(",select_remark='").append(selectBean.getSelectRemark()).append("'");
				}
				if(selectBean.getSelectNum()!=null){
					sql.append(",select_num=").append(selectBean.getSelectNum());
				}
				sql.append(" where id=").append(selectBean.getId());
				pstmt = con.prepareStatement(sql.toString());
//	            pstmt.setInt(1, selectBean.getType());
//	            pstmt.setString(2, selectBean.getSelectTitle());
//	            pstmt.setString(3, selectBean.getSelectRemark());
//	            pstmt.setInt(4, selectBean.getSelectNum());
//	            pstmt.setInt(5, selectBean.getId());
	            pstmt.executeUpdate();
	            DbConnectionManager.fastcloseStmt(pstmt);
	            
	            pstmt = con.prepareStatement("delete from conference_option where conference_select_id=?");
	            pstmt.setInt(1, selectBean.getId());
	            pstmt.executeUpdate();
	            DbConnectionManager.fastcloseStmt(pstmt);
	            
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
	            
	            replys.add(getReplyIQ(rq,message));
	            List<String> userAddress = getUserAddress(selectBean.getRoomJid());
	            replys.add(userAddress);
				return replys;
			}
			
		});
		return replys;
	}

	
	
	
	
	public ConferenceSelectBean parseBean(Connection con,ConferenceSelectBean selectBean) throws Exception{
		message.setResultMessage("success");
//		com.lorent.lvmc.bean.ConferenceSelectBean selectBean = message.getConferenceVoteBean().get(0).getSelects().get(0);
		
        
        if(selectBean.getId() == null || selectBean.getId()<1){
        	throw new ConferenceVoteException("表决项ID为空");
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
