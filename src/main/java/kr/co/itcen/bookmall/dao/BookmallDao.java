package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookmallDao {
//	protected Connection conn = null;
//	protected PreparedStatement pstmt = null;
//	protected ResultSet rs = null;
	
	protected Connection openConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.48:3306/bookmall?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
//	protected void closeConnection() throws SQLException {
//		if (conn != null) {
//			conn.close();
//		}
//
//		if (pstmt != null) {
//			pstmt.close();
//		}
//
//		if (rs != null) {
//			rs.close();
//		}
//	}
}
