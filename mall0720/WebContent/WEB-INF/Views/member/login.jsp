<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	#loginForm,#header{
		padding-left:30%;
		width: 60%;
	}	
	 #find{
	 	padding-left:30%;
	 	text-align: right;
	 }
	 #right{
	 	text-align: right;
	 }
</style>
<script>
	$(document).ready(function() {
		
		$('#welcomeMsg').hide();
		
		if( $('#joined').val() == 'joined' ){
			$('#welcomeMsg').show();
		}
		
		$('#loginBtn').click(function() {
			console.log('로그인버튼 클릭');
			//아이디, 비밀번호 입력여부 검사
			if ($('#memberId').val() == "") {
				//console.log('아이디 미입력');
				alert('아이디를 입력하세요');
			} else if ($('#memberPw').val() == "") {
				//console.log('비밀번호 미입력');
				alert('비밀번호를 입력하세요');
			} else {
	            //0617박종무 아이디 비번있는지 확인후 아이디가 있다면 로그인 단계로, 일치하는 비번이랑 아이디가 없으면 정보가 다르다고확인 후 로그인페이지 유지
	            $.ajax({
	                  type      : "POST",
	                  url      : "/JSONIdPwCheckController",
	                  data      : { memberId   : $('#memberId').val() ,
	                            memberPw   : $('#memberPw').val()  
	                  },
	                  dataType   : "JSON",
	                  success   : function(str) {
	                     console.log("성공");
	                     if(str.result=="true"){
	                        $('#loginForm').attr('action',"/LoginController");
	                        $('#loginForm').attr('method',"post");
	                        $('#loginForm').submit();
	                     }else{ 
	                        alert('아이디 또는 비번이 맞지 않습니다.');
	                        $('#memberPw').val('');
	                        $('#memberId').val('');
	                     }
	                  }
	            });
	         }
		});
	});

</script>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 로그인 폼 -->
<div class="container">
	<div id="header">
		<div id="welcomeMsg"><h1>가입을 환영합니다<h1></h1></div>
		<h2>로그인</h2>
		<input type="hidden" id="joined" value="${joined}"/>
	</div>
	
	<form role="form" action="/JoinMemberController" method="get" id="loginForm">
		<div class="form-group">
			<label for="memberId">아이디 : </label>
			<input type="text" class="form-control" id="memberId" name ="memberId" placeholder="Enter ID">
		</div>
		<div class="form-group">
			<label for="memberPw">비밀번호:</label>
			<input type="password" class="form-control" id="memberPw" name = "memberPw" placeholder="Enter password">
		</div>
		<div id="find">
			<a href="/FindController">아이디/비밀번호 찾기</a>
		</div>
		<div id="right">
			<input type="button" class="btn btn-default" id="loginBtn" value="로그인"/>
			<input type="submit" class="btn btn-default" id="Join" value="회원가입" />
		</div>
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
</div>
<br/>
</body>
</html>