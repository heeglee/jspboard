<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글보기</title> 
		<link rel="stylesheet" href="./css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글보기</h3>
			<div class="view">
				<form action="#" method="post">
					<table>
						<tr>
							<td>제목</td>
							<td><input type="text" name="subject" value="${ vo.title }" readonly />
							</td>
						</tr>
						
						<c:if test="${ vo.file > 0 }">
						<tr>
							<td>첨부파일</td>
							<td>
								<a href="/jboard2/filedown.do?parent=${fvo.parent}">${ fvo.oldName }</a>
								<span>${ fvo.download }회 다운로드</span>
							</td>
						</tr>
						</c:if>
						
						<tr>
							<td>내용</td>
							<td>
								<textarea name="content" rows="20" readonly>${ vo.content }</textarea>
							</td>
						</tr>
					</table>
					<div class="btns">
						<c:if test="${ member.uid == vo.uid }">
							<a href="#" class="cancel del">삭제</a>	
							<a href="/jboard2/modify.do?pg=${pg}&seq=${vo.seq}" class="cancel mod">수정</a>
						</c:if>
						<a href="/jboard2/list.do?pg=${ pg }" class="cancel">목록</a>
					</div>
				</form>
			</div><!-- view 끝 -->
			
			<!-- 댓글리스트 -->
			<section class="comments">
				<h3>댓글목록</h3>
				
				
				<c:forEach var="commt" items="${ comments }">
					<div class="comment">
						<span>
							<span>${ commt.nick }</span>
							<span>${ commt.rdate }</span>
						</span>
						<textarea>${ commt.content }</textarea>
						<div>
							<a href="#" class="del">삭제</a>
							<a href="#" class="mod">수정</a>
						</div>
					</div>
				</c:forEach>
				
				<c:if test="${ empty comments }">
					<p class="empty">
						등록된 댓글이 없습니다.
					</p>
				</c:if>
				
			</section>
			
			<!-- 댓글쓰기 -->
			<section class="comment_write">
				<h3>댓글쓰기</h3>
				<div>
					<form action="/jboard2/comment.do" method="post">
						<input type="hidden" name="pg" 	 value="${pg}" />
						<input type="hidden" name="parent" value="${vo.seq}" />
						<input type="hidden" name="cate" 	 value="${vo.cate}" />
						<input type="hidden" name="uid" 	 value="${member.uid}" />
						<textarea name="comment" rows="5"></textarea>
						<div class="btns">
							<a href="#" class="cancel">취소</a>
							<input type="submit" class="submit" value="작성완료" />
						</div>
					</form>
				</div>
			</section>
		</div><!-- board 끝 -->
	</body>

</html>










