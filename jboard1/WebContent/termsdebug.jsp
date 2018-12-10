<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	String HOST = "jdbc:mysql://192.168.0.178:3306/heeg";
	String USER = "heeg";
	String PASS = "1234";

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(HOST, USER, PASS);
	stmt = conn.createStatement();
	rs = stmt.executeQuery("select * from JB_TERMS;");
	rs.next();
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>terms</title>
</head>

<body>
	<%= rs.getString(1) %>
</body>
</html>

<%
	rs.close();
	stmt.close();
	conn.close();
%>