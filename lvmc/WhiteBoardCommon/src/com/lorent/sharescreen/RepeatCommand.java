package com.lorent.sharescreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.sharefile.ShareFileDto;
import com.lorent.sharefile.bean.UserInfoBean;
import com.lorent.whiteboard.command.MeetingCommandAdaptor;

public class RepeatCommand extends MeetingCommandAdaptor {
	
	private static final Logger logger = LoggerFactory.getLogger(RepeatCommand.class);

	public RepeatCommand(String meetingId,String toUserName) {
		super(meetingId);
		this.toUserName = toUserName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public synchronized void execute(IoSession session){
		String username = getToUserName();
        logger.info("RepeatCommand:username=" + username);
		ArrayList<UserInfoBean> userInfos = ShareFileDto.getUserInfos(getMeetingId());
		for (UserInfoBean userInfoBean : userInfos) {
			if(username.equals(userInfoBean.getUserName())){				
				logger.info("RepeatCommand: send target:"+userInfoBean.getAliveSession());
				userInfoBean.getAliveSession().write(this);
				break;
			}
		}
	}
	
	private String toUserName;

	/**
	 * @return the toUserName
	 */
	public String getToUserName() {
		return toUserName;
	}

	private String operation;

	private Map<String, Object> parameters = new HashMap<String, Object>();
	
	
	public void setParameter(String key,Object value){
		parameters.put(key, value);
	}
	
	
	public Object getParameter(String key){
		return parameters.get(key);
	}


	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
