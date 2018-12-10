<%@page import="kr.co.jboard1.config.DBConfig"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	conn = DBConfig.getConnect();
	stmt = conn.createStatement();
	rs = stmt.executeQuery("select * from JB_TERMS;");
	rs.next();
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>terms</title>
<link rel="stylesheet" href="./css/style.css" />
</head>

<body>
	<div id="terms">
		<section>
			<table>
				<caption>사이트 이용약관</caption>
				<tr>
					<td><textarea readonly><%= rs.getString(1) %></textarea>
						<div>
							<label><input type="checkbox" name="chk1" />&nbsp;동의합니다.</label>
						</div></td>
				</tr>
			</table>
		</section>
		<section>
			<table>
				<caption>개인정보 취급방침</caption>
				<tr>
					<td><textarea readonly><%= rs.getString(2) %></textarea>
						<div>
							<label><input type="checkbox" name="chk2" />&nbsp;동의합니다.</label>
						</div></td>
				</tr>
			</table>
		</section>

		<div>
			<a href="./login.jsp" class="btnCancel">취소</a> <a
				href="./register.jsp" class="btnNext">다음</a>
		</div>

	</div>
</body>
</html>

<%
	rs.close();
	stmt.close();
	conn.close();
%>