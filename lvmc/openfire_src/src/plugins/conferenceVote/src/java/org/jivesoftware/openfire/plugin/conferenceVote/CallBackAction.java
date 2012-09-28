package org.jivesoftware.openfire.plugin.conferenceVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class CallBackAction {

	public abstract List doCallBack(Connection con,PreparedStatement pstmt,ResultSet rs) throws Exception;
	
}
