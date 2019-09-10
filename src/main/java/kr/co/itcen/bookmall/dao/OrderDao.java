package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.OrderBookVo;
import kr.co.itcen.bookmall.vo.OrderVo;
import kr.co.itcen.bookmall.vo.UserVo;

public class OrderDao extends BookmallDao {

	public int deleteAll() {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			List<OrderVo> orderList = this.getAllList();
			
			for (OrderVo orderVo : orderList) {
				System.out.println("[" + orderVo.getNo() + "] " + "삭제한 order_book 개수: " + this.deleteOrderBook(orderVo));
			}
			
			conn = this.openConnection();
			
			String sql = "delete from bookmall.order";
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
	
	private int deleteOrderBook(OrderVo orderVo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "delete from order_book where order_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderVo.getNo());
			
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
	
	private List<OrderVo> getAllList() {
		List<OrderVo> orderList = new ArrayList<>();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select no, user_no, address, reg_date from bookmall.order order by no asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderVo orderVo = new OrderVo(rs.getLong("no")
										, rs.getLong("user_no")
										, rs.getString("address")
										, rs.getDate("reg_date")
										, null)
				;
				
				List<OrderBookVo> orderBookList = this.getOrderBookList(orderVo);
				orderVo.setOrderBookList(orderBookList);
				
				orderList.add(orderVo);
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
		
		return orderList;
	}

	public long insert(OrderVo orderVo) {
		long idx = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "insert into bookmall.order(user_no, address) values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderVo.getUserNo());
			pstmt.setString(2, orderVo.getAddress());
			
			int rslt = pstmt.executeUpdate();
			
			if (rslt == 1) {
				idx = this.getLastIndex();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return idx;
	}
	
	private long getLastIndex() {
		long idx = -1;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "SELECT LAST_INSERT_ID() as 'idx'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				idx = rs.getLong("idx");
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
		
		return idx;
	}

	public boolean insertOrderBook(OrderBookVo orderBookVo) {
		int rslt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "insert into order_book(order_no, book_no, count) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderBookVo.getOrderNo());
			pstmt.setLong(2, orderBookVo.getBookNo());
			pstmt.setLong(3, orderBookVo.getCount());
			
			rslt = pstmt.executeUpdate();
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
		
		return (rslt == 1) ? true : false;
	}

	public List<OrderVo> getList(UserVo userVo) {
		List<OrderVo> orderList = new ArrayList<>();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select no, user_no, address, reg_date from bookmall.order where user_no=? order by no asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, userVo.getNo());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderVo orderVo = new OrderVo(rs.getLong("no")
										, rs.getLong("user_no")
										, rs.getString("address")
										, rs.getDate("reg_date")
										, null)
				;
				
				List<OrderBookVo> orderBookList = this.getOrderBookList(orderVo);
				orderVo.setOrderBookList(orderBookList);
				
				orderList.add(orderVo);
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
		
		return orderList;
	}
	
	private List<OrderBookVo> getOrderBookList(OrderVo orderVo) {
		List<OrderBookVo> orderBookList = new ArrayList<>();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select no, order_no, book_no, count from order_book where order_no=? order by no asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderVo.getNo());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderBookVo orderBookVo = new OrderBookVo(rs.getLong("no")
												, rs.getLong("count")
												, rs.getLong("order_no")
												, rs.getLong("book_no"))
				;
				
				orderBookList.add(orderBookVo);
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
		
		return orderBookList;
	}

	public boolean updateOrderBook(OrderBookVo orderBookVo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "update order_book set count=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderBookVo.getCount());
			pstmt.setLong(2, orderBookVo.getNo());
			
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
