package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.CartVo;
import kr.co.itcen.bookmall.vo.UserVo;

public class CartDao extends BookmallDao {

	public int deleteAll() {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "delete from cart";
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

	public boolean insert(CartVo vo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "insert into cart(count, user_no, book_no) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getCount());
			pstmt.setLong(2, vo.getUserNo());
			pstmt.setLong(3, vo.getBookNo());
			
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

	public List<CartVo> getList(UserVo userVo) {
		List<CartVo> list = new ArrayList<>();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select c.no, c.count, c.user_no, c.book_no, (b.price * c.count) as price"
					+ " from cart as c, book as b, user as u"
					+ " where u.no = ?"
					+ " and c.book_no = b.no"
					+ " and c.user_no = u.no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, userVo.getNo());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CartVo vo = new CartVo(rs.getLong("no")
								, rs.getLong("count")
								, rs.getLong("user_no")
								, rs.getLong("book_no")
								, rs.getLong("price"))
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

	public boolean update(CartVo vo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "update cart set count=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getCount());
			pstmt.setLong(2, vo.getNo());
			
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
