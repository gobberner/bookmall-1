package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.UserVo;

public class UserDao extends BookmallDao {
	public boolean insert(UserVo vo) {
		int ar = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = this.openConnection();

			String sql = "insert into user(name, contact, email, passwd) values(?, ?, ?, password(?))";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContact());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPasswd());

			ar = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (ar == 1) ? true : false;
	}

	public UserVo get(String email) {
		UserVo userVo = null;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			conn = this.openConnection();
			String sql = "select no, name, contact, email, passwd from user where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				userVo = new UserVo(rs.getLong("no")
						, rs.getString("name")
						, rs.getString("contact")
						, rs.getString("email")
						, rs.getString("passwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
				if (rs != null) {
					rs.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userVo;
	}

	public List<UserVo> getList() {
		List<UserVo> list = new ArrayList<>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();

			String sql = "select no, name, contact, email, passwd from user order by no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserVo vo = new UserVo(rs.getLong("no")
						, rs.getString("name")
						, rs.getString("contact")
						, rs.getString("email")
						, rs.getString("passwd"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
				if (rs != null) {
					rs.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public int deleteAll() {
		int ar = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();

			String sql = "delete from user";
			pstmt = conn.prepareStatement(sql);
			ar = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ar;
	}

	public boolean update(UserVo vo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = this.openConnection();

			String sql = "update user set name=?, contact=?, email=? where no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContact());
			pstmt.setString(3, vo.getEmail());
			pstmt.setLong(4, vo.getNo());

			ar = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return (ar == 1) ? true : false;
	}
}
