package org.jivesoftware.openfire.plugin.groupchat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.muc.MUCRole;
import org.jivesoftware.openfire.muc.MUCRoom;
import org.jivesoftware.openfire.plugin.groupchat.util.Constants;
import org.jivesoftware.util.StringUtils;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;



public abstract class BaseAction {

	
	
	public BaseAction(){}
	
//	public BaseAction(ConferenceVoteMessage message){
//		this.message = message;
//	}
	
	public abstract List execute(IQ request);
	
//	public List execute(final IQ request,CallBackAction callBackAction){
//		Connection con = null;
//        PreparedStatement pstmt = null;
//        boolean abortTransaction = false;
//        ResultSet rs = null;
//        
//        List replys = new ArrayList();
//		try {
//			con = DbConnectionManager.getTransactionConnection();
//			replys = callBackAction.doCallBack(con,pstmt,rs);
//        }
//        catch (Exception e) {
//        	e.printStackTrace();
//            abortTransaction = true;
//            String msg = null;
//            if(e instanceof ConferenceVoteException){
//            	msg = e.getMessage();
//            }else{
//            	msg = "操作出错";
//            }
//            message.setResultMessage(msg);
//            try {
//				replys.add(this.getReplyIQ(request, message));
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//        }
//        finally {
//            DbConnectionManager.closeStatement(pstmt);
//            DbConnectionManager.closeTransactionConnection(con, abortTransaction);
//        }
//		
//		return replys;
//	}
	
	public List<String> getUserAddress(String roomJid){
		List<String> list = new ArrayList<String>();
		JID roomJID = new JID(roomJid);
		String roomName = roomJID.getNode();
		MUCRoom room = XMPPServer.getInstance().getMultiUserChatManager().getMultiUserChatService(roomJID).getChatRoom(roomName);
		for (MUCRole role : room.getOccupants()) {
			list.add(role.getUserAddress().toString());
		}
		return list;
	}
	
	public String serializeObject(Object obj) throws Exception{
		ByteArrayOutputStream sw = new ByteArrayOutputStream();   
        ObjectOutputStream oos = new ObjectOutputStream(sw);
        oos.writeObject(obj);
        return StringUtils.encodeBase64(sw.toByteArray());
	}
	
	public IQ getReplyIQ(IQ request,Object obj) throws Exception{
		IQ reply = IQ.createResultIQ(request);
        Element queryResult = DocumentHelper.createElement(QName.get(Constants.PREFIX_NAME, Constants.SPACE_NAME));
        queryResult.addElement("content").addText(this.serializeObject(obj));
        reply.setChildElement(queryResult);
		return reply;
	}

	
	public boolean checkList(List<?> list){
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	
}
