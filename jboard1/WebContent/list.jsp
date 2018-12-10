<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.jboard1.dao.BoardDAO"%>
<%@page import="kr.co.jboard1.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.jboard1.vo.MemberVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String pg = request.getParameter("pg");

	MemberVO user = (MemberVO)session.getAttribute("user");
	
	BoardDAO dao = BoardDAO.getInstance();
	int total = dao.getTotalCount();
	int start = dao.getLimit(pg);
	int paging = dao.getPage(total);
	List<BoardVO> list = dao.list(start);
	
	int count = total - start;
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글목록</title> 
		<link rel="stylesheet" href="./css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글목록</h3>
			<!-- 리스트 -->
			<div class="list">
				<p class="logout"><%= user.getNick() %>님! 반갑습니다. <a href="./proc/logout.jsp">[로그아웃]</a><p>
				<table>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>글쓴이</td>
						<td>날짜</td>
						<td>조회</td>
					</tr>
				<%
					for(BoardVO vo : list) {
				%>
					<tr>
						<td><%= count-- %></td>
						<td><a href="./view.jsp?seq=<%= vo.getSeq() %>"><%= vo.getTitle() %></a>&nbsp;[<%= vo.getComment() %>]</td>
						<td><%= vo.getUid() %></td>
						<td><%= vo.getRdate().substring(2, 10) %></td>
						<td><%= vo.getHit() %></td>
					</tr>
				<%
					}
				%>
				</table>
			</div>
			
			<!-- 페이징 -->
			<nav class="paging">
				<span> 
				<a href="#" class="prev">이전</a>
				<%
					for(int i = 1; i <= paging; i++) {
				%>
				<a href="./list.jsp?pg=<%= i %>" class="num"><%= i %></a>
				<%
					}
				%>
				<a href="#" class="next">다음</a>
				</span>
			</nav>
			<a href="./write.jsp?uid=<%= user.getUid() %>" class="btnWrite">글쓰기</a>
		</div>
	</body>

</html>
