<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Connection conn = null;
	PreparedStatement psmt = null;

	String HOST = "jdbc:mysql://192.168.0.178:3306/heeg";
	String USER = "heeg";
	String PASS = "1234";

	String uid = request.getParameter("id");
	String pw = request.getParameter("pw1");
	String name = request.getParameter("name");
	String nick = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp = request.getParameter("hp");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr();

	String sql = "insert into JB_MEMBER";
	sql += "(uid, pass, name, nick, email, hp, zip, addr1, addr2, regip, rdate)";
	sql += "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now());";

	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(HOST, USER, PASS);
	psmt = conn.prepareStatement(sql);

	psmt.setString(1, uid);
	psmt.setString(2, pw);
	psmt.setString(3, name);
	psmt.setString(4, nick);
	psmt.setString(5, email);
	psmt.setString(6, hp);
	psmt.setString(7, zip);
	psmt.setString(8, addr1);
	psmt.setString(9, addr2);
	psmt.setString(10, regip);

	psmt.executeUpdate(sql);

	psmt.close();
	conn.close();
	
	response.sendRedirect("./login.jsp?reg=success");
%>