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

public class UpdateVoteAction extends BaseAction {

	private static final Logger Log = LoggerFactory.getLogger(UpdateVoteAction.class);
	
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
//            pstmt.setString(1, voteBean.getTitle());
//            pstmt.setString(2, voteBean.getTitleRemark());
//            pstmt.setInt(3, voteBean.getEffectiveTime());
//            pstmt.setInt(4, voteBean.getConferenceId());
//            pstmt.setDate(5, voteBean.getBeginTime()!=null?new java.sql.Date(voteBean.getBeginTime().getTime()):null);
//            pstmt.setBoolean(6, voteBean.isStart());
//            pstmt.setBoolean(7, voteBean.isClose());
//            pstmt.setInt(8, voteBean.getId());
            pstmt.executeUpdate();
            
            DbConnectionManager.fastcloseStmt(pstmt);
            
            replys.add(this.getSuccessReplyIQ(request, voteBean));
            List<String> userAddress = this.getUserAddress(voteBean.getRoomJid());
            replys.add(userAddress);
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
        operate.addAttribute("name", ActionFactory.UPDATE_VOTE);
        operate.addElement("result").addText(Constants.FAIL);
        operate.addElement("roomjid").addText(voteBean.getRoomJid());
        operate.addElement("message").addText(message);
        reply.setChildElement(queryResult);
		return reply;
	}
	
	public IQ getSuccessReplyIQ(IQ request,ConferenceVoteBean voteBean){
		IQ reply = IQ.createResultIQ(request);
//		reply.setTo(userJid);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addAttribute("name", ActionFactory.UPDATE_VOTE);
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addElement("roomjid").addText(voteBean.getRoomJid());
        operate.addElement("id").addText(String.valueOf(voteBean.getId()));
        operate.addElement("title").addText(voteBean.getTitle());
        operate.addElement("title_remark").addText(voteBean.getTitleRemark());
        operate.addElement("effective_time").addText(String.valueOf(voteBean.getEffectiveTime()));
        operate.addElement("conference_id").addText(String.valueOf(voteBean.getConferenceId()));
        operate.addElement("begin_time").addText(DateUtil.getDateStr(voteBean.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
        operate.addElement("is_start").addText(String.valueOf(voteBean.isStart()));
        operate.addElement("is_close").addText(String.valueOf(voteBean.isClose()));
        //        operate.addElement("creator").addText(voteBean.getCreatorStr());
//        Element selects = operate.addElement("selects");
//        for(ConferenceSelectBean selectBean:voteBean.getSelects()){
//        	Element select = selects.addElement("select");
//        	select.addElement("id").addText(String.valueOf(selectBean.getId()));
//        	select.addElement("type").addText(String.valueOf(selectBean.getType()));
//        	select.addElement("select_title").addText(selectBean.getSelectTitle());
//        	select.addElement("select_remark").addText(selectBean.getSelectRemark());
//        	select.addElement("select_num").addText(String.valueOf(selectBean.getSelectNum()));
//        	Element options = select.addElement("options");
//        	for(ConferenceOptionBean optionBean:selectBean.getOptions()){
//        		Element option = options.addElement("option");
//        		option.addElement("id").addText(String.valueOf(optionBean.getId()));
//        		option.addElement("option_name").addText(optionBean.getOptionName());
//        	}
//        }
        
        reply.setChildElement(queryResult);
		return reply;
	}
	
	
	public ConferenceVoteBean parseXML(IQ request, Connection con,ConferenceVoteBean voteBean) throws Exception{
		PreparedStatement pstmt = null;
        boolean abortTransaction = false;
        ResultSet rs = null;
//		ConferenceVoteBean voteBean = new ConferenceVoteBean();
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
        	throw new ConferenceVoteException("会议投票ID为空");
        }else{
        	try{
        		voteBean.setId(Integer.parseInt(idStr));
        	}catch(Exception e){
        		throw new ConferenceVoteException("会议投票ID参数错误");
        	}
        }
        Element title = operate.element("title");
        String titleStr = title.getText();
        voteBean.setTitle(titleStr);
        Element titleRemark = operate.element("title_remark");
        String titleRemarkStr = titleRemark.getText();
        voteBean.setTitleRemark(titleRemarkStr);
        Element effectiveTime = operate.element("effective_time");
        String effectiveTimeStr = effectiveTime.getText();
        if(effectiveTimeStr == null || effectiveTimeStr.trim().length()<1){
        	voteBean.setEffectiveTime(0);
        }else{
        	try{
        		voteBean.setEffectiveTime(Integer.parseInt(effectiveTimeStr));
        	}catch(Exception e1){
        		throw new ConferenceVoteException("会议投票有效时间参数错误");
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
        Element beginTime = operate.element("begin_time");
        String beginTimeStr = beginTime.getText();
        if(beginTimeStr == null || beginTimeStr.trim().length()<1){
        	voteBean.setBeginTime(null);
        }else{
        	voteBean.setBeginTime(DateUtil.getDate(beginTimeStr, "yyyy-MM-dd HH:mm:ss"));
        }
//        Element creator = operate.element("creator");
//        String creatorStr = creator.getText();
//        voteBean.setCreatorStr(creatorStr);
//        int userId = 0;
//        if(creatorStr == null || creatorStr.trim().length()<1){
//        	throw new Exception("创建者的参数为空");
//        }else{
//        	pstmt = con.prepareStatement("select user_id from sys_user where lcc_account=?");
//	        pstmt.setString(1, creatorStr);
//	        rs = pstmt.executeQuery();
//	        if(rs.next()){
//	        	userId = rs.getInt("user_id");
//	        	voteBean.setCreator(userId);
//	        }else{
//	        	throw new Exception("创建者不存在");
//	        }
//	        DbConnectionManager.fastcloseStmt(pstmt);
//        }
        Element start = operate.element("is_start");
        if("true".equals(start.getText())){
        	voteBean.setStart(true);
        }else if("false".equals(start.getText())){
        	voteBean.setStart(false);
        }else{
        	voteBean.setStart(null);
        }
        Element close = operate.element("is_close");
        if("true".equals(close.getText())){
        	voteBean.setClose(true);
        }else if("false".equals(close.getText())){
        	voteBean.setClose(false);
        }else{
        	voteBean.setClose(null);
        }

        return voteBean;
	}

}
