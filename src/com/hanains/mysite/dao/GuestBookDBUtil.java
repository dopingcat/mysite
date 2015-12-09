package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GuestBookDBUtil {
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		
		return DriverManager.getConnection(dbUrl, "webdb", "webdb");
	}
}
