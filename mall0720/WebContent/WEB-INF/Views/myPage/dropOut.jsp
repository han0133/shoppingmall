<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	h1,#dropOutForm{
		padding-left:30%;
		width: 60%;
		color: gray;
	}
	#pw,#outCheckingText,#autoOutCheck{
		font-size: large;
		color: gray;
	}
	#btn{
		text-align: right;
	}
</style>
<script>
	$(document).ready(function(){
		var checkingText = Math.floor(Math.random() * 9000);
		$('#outCheckingText').text(checkingText);
		$('#dropOutBtn').click(function(){
			console.log('클릭');
			if($('#memberPw').val() == ""){
				console.log('if1번');
				$('#pwHelper').css('color','red');
				$('#pwHelper').text('필수 입력 정보 입니다');
			}else if($('#memberPw').val().length < 4){
				console.log('if2번');
				$('#pwHelper').css('color','red');
				$('#pwHelper').text('다시 확인해주세요');
			}else if($('#outCheckingText').text() != $('#outChecking').val()){
				console.log('if3번');
				$('#pwHelper').text('');
				$('#checkingHelper').css('color','red');
				$('#checkingHelper').text('다시 확인해 주세요');
			}else{
				console.log('if4번');
				$('#dropOutForm').submit();
			}
		});
		$('#memberPw').focus(function(){
			$('#pwHelper').text('');
		});
		$('#outChecking').focus(function(){
			$('#checkingHelper').text('');
		});
		
	});
</script>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<h1>회원 탈퇴</h1>
<br/>
<form id="dropOutForm" action="/DropOutController" method="POST">
	<div id="pw">비밀번호 체크</div>
	<div><input type="password" id="memberPw" class="form-control" name="memberPw"></div>
	<div><span id="pwHelper"></span></div>
	<br/>
	<div id="autoOutCheck">자동탈퇴방지 &nbsp;&nbsp; <span id="outCheckingText"></span></div>
	<div><input type="text" id="outChecking" class="form-control"></div>
	<div><span id="checkingHelper"></span></div>
	<br/>
	<div id="btn"><input type="button" id="dropOutBtn" class="btn btn-default" value="삭제"></div>
</form>
<br/>
<br/>
<footer class="container-fluid text-center">
	<address>
			<p><strong>(주)FruitMall</strong> ksmart <span><strong>개인정보보호담당자</strong> 서지연</span></p>
			<p><strong>사업자등록번호</strong> 010-9201-4268 <span><strong>통신판매업신고</strong> 2015-기린대로-0589</span></p>
			<p><strong>주소</strong> 전주시 덕진구 송천동 주공아파트 130동 703호</p>
			<p><span><strong>TEL/FAX</strong> 010-9201-4268</span></p>
	</address>
	<div>(주)FruitMall은 신선한 과일을 공급하고 있습니다. 유기농 제품만을 취급합니다.</div>
	<div>Copyright ⓒ Seojiyeon Co, Ltd. All rights reserved</div>
</footer>
</body>
</html>