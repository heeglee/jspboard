$(function(){
	
	// form전송이 발생할때(submit 버튼 클릭했을때)
	$('.register > form').submit(function(){
		
		// 비밀번호 일치여부 검사
		var pw1 = $('input[name=pw1]').val();
		var pw2 = $('input[name=pw2]').val();
		
		if(pw1 != pw2){
			alert('비밀번호가 일치하지 않습니다.');
			return false; // 폼전송이 취소된다.
		}		
		
		// 이름 한글 검사
		var name = $('input[name=name]').val();
		var regName  = /^[가-힣]{2,4}$/;
		
		if( !regName.test(name) ){
			alert('이름을 정확하게 입력하십시요.\n 한글로만 입력하십시요.');
			return false; // 폼전송 취소
		}
		
		return true; // 최종적으로 폼전송이 실행	
	});
	
	
	
	
	
});