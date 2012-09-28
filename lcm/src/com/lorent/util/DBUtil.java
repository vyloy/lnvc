package com.lorent.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private DBUtil() {

	}

	static{
		try{
			System.out.println("Load oracle driver...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			System.out.println("Load oracle driver fail...");
		}
	}
	
	static{
		try{
			System.out.println("Load mysql driver...");
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("Load mysql driver fail...");
		}
	}
	
	static {
		try {
			System.out.println("Load postgresql driver...");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Load postgresql driver fail...");
		}
	}

	public static Connection getConnection(String url, String username, String passwd) throws SQLException {
		return DriverManager.getConnection(url, username, passwd);
	}

	public static void close(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Connection connection = DBUtil.getConnection("jdbc:postgresql://10.168.250.12:5432/test", "lcmadmin", "password");
		System.out.println(connection);
	}

}
