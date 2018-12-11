$(function(){
				
	// 글쓰기 클릭할때 로그인여부 체크
	$('.btnWrite').click(function(){
		if(login){
			// a태그의 링크이동 O
			return true;						
		}else{
			alert('로그인을 해야 글을 작성할 수 있습니다.');
			// a태그의 링크이동 X
			return false;
		}									
	});
	
	// 글제목을 클릭해서 글보기로 들어갈때 로그인여부 체크
	$('.title').click(function(){
		if(login){
			return true;
		}else{
			alert('로그인을 해야 글을 열람하실 수 있습니다.');
			return false;
		}
	});
});