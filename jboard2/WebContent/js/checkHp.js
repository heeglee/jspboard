$(function(){
				
	// 아이디를 입력하고 포커스가 빠질때 중복체크 요청
	$('input[name=hp]').focusout(function(){
		
		var tag = $(this);		
		var param = '?check=hp&value='+tag.val();
		
		$.ajax({
			url: '/jboard2/member/checkUser.do'+param,
			type: 'get',
			dataType: 'json',
			success: function( data ){							
				
				if(data.result == 1){
					$('.resultHp').css('color', 'red').text('이미 사용중인 휴대폰번호 입니다.');
					tag.focus();
				}else{
					$('.resultHp').css('color', 'green').text('사용 가능한 휴대폰번호 입니다.');
				}							
			}						
		});					
	});
	
	
});