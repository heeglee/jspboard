<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글수정</title> 
		<link rel="stylesheet" href="./css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글수정</h3>
			<div class="write">
				<form action="/jboard2/modify.do" method="post">
					<input type="hidden" name="pg" value="${pg}" />
					<input type="hidden" name="seq" value="${vo.seq}" />
					<input type="hidden" name="cate" value="free" />
					<input type="hidden" name="uid" value="${ member.uid }" />
					<table>
						<tr>
							<td>제목</td>
							<td><input type="text" name="subject" placeholder="제목을 입력하세요." required value="${ vo.title }" /></td>
						</tr>				
						<tr>
							<td>내용</td>
							<td>
								<textarea name="content" rows="20" required>${ vo.content }</textarea>
							</td>
						</tr>
						<tr>
							<td>첨부</td>
							<td>
								<input type="file" name="file" />
							</td>
						</tr>
					</table>
					<div class="btns">
						<a href="/jboard2/view.do?pg=${pg}&seq=${vo.seq}" class="cancel">취소</a>
						<input type="submit" class="submit" value="수정완료" />
					</div>
				</form>
			</div>
		</div>
	</body>
</html>


