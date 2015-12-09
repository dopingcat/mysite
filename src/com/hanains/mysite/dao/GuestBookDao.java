package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hanains.mysite.vo.GuestBookVo;

public class GuestBookDao {
	private GuestBookDBUtil dbUtil = null;
	
	public GuestBookDao() {
		dbUtil = new GuestBookDBUtil();
	}
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = "select no, name, password, message, reg_date from GUESTBOOK";
		
		try {
			if(connection == null) {
				connection = dbUtil.getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			resultSet = pstmt.executeQuery(sql);
			
			while(resultSet.next()) {
				list.add(new GuestBookVo(
						resultSet.getLong(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5)));
			}
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Driver Load failed - " + cnfe.toString());
		} catch (SQLException sqle) {
			System.err.println("excute sql failed - " + sqle.toString());
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.toString());
			}
		}
		return list;
	}
	
	public void insert(GuestBookVo vo) {
		String sql = "insert into guestbook values(GUESTBOOK_SEQ.nextval, ?, ?, ?, SYSDATE)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			if(connection == null) {
				connection = dbUtil.getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.toString());
		} catch (SQLException sqle) {
			System.err.println(sqle.toString());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.toString());
			}
		}
	}
	
	public void delete(Long no, String password) {
		String sql = "delete from guestbook where no=? and password=?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			if(connection == null) {
				connection = dbUtil.getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.toString());
		} catch (SQLException sqle) {
			System.err.println(sqle.toString());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.toString());
			}
		}
	}
}
