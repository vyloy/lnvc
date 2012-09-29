/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolFacade;

/**
 *
 * @author Administrator
 */
public class MyDataBase {

    private static Logger log = Logger.getLogger(MyDataBase.class);
//    private static final String USER_NAME = "vovo";
//    private static final String PASS_WORD = "passwd";
    private static String alias;

    public static Connection getConnection() throws Exception{
    	Connection conn = DriverManager.getConnection("proxool." + alias);
        return conn;
    }

    public static void init(String lccno) throws Exception{
        String path = Constants.USER_DATA_DIR + "/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
        Properties info = new Properties();
        info.setProperty("proxool.maxinum-connection-count", "10");
        info.setProperty("proxool.house-keeping-test-sql", "select CURRENT_DATE");
//        info.setProperty("user", USER_NAME);
//        info.setProperty("password", PASS_WORD);
        alias = "vovo" + lccno;
        String driverClass = "org.sqlite.JDBC";
        String driverUrl = "jdbc:sqlite:" + path  + lccno + ".vovo";
        String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;
        ProxoolFacade.registerConnectionPool(url, info);
        initTable();
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception ex) {
            	log.error("closeResultSet", ex);
            } finally {
                rs = null;
            }
        }
    }

    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception ex) {
            	log.error("closeStatement", ex);
            } finally {
                stmt = null;
            }
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception ex) {
            	log.error("closeConnection", ex);
            } finally {
                con = null;
            }
        }
    }

    public static void closeAll(ResultSet rs, Statement stmt, Connection con) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(con);
    }

    private static void initTable() throws Exception{
        String sql = "SELECT COUNT(1) ct  FROM sqlite_master where type='table' and name='chat_record'";
        Statement stmt = null;
        ResultSet rs = null;
        int ct = 0;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ct = rs.getInt("ct");
            }
            if (ct < 1) {//创建表chat_record
                stmt.executeUpdate("CREATE TABLE chat_record (" +
                		"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                		"senderId INTEGER, " +
                		"senderName TEXT, " +
                		"content TEXT, " +  
                		"sendDate TEXT, " +
                		"sessionId TEXT, " +
                		"fontSize INTEGER, " +
                		"fontName TEXT, " +
                		"fontColor INTEGER, " +
                		"bold BOOLEAN, " +
                		"italic BOOLEAN, " +
                		"underLine BOOLEAN " +
                		")");
                stmt.executeUpdate("CREATE UNIQUE INDEX idx_chat_record_id on chat_record (id ASC)");
            }
            
        } finally {
            closeAll(rs, stmt, con);
        }
    }
    
	public static void printSQL(String sql, Object[] params) {
		// 1 如果没有参数，说明是不是动态SQL语句
		int paramNum = 0;
		if (null != params)
			paramNum = params.length;
		if (1 > paramNum)
			log.info(sql);
		// 2 如果有参数，则是动态SQL语句
		StringBuffer returnSQL = new StringBuffer();
		String[] subSQL = sql.split("\\?");
		for (int i = 0; i < paramNum; i++) {
			if (params[i] instanceof Integer) {
				returnSQL.append(subSQL[i]).append(" ").append(params[i])
						.append(" ");
			} else if (params[i] instanceof Date) {
				returnSQL.append(subSQL[i]).append(" '").append(
						DateUtil.getStringByDate((java.util.Date) params[i]))
						.append("' ");
			} else {
				returnSQL.append(subSQL[i]).append(" '").append(params[i])
						.append("' ");
			}
		}
		log.info(returnSQL.toString());
	}



    public static void main(String args[])throws Exception{
        MyDataBase.init("100000");
//        ResultSet rs = null;
//        Statement stmt = null;
//        try {
//            stmt = con.createStatement();
//            stmt.executeUpdate("insert into chat_record (sender) values ('a')");
//            rs = stmt.executeQuery("select * from chat_record");
//            while (rs.next()) {
//                System.out.println(rs.getString("sender"));
//            }
//        } catch (Exception ex) {
//            log.info(ex);
////            ex.printStackTrace();;
//        } finally {
//            MyDataBase.closeAll(rs, stmt, con);
//        }
    }
}
