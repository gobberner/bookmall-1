package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.BookVo;

public class BookDao extends BookmallDao {

	public boolean insert(BookVo vo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "insert into book(title, price, category_no) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			
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

	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<>();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select no, title, price, category_no from book order by no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookVo vo = new BookVo(rs.getLong("no")
								, rs.getString("title")
								, rs.getLong("price")
								, rs.getLong("category_no"))
				;
				
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
			
			String sql = "delete from book";
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

	public BookVo get(String title) {
		BookVo vo = null;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select no, title, price, category_no from book where title=? order by no asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new BookVo(rs.getLong("no")
						, rs.getString("title")
						, rs.getLong("price")
						, rs.getLong("category_no"))
				;
			}
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
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}

	public boolean update(BookVo bookVo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "update book set title=?, price=?, category_no=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setLong(2, bookVo.getPrice());
			pstmt.setLong(3, bookVo.getCategoryNo());
			pstmt.setLong(4, bookVo.getNo());
			
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
