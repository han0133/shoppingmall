<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#searchBtn').click(function(){
		$.ajax({
		    type      : "POST",
		    url      : "/LevelModifyController",
		    data      : { memberId   : $('#idSerch').val()},
		    dataType   : "JSON",
		    success   : function(str) {
		       console.log("성공");
		       if(str.result=="true"){
		          $('#levelView').attr('value', 권한);
		          $('#idSearchForm').submit();
		       }else{ 
		          alert('검색 결과가 없습니다.');
		          $('#searchText').attr('value','검색결과가 없습니다')
		       }
		    }
		});
	});
});

</script>

</head>
<body>
	<h1>권한 변경 페이지</h1>
	<div>
		<label>아이디 찾기 </label> 
		<input type="text" name="idSearch" id="searchText">
		<input type="button" value="찾기" id="searchBtn">
	</div>
	<div>
		<label>등급</label>
		<input type="text" id="levelView" readonly="readonly">
		<input type="button" value="권한 변경"> 	
	</div>
</body>
</html>