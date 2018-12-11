$(document).ready(function() {

	var chk1 = $('input[name=chk1]');
	var chk2 = $('input[name=chk2]');

	$('.btnNext').click(function(e) {
		e.preventDefault();

		var rs1 = chk1.is(':checked');
		var rs2 = chk2.is(':checked');

		if (rs1 && rs2) {
			location.href = '/jboard2/member/regist.do';
		} else {
			alert('동의 체크를 하셔야 합니다.');
		}

	});

	$('.btnCancel').click(function(e) {
		e.preventDefault();

		if (confirm('정말 취소 하시겠습니까?')) {
			// 로그인 화면으로 이동
			location.href = "/jboard2/member/login.do";
		}
	});
});