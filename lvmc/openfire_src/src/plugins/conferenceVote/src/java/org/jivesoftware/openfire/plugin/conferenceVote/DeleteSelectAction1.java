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
import com.lorent.lvmc.bean.ConferenceVoteBean;

public class DeleteSelectAction1 extends BaseAction {

	private static final Logger Log = LoggerFactory.getLogger(DeleteSelectAction1.class);
	
	@Override
	public List execute(IQ request) {
		final IQ rq = request;
		List replys = execute(rq, new CallBackAction(){

			@Override
			public List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception{
				ConferenceSelectBean selectBean = message.getConferenceVoteBean().get(0).getSelects().get(0);
				parseBean(con,selectBean);
		        List replys = new ArrayList();
		        pstmt = con.prepareStatement("delete from conference_select where id=?");
				pstmt.setInt(1, selectBean.getId());
				
	            pstmt.executeUpdate();
	            
	            DbConnectionManager.fastcloseStmt(pstmt);
	            pstmt = con.prepareStatement("delete from conference_option where conference_select_id=?");
	            pstmt.setInt(1, selectBean.getId());
	            pstmt.executeUpdate();
	            DbConnectionManager.fastcloseStmt(pstmt);
	            

	            
	            replys.add(getReplyIQ(rq, message));
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
        
        return selectBean;
	}
	
}
