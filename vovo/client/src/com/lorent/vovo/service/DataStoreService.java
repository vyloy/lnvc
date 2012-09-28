package com.lorent.vovo.service;


import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.lorent.common.service.BaseService;
import com.lorent.vovo.dto.ChatRecord;
import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.util.MyDataBase;

public class DataStoreService extends BaseService {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public void saveChatRecord(ChatRecord record) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = MyDataBase.getConnection();
			ps = conn.prepareStatement("insert into chat_record" +
					"(senderId, senderName, content, sendDate, sessionId, fontSize, fontName, fontColor, bold, italic, underLine)" +
					" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, record.getSenderId());
			ps.setString(2, record.getSenderName());
			ps.setString(3, record.getMsg());
			ps.setString(4, sdf.format(record.getDate()));
			ps.setString(5, record.getSessionID());
			ps.setInt(6, record.getStyle().getSize());
			ps.setString(7, record.getStyle().getName());
			ps.setInt(8, record.getStyle().getColor().getRGB());
			ps.setBoolean(9, record.getStyle().getBold());
			ps.setBoolean(10, record.getStyle().getItalic());
			ps.setBoolean(11, record.getStyle().getUnderLine());
			ps.executeUpdate();
		}finally{
			MyDataBase.closeAll(rs, ps, conn);
		}
	}
	
	public int getRecordNum(String sessionID)throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = MyDataBase.getConnection();
			ps = conn.prepareStatement("select count(id) " +
					" from chat_record " +
					" where sessionId = ? ");
			ps.setString(1, sessionID);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		}finally{
			MyDataBase.closeAll(rs, ps, conn);
		}
	}
	
	public List<ChatRecord> getChatRecord(String sessionID, int num, int offset)throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ChatRecord> records = new ArrayList<ChatRecord>();
		try{
			conn = MyDataBase.getConnection();
			ps = conn.prepareStatement("select senderId, senderName, content, sendDate, fontSize, fontName, fontColor, bold, italic, underLine " +
					" from chat_record " +
					" where sessionId = ? " +
					" order by sendDate asc " + 
					" limit ?,?");
			ps.setString(1, sessionID);
			ps.setInt(2, offset);
			ps.setInt(3, num);
			rs = ps.executeQuery();
			ChatRecord record = null;
			FontStyle fontStyle = null;
			while(rs.next()){
				fontStyle = new FontStyle(rs.getString(6), rs.getInt(5), rs.getBoolean(8), rs.getBoolean(9), rs.getBoolean(10), new Color(rs.getInt(7)));
				record = new ChatRecord(rs.getInt(1), rs.getString(2), rs.getString(3), sdf.parse(rs.getString(4)), fontStyle, sessionID);
				records.add(record);
			}
		}finally{
			MyDataBase.closeAll(rs, ps, conn);
		}
		return records;
	}
}
