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
import org.jivesoftware.openfire.plugin.conferenceVote.model.ConferenceOptionBean;
import org.jivesoftware.openfire.plugin.conferenceVote.model.ConferenceSelectBean;
import org.jivesoftware.openfire.plugin.conferenceVote.model.ConferenceVoteBean;
import org.jivesoftware.openfire.plugin.conferenceVote.util.Constants;
import org.jivesoftware.openfire.plugin.conferenceVote.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class DoVoteAction extends BaseAction {

	private static final Logger Log = LoggerFactory
			.getLogger(DoVoteAction.class);

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
			parseXML(request, con, voteBean);
			
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
			replys.add(this.getSuccessReplyIQ(request, voteBean));
//			List<String> userAddress = this.getUserAddress(voteBean
//					.getRoomJid());
//			replys.add(userAddress);
		} catch (Exception e) {
			e.printStackTrace();
			abortTransaction = true;
			String message = null;
            if(e instanceof ConferenceVoteException){
            	message = e.getMessage();
            }else{
            	message = "操作出错";
            }
			replys.add(this.getFailReplyIQ(request, voteBean, message));
		} finally {
			DbConnectionManager.closeStatement(pstmt);
			DbConnectionManager.closeTransactionConnection(con,
					abortTransaction);
		}

		return replys;
	}

	public IQ getFailReplyIQ(IQ request, ConferenceVoteBean voteBean, String message) {
		IQ reply = IQ.createResultIQ(request);
		Element queryResult = DocumentHelper.createElement(QName.get(
				Constants.PREFIX_NAME, Constants.SPACE_NAME));
		Element operate = queryResult.addElement("operate");
		operate.addAttribute("name", ActionFactory.DOVOTE);
		operate.addElement("result").addText(Constants.FAIL);
		operate.addElement("roomjid").addText(voteBean.getRoomJid());
		operate.addElement("message").addText(message);
		reply.setChildElement(queryResult);
		return reply;
	}

	public IQ getSuccessReplyIQ(IQ request, ConferenceVoteBean voteBean) {
		IQ reply = IQ.createResultIQ(request);
		// reply.setTo(userJid);
		Element queryResult = DocumentHelper.createElement(QName.get(
				Constants.PREFIX_NAME, Constants.SPACE_NAME));
		Element operate = queryResult.addElement("operate");
		operate.addAttribute("name", ActionFactory.DOVOTE);
		operate.addElement("result").addText(Constants.SUCCESS);
		operate.addElement("roomjid").addText(voteBean.getRoomJid());
		

		reply.setChildElement(queryResult);
		return reply;
	}

	public ConferenceVoteBean parseXML(IQ request, Connection con,ConferenceVoteBean voteBean)
			throws Exception {
		PreparedStatement pstmt = null;
		boolean abortTransaction = false;
		ResultSet rs = null;
//		ConferenceVoteBean voteBean = new ConferenceVoteBean();
		// List<ConferenceSelectBean> selectBeans = new
		// ArrayList<ConferenceSelectBean>();
		// Map<ConferenceSelectBean,List<ConferenceOptionBean>> optionMap = new
		// HashMap<ConferenceSelectBean,List<ConferenceOptionBean>>();
		Element query = request.getChildElement();
		Element operate = query.element("operate");
		String command = operate.attributeValue("name");
		Element roomJid = operate.element("roomjid");
		String roomJidStr = roomJid.getText();
		voteBean.setRoomJid(roomJidStr);
		Element id = operate.element("id");
		String idStr = id.getText();
		if (idStr == null || idStr.trim().length() < 1) {
			throw new ConferenceVoteException("会议投票ID为空");
		} else {
			try {
				voteBean.setId(Integer.parseInt(idStr));
			} catch (Exception e) {
				throw new ConferenceVoteException("会议投票ID参数错误");
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
		Element selects = operate.element("selects");
        List<Element> selectList = selects.elements("select");
        if(selectList != null){
        	
        	for(Element select:selectList){
        		ConferenceSelectBean selectBean = new ConferenceSelectBean();
        		Element selectId = select.element("id");
        		String selectIdStr = selectId.getText();
//        		System.out.println("selectIdStr========="+selectIdStr);
        		if(selectIdStr == null || selectIdStr.trim().length()<1){
        			throw new ConferenceVoteException("表决项ID为空");
        		}else{
        			try{
        				selectBean.setId(Integer.parseInt(selectIdStr));
        			}catch(Exception e){
        				throw new ConferenceVoteException("表决项ID不为整数");
        			}
        		}
        		voteBean.getSelects().add(selectBean);
        		Element options = select.element("options");
        		List<Element> optionList = options.elements("option");
        		if(optionList != null){
//        			List<ConferenceOptionBean> list = new ArrayList<ConferenceOptionBean>();
        			for(Element option:optionList){
        				ConferenceOptionBean optionBean = new ConferenceOptionBean();
        				Element optionId = option.element("id");
        				String optionIdStr = optionId.getText();
//        				System.out.println("optionIdStr========="+optionIdStr);
        				if(optionIdStr == null || optionIdStr.trim().length()<1){
                			throw new ConferenceVoteException("选项ID为空");
                		}else{
                			try{
                				optionBean.setId(Integer.parseInt(optionIdStr));
                			}catch(Exception e){
                				throw new ConferenceVoteException("选项ID不为整数");
                			}
                		}
        				selectBean.getOptions().add(optionBean);
        			}
        		}
        	}
        }

		return voteBean;
	}

}
