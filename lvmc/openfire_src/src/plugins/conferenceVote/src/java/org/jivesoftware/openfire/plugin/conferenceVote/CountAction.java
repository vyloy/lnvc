package org.jivesoftware.openfire.plugin.conferenceVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.jivesoftware.openfire.plugin.conferenceVote.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;
import java.util.Map;
import java.util.HashMap;

public class CountAction extends BaseAction {

	private static final Logger Log = LoggerFactory
			.getLogger(CountAction.class);
	private String roomJidStr;
	private boolean flag = false;
	private String operateName = ActionFactory.COUNT;
	
	
	
	public String getOperateName() {
		return operateName;
	}


	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public String getRoomJidStr() {
		return roomJidStr;
	}


	public void setRoomJidStr(String roomJidStr) {
		this.roomJidStr = roomJidStr;
	}


	@Override
	public List execute(IQ request) {
		Connection con = null;
        PreparedStatement pstmt = null;
        boolean abortTransaction = false;
        ResultSet rs = null;
        ConferenceVoteBean voteBean = new ConferenceVoteBean();
        List replys = new ArrayList();
        int total_num = 0;
		try {
			con = DbConnectionManager.getTransactionConnection();
			parseXML(request,con,voteBean);
			Map<Integer,ConferenceSelectBean> selectMap = new HashMap<Integer,ConferenceSelectBean>();
			pstmt = con
					.prepareStatement("select ss.ct select_total_num,ss.conference_select_id,ro.conference_option_id,ro.ct option_total_num from "
							+ " ( select count(1) ct,conference_select_id from conference_vote_record "
							+ "where conference_vote_id=? group by conference_select_id "
							+ ") ss,"
							+ "(select oc.ct,oc.conference_option_id,r.conference_select_id from ("
							+ "select count(1) ct,conference_option_id from conference_vote_record "
							+ " where conference_vote_id=? group by conference_option_id "
							+ ") oc,"
							+ "(select co.conference_select_id,co.id conference_option_id from conference_option co,conference_select cs "
							+ " where cs.conference_vote_id=? and co.conference_select_id=cs.id"
							+ " ) r where oc.conference_option_id=r.conference_option_id"
							+ ") ro where ss.conference_select_id=ro.conference_select_id");
			pstmt.setInt(1, voteBean.getId());
			pstmt.setInt(2, voteBean.getId());
			pstmt.setInt(3, voteBean.getId());
//            pstmt.setString(1, voteBean.getTitle());
//            pstmt.setString(2, voteBean.getTitleRemark());
//            pstmt.setInt(3, voteBean.getEffectiveTime());
//            pstmt.setInt(4, voteBean.getConferenceId());
//            pstmt.setDate(5, voteBean.getBeginTime()!=null?new java.sql.Date(voteBean.getBeginTime().getTime()):null);
//            pstmt.setBoolean(6, voteBean.isStart());
//            pstmt.setBoolean(7, voteBean.isClose());
//            pstmt.setInt(8, voteBean.getId());
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            	Integer selectId = new Integer(rs.getInt("conference_select_id"));
            	ConferenceSelectBean selectBean = null;
            	int selectTotalNum = rs.getInt("select_total_num");
            	int optionTotalNum = rs.getInt("option_total_num");
            	if(selectMap.containsKey(selectId)){
            		selectBean = selectMap.get(selectId);
            		
            	}else{
            		selectBean = new ConferenceSelectBean();
                	selectBean.setId(selectId);
                	selectBean.setTotalNum(selectTotalNum);
                	selectMap.put(new Integer(selectId), selectBean);
            	}
            	ConferenceOptionBean optionBean = new ConferenceOptionBean();
            	optionBean.setId(rs.getInt("conference_option_id"));
            	optionBean.setNum(optionTotalNum);
            	double percent = NumberUtil.diviReturnDouble(optionTotalNum, selectTotalNum);
            	
            	optionBean.setPercent(percent);
            	selectBean.getOptions().add(optionBean);
            }
            DbConnectionManager.fastcloseStmt(pstmt);
            
            Iterator iter = selectMap.entrySet().iterator(); 
            while (iter.hasNext()) { 
                Map.Entry entry = (Map.Entry) iter.next(); 
//                Object key = entry.getKey(); 
                ConferenceSelectBean val = (ConferenceSelectBean)entry.getValue();
                voteBean.getSelects().add(val);
            } 

            
            replys.add(this.getSuccessReplyIQ(request, voteBean));
//            List<String> userAddress = this.getUserAddress(voteBean.getRoomJid());
//            replys.add(userAddress);
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
		this.setFlag(true);
		return replys;
	}

	
	public IQ getFailReplyIQ(IQ request,ConferenceVoteBean voteBean,String message){
		IQ reply = IQ.createResultIQ(request);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addAttribute("name", getOperateName());
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
        operate.addAttribute("name", getOperateName());
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addElement("roomjid").addText(voteBean.getRoomJid());
        operate.addElement("id").addText(String.valueOf(voteBean.getId()));
        
        
        Element selects = operate.addElement("selects");
        for(ConferenceSelectBean selectBean:voteBean.getSelects()){
        	Element select = selects.addElement("select");
        	select.addElement("id").addText(String.valueOf(selectBean.getId()));
        	select.addElement("vote_num").addText(String.valueOf(selectBean.getTotalNum()));
        	Element options = select.addElement("options");
        	for(ConferenceOptionBean optionBean:selectBean.getOptions()){
        		Element option = options.addElement("option");
        		option.addElement("id").addText(String.valueOf(optionBean.getId()));
        		option.addElement("num").addText(String.valueOf(optionBean.getNum()));
        		option.addElement("percent").addText(String.valueOf(optionBean.getPercent()));
        	}
        }
        
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
        this.setRoomJidStr(roomJidStr);
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
        
        return voteBean;
	}

}
