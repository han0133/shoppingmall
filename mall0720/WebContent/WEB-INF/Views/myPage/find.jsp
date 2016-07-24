<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fruitMall: 아이디 비밀번호 찾기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	.row{
		padding-left: 15%;
	}
 	#idFindForm{ 
		padding-left:10%;
 		width: 60%; 
 	}	
 	 #pwFindForm{
 		 padding-right:10%;
 		width: 60%;
 	 }
 	.right{
 		 text-align: right;
 	}

</style>
<script>
	$(document).ready(function() {
		
		//아이디 찾기 유효성 검사
		$('#idFindBtn').click(function() {
			console.log('아이디 찾기 버튼 클릭');
			//이름, 핸드폰 번호 입력 여부 검사
			if ($('#memberNameForId').val() == "" || $('#memberPhoneForId').val() == "") {
				alert('빈 칸을 채워주세요.');
			} else {
				$('#idFindForm').submit();
			}
		});
		 
		//비밀번호 찾기 유효성 검사
		$('#pwFindBtn').click(function() {
			console.log('비밀번호 찾기 버튼 클릭');
			//아이디, 핸드폰 번호 입력 여부 검사
			if ($('#memberIdForPw').val() == "") {
				alert('아이디를 입력하세요');
			} else if ($('#memberPhoneForPw').val() == "") {
				alert('전화번호를 입력하세요');
			} else {
				$('#pwFindForm').submit();
			}
		}); 
	});

</script>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<br/>
<div class="row">
	<div class="col-sm-6">
		<!-- post방식은 아이디 찾는 프로그램을 실행합니다 -->
		<form id="idFindForm" action="/FindActionController" method="post">
		<h3>아이디 찾기</h3>
			<div>
				이름: <input type="text" id="memberNameForId" class="form-control" name="memberName"/>
			</div>
			<div>
				핸드폰 번호: <input type="text" id="memberPhoneForId" class="form-control" placeholder="- 를 제외한 번호만 입력해주세요" name="memberPhone" />
			</div>
			<br/>
			<div class="right">
				<input type="button" id="idFindBtn" class="btn btn-default" value="아이디 찾기" />
			</div>
		</form>
	</div>
	<div class="col-sm-6">
		<!-- get방식은 비밀번호를 찾는 프로그램을 실행합니다 -->
		<form id="pwFindForm" action="/FindActionController" method="get">
		<h3>비밀번호 찾기</h3>
			<div>
				아이디: <input type="text" id="memberIdForPw"  class="form-control" name="memberId"/>
			</div>
			<div>
				핸드폰 번호: <input type="text" id="memberPhoneForPw" class="form-control" placeholder="- 를 제외한 번호만 입력해주세요" name="memberPhone"/>
				<span id="helperForPw"></span>
			</div>
			<br/>
			<div class="right">
				<input type="button" id="pwFindBtn" class="btn btn-default" value="비밀번호  찾기" />
			</div>
		</form>
	</div>
</div>
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