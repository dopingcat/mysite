package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hanains.mysite.vo.BoardVo;

public class BoardDao {
	private static BoardDao boardDao = new BoardDao();
	
	private BoardDao() {
	}
	
	public static BoardDao getBoardDao() {
		return boardDao;
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "webdb", "webdb" );
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		}
		return connection;
	}
	
	public List<BoardVo> getSearchList(String keyword, String pageNum) {
		List<BoardVo> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = 
				"SELECT no, title, member_no, member_name, view_cnt, reg_date, rnum, page, totcnt " +
				"FROM (	SELECT list.*, ROWNUM AS RNUM, FLOOR(((ROWNUM-1)/10)+1) AS PAGE, COUNT(*) OVER() AS TOTCNT " +
						"FROM (	SELECT b.no, b.title, b.member_no, m.name AS member_name, b.view_cnt, to_char(b.reg_date, 'yyyy-mm-dd hh:mi:ss') AS reg_date " +
								"FROM board b, member m " +
								"WHERE b.member_no = m.no AND b.title like ? " +
								"ORDER BY b.reg_date desc) list) " +
				"WHERE PAGE = ?";
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, pageNum);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(resultSet.getLong(1));
				vo.setTitle(resultSet.getString(2));
				vo.setMemberNo(resultSet.getLong(3));
				vo.setMemberName(resultSet.getString(4));
				vo.setViewCnt(resultSet.getLong(5));
				vo.setRegDate(resultSet.getString(6));
				list.add(vo);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("excute sql failed - " + sqle.toString());
		}
		return list;
	}
	
	public Long getSearchPageSize(String keyword) {
		Long pageSize = new Long(0);
		
		String sql = 
				"SELECT MAX(PAGE) AS page_size " +
				"FROM (	SELECT list.*, ROWNUM AS RNUM, FLOOR(((ROWNUM-1)/10)+1) AS PAGE, COUNT(*) OVER() AS TOTCNT " +
						"FROM (	SELECT b.no, b.title, b.member_no, m.name AS member_name, b.view_cnt, to_char(b.reg_date, 'yyyy-mm-dd hh:mi:ss') AS reg_date " +
								"FROM board b, member m " +
								"WHERE b.member_no = m.no AND b.title LIKE ? " +
								"ORDER BY b.reg_date DESC) list)";
		
		System.err.println(sql.replace("?", keyword));
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, keyword);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				pageSize = resultSet.getLong(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("excute sql failed - " + sqle.toString());
		}
		return pageSize;
	}
	
	public List<BoardVo> getList(String pageNum) {
		List<BoardVo> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = 
				"SELECT no, title, member_no, member_name, view_cnt, reg_date, rnum, page, totcnt " +
				"FROM (	SELECT list.*, ROWNUM AS RNUM, FLOOR(((ROWNUM-1)/10)+1) AS PAGE, COUNT(*) OVER() AS TOTCNT " +
						"FROM (	select b.no, b.title, b.member_no, m.name as member_name, b.view_cnt, to_char(b.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date " +
								"from board b, member m " +
								"where b.member_no = m.no " +
								"order by b.reg_date desc) list) " +
				"WHERE PAGE = ?";
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pageNum);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(resultSet.getLong(1));
				vo.setTitle(resultSet.getString(2));
				vo.setMemberNo(resultSet.getLong(3));
				vo.setMemberName(resultSet.getString(4));
				vo.setViewCnt(resultSet.getLong(5));
				vo.setRegDate(resultSet.getString(6));
				list.add(vo);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("excute sql failed - " + sqle.toString());
		}
		return list;
	}
	
	public Long getTotalPageSize() {
		Long pageSize = new Long(0);
		String sql = 
				"SELECT MAX(PAGE) as page_size " +
				"FROM (	SELECT list.*, ROWNUM AS RNUM, FLOOR(((ROWNUM-1)/10)+1) AS PAGE, COUNT(*) OVER() AS TOTCNT " +
						"FROM (	select b.no, b.title, b.member_no, m.name as member_name, b.view_cnt, to_char(b.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date " +
								"from board b, member m " +
								"where b.member_no = m.no " +
								"order by b.reg_date desc) list)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				pageSize = resultSet.getLong(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("excute sql failed - " + sqle.toString());
		}
		return pageSize;
	}
	
	public BoardVo selectContent(String no) {
		String sql = 
				"select no, title, content, member_no " +
				"from board where no=?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		BoardVo vo = null;
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, no);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				vo = new BoardVo();
				vo.setNo(resultSet.getLong(1));
				vo.setTitle(resultSet.getString(2));
				vo.setContent(resultSet.getString(3));
				vo.setMemberNo(resultSet.getLong(4));
			}
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
		return vo;
	}
	
	public void incViewCnt(String no) {
		String sql = "update board set view_cnt = view_cnt+1 where no=?";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
	
	public void insert(BoardVo vo) {
		String sql = 
				"insert into board " +
				"values(BOARD_NO_SEQ.nextval, ?, ?, ?, 0, SYSDATE)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getMemberNo());
			
			pstmt.executeUpdate();
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

	public void update(BoardVo vo) {
		String sql = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE NO=?";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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