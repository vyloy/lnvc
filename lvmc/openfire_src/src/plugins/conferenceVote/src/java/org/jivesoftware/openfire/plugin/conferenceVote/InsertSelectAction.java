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
import org.jivesoftware.openfire.plugin.conferenceVote.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public class InsertSelectAction extends BaseAction{

	private static final Logger Log = LoggerFactory.getLogger(InsertSelectAction.class);
	
	@Override
	public List execute(IQ request) {
		Connection con = null;
        PreparedStatement pstmt = null;
        boolean abortTransaction = false;
        ResultSet rs = null;
        ConferenceSelectBean selectBean = new ConferenceSelectBean();
        List replys = new ArrayList();
		try {
			con = DbConnectionManager.getTransactionConnection();
			parseXML(request,con,selectBean);
			
			pstmt = con.prepareStatement("insert into conference_select (conference_vote_id,type,select_title,select_remark,select_num) values (?,?,?,?,?) returning conference_select.id");
			pstmt.setInt(1, selectBean.getConferenceVoteId());
			pstmt.setInt(2, selectBean.getType());
            pstmt.setString(3, selectBean.getSelectTitle());
            pstmt.setString(4, selectBean.getSelectRemark());
            pstmt.setInt(5, selectBean.getSelectNum());
//            pstmt.executeUpdate();
            
            rs = pstmt.executeQuery();
        	int selectId = 0;
        	if(rs.next()){
        		selectId = rs.getInt(1);
        	}
            DbConnectionManager.fastcloseStmt(pstmt);
            selectBean.setId(selectId);
//            pstmt = con.prepareStatement("delete from conference_option where conference_select_id=?");
//            pstmt.setInt(1, selectBean.getId());
//            pstmt.executeUpdate();
//            DbConnectionManager.fastcloseStmt(pstmt);
            
            List<ConferenceOptionBean> options = selectBean.getOptions();
        	for(ConferenceOptionBean optionBean:options){
        		pstmt = con.prepareStatement("insert into conference_option (conference_select_id,option_name) values (?,?) returning conference_option.id");
        		pstmt.setInt(1, selectBean.getId());
        		pstmt.setString(2, optionBean.getOptionName());
//        		pstmt.executeUpdate();
        		rs = pstmt.executeQuery();
        		int optionId = 0;
        		if(rs.next()){
        			optionId = rs.getInt(1);
        		}
        		DbConnectionManager.fastcloseStmt(pstmt);
        		optionBean.setId(optionId);
        		optionBean.setConferenceSelectId(selectBean.getId());
        	}
            
            replys.add(this.getSuccessReplyIQ(request, selectBean));
            List<String> userAddress = this.getUserAddress(selectBean.getRoomJid());
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
            replys.add(this.getFailReplyIQ(request, selectBean, message));
        }
        finally {
            DbConnectionManager.closeStatement(pstmt);
            DbConnectionManager.closeTransactionConnection(con, abortTransaction);
        }
		
		return replys;
	}

	
	public IQ getFailReplyIQ(IQ request,ConferenceSelectBean selectBean,String message){
		IQ reply = IQ.createResultIQ(request);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addAttribute("name", ActionFactory.INSERT_SELECT);
        operate.addElement("result").addText(Constants.FAIL);
        operate.addElement("roomjid").addText(selectBean.getRoomJid());
        operate.addElement("message").addText(message);
        reply.setChildElement(queryResult);
		return reply;
	}
	
	public IQ getSuccessReplyIQ(IQ request,ConferenceSelectBean selectBean){
		IQ reply = IQ.createResultIQ(request);
//		reply.setTo(userJid);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        Element operate = queryResult.addElement("operate");
        operate.addAttribute("name", ActionFactory.INSERT_SELECT);
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addElement("roomjid").addText(selectBean.getRoomJid());
        operate.addElement("id").addText(String.valueOf(selectBean.getId()));
        operate.addElement("conference_vote_id").addText(String.valueOf(selectBean.getConferenceVoteId()));
        operate.addElement("type").addText(String.valueOf(selectBean.getType()));
        operate.addElement("select_title").addText(selectBean.getSelectTitle());
        operate.addElement("select_num").addText(String.valueOf(selectBean.getSelectNum()));
        operate.addElement("select_remark").addText(String.valueOf(selectBean.getSelectRemark()));
        Element options = operate.addElement("options");
        List<ConferenceOptionBean> optionList = selectBean.getOptions();
        for(ConferenceOptionBean optionBean:optionList){
        	Element option = options.addElement("option");
        	option.addElement("id").addText(String.valueOf(optionBean.getId()));
        	option.addElement("option_name").addText(optionBean.getOptionName());
        }
        
        //        operate.addElement("creator").addText(selectBean.getCreatorStr());
//        Element selects = operate.addElement("selects");
//        for(ConferenceSelectBean selectBean:selectBean.getSelects()){
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
	
	
	public ConferenceSelectBean parseXML(IQ request, Connection con, ConferenceSelectBean selectBean) throws Exception{
		PreparedStatement pstmt = null;
        boolean abortTransaction = false;
        ResultSet rs = null;
//        ConferenceSelectBean selectBean = new ConferenceSelectBean();
//		List<ConferenceSelectBean> selectBeans = new ArrayList<ConferenceSelectBean>();
//		Map<ConferenceSelectBean,List<ConferenceOptionBean>> optionMap = new HashMap<ConferenceSelectBean,List<ConferenceOptionBean>>();
		Element query = request.getChildElement();
		Element operate = query.element("operate");
        String command = operate.attributeValue("name");
        Element roomJid = operate.element("roomjid");
        String roomJidStr = roomJid.getText();
        selectBean.setRoomJid(roomJidStr);
//        Element id = operate.element("id");
//        String idStr = id.getText();
//        if(idStr == null || idStr.trim().length()<1){
//        	throw new Exception("表决项ID为空");
//        }else{
//        	try{
//        		selectBean.setId(Integer.parseInt(idStr));
//        	}catch(Exception e){
//        		throw new Exception("表决项ID参数错误");
//        	}
//        }
        Element type = operate.element("type");
		String typeStr = type.getText();
		try{
			selectBean.setType(Integer.parseInt(typeStr));
		}catch(Exception e){
			throw new ConferenceVoteException("表决项类型参数错误");
		}
		Element conferenceVoteId = operate.element("conference_vote_id");
		String conferenceVoteIdStr = conferenceVoteId.getText();
		try{
			selectBean.setConferenceVoteId(Integer.parseInt(conferenceVoteIdStr));
		}catch(Exception e){
			throw new ConferenceVoteException("表决项投票主题ID参数错误");
		}
		Element selectTitle = operate.element("select_title");
		String selectTitleStr = selectTitle.getText();
		selectBean.setSelectTitle(selectTitleStr);
		Element selectRemark = operate.element("select_remark");
		String selectRemarkStr = selectRemark.getText();
		selectBean.setSelectRemark(selectRemarkStr);
		Element selectNum = operate.element("select_num");
		String selectNumStr = selectNum.getText();
		try{
			selectBean.setSelectNum(Integer.parseInt(selectNumStr));
		}catch(Exception ex){
			selectBean.setSelectNum(0);
		}
		Element options = operate.element("options");
		List<Element> optionList = options.elements("option");
		if(optionList != null && optionList.size()>0){
//			List<ConferenceOptionBean> list = new ArrayList<ConferenceOptionBean>();
			for(Element option:optionList){
				ConferenceOptionBean optionBean = new ConferenceOptionBean();
				Element optionName = option.element("option_name");
				String optionNameStr = optionName.getText();
				optionBean.setOptionName(optionNameStr);
				selectBean.getOptions().add(optionBean);
			}
		}
        return selectBean;
	}

}
