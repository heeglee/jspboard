<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>var login = Boolean("${ sessionScope.login }");</script>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글목록</title> 
		<link rel="stylesheet" href="./css/style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/jboard2/js/listLoginCheck.js"></script>
	</head>
	<body>
		<div id="board">
			<h3>글목록</h3>
			<!-- 리스트 -->
			<div class="list">
				<p class="logout">
					${ member.nick }님! 반갑습니다. 
					<a href="/jboard2/member/logout.do">[로그아웃]</a>
				<p>
				<table>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>글쓴이</td>
						<td>날짜</td>
						<td>조회</td>
					</tr>
				
					<c:forEach var="vo" items="${ list }">					
						<tr>
							<td>${ startNum }</td>
							<td><a href="/jboard2/view.do?pg=${pg}&seq=${ vo.seq }" class="title">${ vo.title }</a>&nbsp;[${ vo.comment }]</td>
							<td>${ vo.nick }</td>
							<td>${ vo.rdate }</td>
							<td>${ vo.hit }</td>
						</tr>
						<c:set var="startNum" value="${ startNum-1 }" />
					</c:forEach>
					
				</table>
			</div>
			<!-- 페이징 -->
			<nav class="paging">
				<span> 
				<c:if test="${ groupStart > 1 }">
					<a href="/jboard2/list.do?pg=${ groupStart-1 }" class="prev">이전</a>
				</c:if>
				<c:forEach var="k" begin="${ groupStart }" end="${ groupEnd }">
					<a href="/jboard2/list.do?pg=${k}" class="${k==pg ? 'current':''} num">${ k }</a>
				</c:forEach>
				
				<c:if test="${ groupEnd < page }">
					<a href="/jboard2/list.do?pg=${ groupEnd+1 }" class="next">다음</a>
				</c:if>
				</span>
			</nav>
			<a href="/jboard2/write.do" class="btnWrite">글쓰기</a>
		</div>
	</body>

</html>










