<%@page import="kr.co.jboard1.config.DBConfig"%>
<%@page import="kr.co.jboard1.vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	String sql = "select * from JB_MEMBER where uid='";
	sql += id + "' and pass='";
	sql += pw + "';";

	conn = DBConfig.getConnect();
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	
	if(rs.next()) {
		// login success
		MemberVO user = new MemberVO();
		user.setSeq(rs.getInt(1));
		user.setUid(rs.getString(2));
		user.setPass(rs.getString(3));
		user.setName(rs.getString(4));
		user.setNick(rs.getString(5));
		user.setEmail(rs.getString(6));
		user.setHp(rs.getString(7));
		user.setGrade(rs.getInt(8));
		user.setZip(rs.getString(9));
		user.setAddr1(rs.getString(10));
		user.setAddr2(rs.getString(11));
		user.setRegip(rs.getString(12));
		user.setRdate(rs.getString(13));
		
		rs.close();
		stmt.close();
		conn.close();
		
		session.setAttribute("user", user);
		response.sendRedirect("../list.jsp");
		
	} else {
		// login failed
		rs.close();
		stmt.close();
		conn.close();
		
		response.sendRedirect("../login.jsp?reg=fail");
	}
	
%>