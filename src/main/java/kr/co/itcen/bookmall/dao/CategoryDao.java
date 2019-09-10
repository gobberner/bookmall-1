package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.vo.CategoryVo;

public class CategoryDao extends BookmallDao {

	public boolean insert(CategoryVo vo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "insert into category(name) values(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			
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

	public CategoryVo get(String name) {
		CategoryVo vo = null;
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select no, name from category where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new CategoryVo(rs.getLong("no")
						, rs.getString("name"))
				;
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
		
		return vo;
	}
	
	public List<CategoryVo> getList() {
		List<CategoryVo> list = new ArrayList<>();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "select no, name from category order by no asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CategoryVo vo = new CategoryVo(rs.getLong("no")
									, rs.getString("name"))
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
			
			String sql = "delete from category";
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

	public boolean update(CategoryVo categoryVo) {
		int ar = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.openConnection();
			
			String sql = "update category set name=? where no=?";
			conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryVo.getName());
			pstmt.setLong(2, categoryVo.getNo());
			
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
