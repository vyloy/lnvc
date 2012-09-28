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

public class DeleteSelectAction extends BaseAction {

	private static final Logger Log = LoggerFactory.getLogger(DeleteSelectAction.class);
	
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
			
			pstmt = con.prepareStatement("delete from conference_select where id=?");
			pstmt.setInt(1, selectBean.getId());
			
            pstmt.executeUpdate();
            
            DbConnectionManager.fastcloseStmt(pstmt);
            pstmt = con.prepareStatement("delete from conference_option where conference_select_id=?");
            pstmt.setInt(1, selectBean.getId());
            pstmt.executeUpdate();
            DbConnectionManager.fastcloseStmt(pstmt);
            

            
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
        operate.addAttribute("name", ActionFactory.DELETE_SELECT);
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
        operate.addAttribute("name", ActionFactory.DELETE_SELECT);
        operate.addElement("result").addText(Constants.SUCCESS);
        operate.addElement("roomjid").addText(selectBean.getRoomJid());
        operate.addElement("id").addText(String.valueOf(selectBean.getId()));
        
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
        Element id = operate.element("id");
        String idStr = id.getText();
        if(idStr == null || idStr.trim().length()<1){
        	throw new ConferenceVoteException("表决项ID为空");
        }else{
        	try{
        		selectBean.setId(Integer.parseInt(idStr));
        	}catch(Exception e){
        		throw new ConferenceVoteException("表决项ID参数错误");
        	}
        }
        
        return selectBean;
	}
	
}
