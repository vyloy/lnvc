package org.jivesoftware.openfire.plugin.groupchat;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;

import org.dom4j.Element;
import org.jivesoftware.util.LocaleUtils;
import org.jivesoftware.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;


public class ActionFactory {

	private static final Logger Log = LoggerFactory.getLogger(ActionFactory.class);
	
	private static ActionFactory factory = new ActionFactory();
	
	public final static String GET_GROUPCHAT = "getGroupChat";
	public final static String APPLYIN_GROUPCHAT = "applyInGroupChat";
	public final static String SET_GROUPCHAT_AUTHORITY = "setGroupChatAuthority";
	public final static String FETCH_GROUPCHAT_AUTHORITY = "fetchGroupChatAuthority";
	public final static String UPDATE_GROUPCHAT_AUTHORITY = "updateGroupChatAuthority";
	public final static String SEARCH_GROUPCHAT = "searchGroupChat";
	public final static String QUIT_GROUPCHAT = "quitGroupChat";
	
	private ActionFactory(){
		
	}
	
	public static ActionFactory getInstance(){
		return factory;
	}
	
	public BaseAction getAction(String str){
		BaseAction action = null;
		if(GET_GROUPCHAT.equals(str)){
			action = new GetGroupChatAction();
		}else if(APPLYIN_GROUPCHAT.equals(str)){
			action = new ApplyInGroupChatAction();
		}else if(SET_GROUPCHAT_AUTHORITY.equals(str)){
			action = new SetGroupChatMembersAuthority();
		}else if(FETCH_GROUPCHAT_AUTHORITY.equals(str)){
			action = new FetchGroupChatAuthorityAction();
		}else if(UPDATE_GROUPCHAT_AUTHORITY.equals(str)){
			action = new UpdateGroupChatAuthorityAction();
		}else if(SEARCH_GROUPCHAT.equals(str)){
			action = new SearchGroupChatAction();
		}else if(QUIT_GROUPCHAT.equals(str)){
			action = new QuitGroupChatAction();
		}
		return action;
	}
	
	
//	public BaseAction getAction(String str){
//		BaseAction action = null;
//		Log.error("SEARCH_VOTE_RECORD==" + SEARCH_VOTE_RECORD);
//		Log.error(LocaleUtils.getLocalizedString("admin.error"), "SEARCH_VOTE_RECORD==" + SEARCH_VOTE_RECORD);
//		if(CREATE_VOTE.equals(str)){
//			action = new CreateVoteAction();
//		}else if(UPDATE_VOTE.equals(str)){
//			action = new UpdateVoteAction();
//		}else if(UPDATE_SELECT.equals(str)){
//			action = new UpdateSelectAction();
//		}else if(INSERT_SELECT.equals(str)){
//			action = new InsertSelectAction();
//		}else if(DELETE_SELECT.equals(str)){
//			action = new DeleteSelectAction();
//		}else if(DELETE_OPTION.equals(str)){
//			action = new DeleteOptionAction();
//		}else if(COUNT.equals(str)){
//			action = new CountAction();
//		}else if(BOARDCAST.equals(str)){
//			action = new VoteBroadCastAction();
//		}else if(DOVOTE.equals(str)){
//			action = new DoVoteAction();
//		}else if(LOAD_VOTE.equals(str)){
//			action = new LoadVoteAction();
//		}else if(SEARCH_VOTE_RECORD.equals(str)){
//			action = new SearchVoteRecordAction();
//		}
//		return action;
//	}
//	
//	public BaseAction getAction(ConferenceVoteMessage message){
//		BaseAction action = null;
//		if(CREATE_VOTE.equals(message.getCommand())){
//			action = new CreateVoteAction1();
//		}else if(UPDATE_VOTE.equals(message.getCommand())){
//			action = new UpdateVoteAction();
//		}else if(UPDATE_SELECT.equals(message.getCommand())){
//			action = new UpdateSelectAction();
//		}else if(INSERT_SELECT.equals(message.getCommand())){
//			action = new InsertSelectAction();
//		}else if(DELETE_SELECT.equals(message.getCommand())){
//			action = new DeleteSelectAction();
//		}else if(DELETE_OPTION.equals(message.getCommand())){
//			action = new DeleteOptionAction();
//		}else if(COUNT.equals(message.getCommand())){
//			action = new CountAction();
//		}else if(BOARDCAST.equals(message.getCommand())){
//			action = new VoteBroadCastAction();
//		}else if(DOVOTE.equals(message.getCommand())){
//			action = new DoVoteAction();
//		}else if(LOAD_VOTE.equals(message.getCommand())){
//			action = new LoadVoteAction();
//		}
//		action.setMessage(message);
//		return action;
//	}
	
	public Object parseXml(IQ request) throws Exception{
		Element query = request.getChildElement();
		Element content = query.element("content");
		return externalizeString(content.getText());
	}
	
	public Object externalizeString(String content) throws Exception{
		ByteArrayInputStream sis = new ByteArrayInputStream(StringUtils.decodeBase64(content));
        ObjectInputStream ois = new ObjectInputStream(sis);
        Object ob = ois.readObject();
        return ob;
	}
}
