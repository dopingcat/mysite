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
	
	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String sql = "select a.no, a.title, a.member_no,"
				+ " b.name as member_name, a.view_cnt,"
				+ " to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss')"
				+ " from board a, member b"
				+ " where a.member_no = b.no"
				+ " order by a.reg_date desc";
		try {
			if(connection == null) {
				connection = getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery(sql);
			
			while(resultSet.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(resultSet.getLong(1));
				vo.setTitle(resultSet.getString(2));
				vo.setMemberName(resultSet.getString(4));
				vo.setViewCnt(resultSet.getLong(5));
				vo.setRegDate(resultSet.getString(6));
				
				list.add(vo);
			}
		} catch (SQLException sqle) {
			System.err.println("excute sql failed - " + sqle.toString());
		}
		return list;
	}
}