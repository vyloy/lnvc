package org.jivesoftware.openfire.plugin.conferenceVote;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;

import org.dom4j.Element;
import org.jivesoftware.util.LocaleUtils;
import org.jivesoftware.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

import com.lorent.lvmc.bean.ConferenceVoteBean;
import com.lorent.lvmc.bean.ConferenceVoteMessage;

public class ActionFactory {

	private static final Logger Log = LoggerFactory.getLogger(ActionFactory.class);
	
	private static ActionFactory factory = new ActionFactory();
	
	public final static String CREATE_VOTE = "create_vote";
	public final static String UPDATE_VOTE = "update_vote";
	public final static String UPDATE_SELECT = "update_select";
	public final static String INSERT_SELECT = "insert_select";
	public final static String DELETE_SELECT = "delete_select";
	public final static String DELETE_OPTION = "delete_option";
	public final static String COUNT = "count";
	public final static String BOARDCAST = "boardcast";
	public final static String DOVOTE = "dovote";
	public final static String LOAD_VOTE = "load_vote";
	public final static String SEARCH_VOTE_RECORD = "search_vote_record";
	
	private ActionFactory(){
		
	}
	
	public static ActionFactory getInstance(){
		return factory;
	}
	
	
	public BaseAction getAction(String str){
		BaseAction action = null;
		if(CREATE_VOTE.equals(str)){
			action = new CreateVoteAction();
		}else if(UPDATE_VOTE.equals(str)){
			action = new UpdateVoteAction();
		}else if(UPDATE_SELECT.equals(str)){
			action = new UpdateSelectAction();
		}else if(INSERT_SELECT.equals(str)){
			action = new InsertSelectAction();
		}else if(DELETE_SELECT.equals(str)){
			action = new DeleteSelectAction();
		}else if(DELETE_OPTION.equals(str)){
			action = new DeleteOptionAction();
		}else if(COUNT.equals(str)){
			action = new CountAction();
		}else if(BOARDCAST.equals(str)){
			action = new VoteBroadCastAction();
		}else if(DOVOTE.equals(str)){
			action = new DoVoteAction();
		}else if(LOAD_VOTE.equals(str)){
			action = new LoadVoteAction();
		}else if(SEARCH_VOTE_RECORD.equals(str)){
			action = new SearchVoteRecordAction();
		}
		return action;
	}
	
	public BaseAction getAction(ConferenceVoteMessage message){
		BaseAction action = null;
		if(CREATE_VOTE.equals(message.getCommand())){
			action = new CreateVoteAction1();
		}else if(UPDATE_VOTE.equals(message.getCommand())){
			action = new UpdateVoteAction();
		}else if(UPDATE_SELECT.equals(message.getCommand())){
			action = new UpdateSelectAction();
		}else if(INSERT_SELECT.equals(message.getCommand())){
			action = new InsertSelectAction();
		}else if(DELETE_SELECT.equals(message.getCommand())){
			action = new DeleteSelectAction();
		}else if(DELETE_OPTION.equals(message.getCommand())){
			action = new DeleteOptionAction();
		}else if(COUNT.equals(message.getCommand())){
			action = new CountAction();
		}else if(BOARDCAST.equals(message.getCommand())){
			action = new VoteBroadCastAction();
		}else if(DOVOTE.equals(message.getCommand())){
			action = new DoVoteAction();
		}else if(LOAD_VOTE.equals(message.getCommand())){
			action = new LoadVoteAction();
		}
		action.setMessage(message);
		return action;
	}
	
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
