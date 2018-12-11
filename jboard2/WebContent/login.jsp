<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<link rel="stylesheet" href="/jboard2/css/style.css" />
		<script>
			var result = "${result}";
			
			if(result == 'fail'){
				alert('아이디와 비밀번호를 다시 확인하시기 바랍니다.');
			}
		
		</script>
		
	</head>
	<body>
		<div id="member">
			<section class="login">		
				<form action="/jboard2/member/login.do" method="post">
					<table>
						<tr>
							<td><img src="/jboard2/img/login_ico_id.png" alt="아이디" /></td>
							<td><input type="text" name="id" required placeholder="아이디 입력" autocomplete="off" /></td>
						</tr>
						<tr>
							<td><img src="/jboard2/img/login_ico_pw.png" alt="비밀번호" /></td>
							<td><input type="password" name="pw" required placeholder="비밀번호 입력" /></td>
						</tr>
					</table>
					<input type="submit" class="btnLogin" value="로그인" />
				</form>			
				
				<div class="info">
					<h3>회원로그인 안내</h3>
					<p>아직 회원이 아니시면 회원으로 가입하세요.</p>
					<div><a href="/jboard2/member/terms.do">회원가입</a></div>
				</div>			
			</section>
		</div>
	
	</body>
</html>