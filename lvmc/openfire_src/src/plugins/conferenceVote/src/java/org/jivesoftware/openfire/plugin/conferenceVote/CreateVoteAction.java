package org.jivesoftware.openfire.plugin.conferenceVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class CreateVoteAction extends BaseAction {

	private static final Logger Log = LoggerFactory.getLogger(CreateVoteAction.class);
	
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
			//con.setAutoCommit(false);
			parseXML(request,con,voteBean);
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
//            rs = pstmt.getGeneratedKeys(); //这里返回ResultSet ，rs里就是我们要的主键
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
//            	rs = pstmt.getGeneratedKeys();
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
//            		pstmt.executeUpdate();
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
            
            replys.add(this.getSuccessReplyIQ(request, voteBean));
            List<String> userAddress = this.getUserAddress(voteBean.getRoomJid());
            replys.add(userAddress);
            
        }
        catch (Exception e) {
            e.printStackTrace();
            abortTransaction = true;
//            try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
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
        operate.addAttribute("name", ActionFactory.CREATE_VOTE);
        operate.addElement("result").addText(Constants.FAIL);
        operate.addElement("roomjid").addText(voteBean.getRoomJid());
        operate.addElement("message").addText(message);
        reply.setChildElement(queryResult);
		return reply;
	}
	
	public IQ getSuccessReplyIQ(IQ request,ConferenceVoteBean voteBean){
		IQ reply = IQ.createResultIQ(request);
		//reply.setTo(userJid);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addAttribute("name", ActionFactory.CREATE_VOTE);
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addElement("roomjid").addText(voteBean.getRoomJid());
        operate.addElement("id").addText(String.valueOf(voteBean.getId()));
        operate.addElement("title").addText(voteBean.getTitle());
        operate.addElement("title_remark").addText(voteBean.getTitleRemark());
        operate.addElement("effective_time").addText(String.valueOf(voteBean.getEffectiveTime()));
        operate.addElement("conference_id").addText(String.valueOf(voteBean.getConferenceId()));
        operate.addElement("begin_time").addText(DateUtil.getDateStr(voteBean.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
        operate.addElement("creator").addText(voteBean.getCreatorStr());
        Element selects = operate.addElement("selects");
        for(ConferenceSelectBean selectBean:voteBean.getSelects()){
        	Element select = selects.addElement("select");
        	select.addElement("id").addText(String.valueOf(selectBean.getId()));
        	select.addElement("type").addText(String.valueOf(selectBean.getType()));
        	select.addElement("select_title").addText(selectBean.getSelectTitle());
        	select.addElement("select_remark").addText(selectBean.getSelectRemark());
        	select.addElement("select_num").addText(String.valueOf(selectBean.getSelectNum()));
        	Element options = select.addElement("options");
        	for(ConferenceOptionBean optionBean:selectBean.getOptions()){
        		Element option = options.addElement("option");
        		option.addElement("id").addText(String.valueOf(optionBean.getId()));
        		option.addElement("option_name").addText(optionBean.getOptionName());
        	}
        }
        
        reply.setChildElement(queryResult);
		return reply;
	}
	
	
	public ConferenceVoteBean parseXML(IQ request, Connection con,ConferenceVoteBean voteBean) throws Exception{
		PreparedStatement pstmt = null;
//        boolean abortTransaction = false;
        ResultSet rs = null;
//		ConferenceVoteBean voteBean = new ConferenceVoteBean();
//		List<ConferenceSelectBean> selectBeans = new ArrayList<ConferenceSelectBean>();
//		Map<ConferenceSelectBean,List<ConferenceOptionBean>> optionMap = new HashMap<ConferenceSelectBean,List<ConferenceOptionBean>>();
		Element query = request.getChildElement();
		Element operate = query.element("operate");
//        String command = operate.attributeValue("name");
        Element roomJid = operate.element("roomjid");
        String roomJidStr = roomJid.getText();
        voteBean.setRoomJid(roomJidStr);
//        System.out.println("roomjid=========" + voteBean.getRoomJid());
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
        		voteBean.setEffectiveTime(0);
        	}
        	
        }
        
//        Element conferenceId = operate.element("conference_id");
        String confNo = roomJidStr.split("@")[0];
        if(confNo == null || confNo.trim().length()<1){
        	throw new ConferenceVoteException("传输会议信息为空");
        }else{
        	pstmt = con.prepareStatement("select id from conference where confno=? and del = 1");
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
        Element beginTime = operate.element("begin_time");
        String beginTimeStr = beginTime.getText();
        if(beginTimeStr == null || beginTimeStr.trim().length()<1){
        	voteBean.setBeginTime(null);
        }else{
        	voteBean.setBeginTime(DateUtil.getDate(beginTimeStr, "yyyy-MM-dd HH:mm:ss"));
        }
        Element creator = operate.element("creator");
        String creatorStr = creator.getText();
        voteBean.setCreatorStr(creatorStr);
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
        voteBean.setClose(false);
        voteBean.setStart(false);
        Element selects = operate.element("selects");
        List<Element> selectList = selects.elements("select");
        if(selectList != null){
        	
        	for(Element select:selectList){
        		ConferenceSelectBean selectBean = new ConferenceSelectBean();
        		Element type = select.element("type");
        		String typeStr = type.getText();
        		if(typeStr == null || typeStr.trim().length()<1){
        			selectBean.setType(0);
        		}else{
        			try{
        				selectBean.setType(Integer.parseInt(typeStr));
        			}catch(Exception e){
        				selectBean.setType(0);
        			}
        		}
        		Element selectTitle = select.element("select_title");
        		String selectTitleStr = selectTitle.getText();
        		selectBean.setSelectTitle(selectTitleStr);
        		Element selectRemark = select.element("select_remark");
        		String selectRemarkStr = selectRemark.getText();
        		selectBean.setSelectRemark(selectRemarkStr);
        		Element selectNum = select.element("select_num");
        		String selectNumStr = selectNum.getText();
        		if(selectNumStr == null || selectNumStr.trim().length()<1){
        			selectBean.setSelectNum(0);
        		}else{
        			try{
        				selectBean.setSelectNum(Integer.parseInt(selectNumStr));
        			}catch(Exception ex){
        				selectBean.setSelectNum(0);
        			}
        		}
        		voteBean.getSelects().add(selectBean);
        		Element options = select.element("options");
        		List<Element> optionList = options.elements("option");
        		if(optionList != null){
//        			List<ConferenceOptionBean> list = new ArrayList<ConferenceOptionBean>();
        			for(Element option:optionList){
        				ConferenceOptionBean optionBean = new ConferenceOptionBean();
        				Element optionName = option.element("option_name");
        				String optionNameStr = optionName.getText();
        				optionBean.setOptionName(optionNameStr);
        				selectBean.getOptions().add(optionBean);
        			}
        		}
        	}
        }
        return voteBean;
	}

}
