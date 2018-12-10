package kr.co.jboard1.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

	private static final String HOST = "jdbc:mysql://192.168.0.178:3306/heeg";
	private static final String USER = "heeg";
	private static final String PASS = "1234";
	
	public static Connection getConnect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		return conn;
	}
}
