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
<script>

$(document).ready(function(){		

	//패스워드 길이 체크
	$('#memberPw').blur(function(){
		if( $('#memberPw').val().length < 6 ){
			if( $('#pwHelper').hide() ){
				$('#pwHelper').show();
			}				
			$('#pwHelper').css('color','red');
			$('#pwHelper').text('패스워드 길이는 6글자 이상입니다.');
		}else{
			$('#pwHelper').hide();
		}
	});
	//패스워드확인 일치 체크
	$('#memberPwConfirm').blur(function(){
		if( $('#memberPwConfirm').val() != $('#memberPw').val() ){
			$('#pwConfirmHelper').text('패스워드가 일치하지 않습니다.');
			$('#pwConfirmHelper').css('color','red');
		}else{
			$('#pwConfirmHelper').hide();
		}
	});
	//이름 유효성
	$('#memberName').blur(function(){
		if( $('#memberName').val().length ==''){
			if( $('#nameHelper').hide() ){
				$('#nameHelper').show();
			}				
			$('#nameHelper').css('color','red');
			$('#nameHelper').text('이름을 입력해주세요.');
		}else{
			$('#nameHelper').hide();
		}
	});
	

	// 폼 제출
	$('#memberModiBtn').click(function(){
		//여부 검사 
		if ($('#memberName').val() == ""){
			alert("이름 입력해주세요.");
		}else if ($('#memberPw').val() == ""){
			alert("비밀번호를 입력해주세요.");
		}else if ($('#memberPwConfirm').val() == ""){
			alert("비밀번호 확인을 입력해주세요.");
		}else if ($('#memberPw').val() != $('#memberPwConfirm').val()){
			alert("비밀번호가 일치하지 않습니다.");
		}else if ($('#memberPhone2').val() == ""){
			alert("전화번호를 입력 해주세요.");
		}else if ($('#memberPhone3').val() == ""){
			alert("전화번호를 입력 해주세요.");
		}else if($('#sample4_postcode').val() == ""){
			alert("주소를 입력 해주세요.");
		}else {
			$('#modiForm').submit();
		}
	});
});

</script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	#modiForm{
		padding-left:30%;
		width: 60%;
	}	
	#center{
		text-align: center;
	}
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>


<form id="modiForm" action="/myInfoModifyController" method="post">
	<h1>회원정보 수정</h1>
	<br/>
	<div id="formDiv">
		<div>
			<label for="memberName">이름  </label>
			<input type="text" name="memberName" id="memberName" class="form-control"  value="${memberRequest.memberName }"/>
		</div>
		<div>
			<label for="memberId">아이디  </label>
			<input type="text" name="memberId" id="memberId" class="form-control" value="${memberRequest.memberId }	" readonly="readonly"/>
		</div>
		<div>
			<span id="idHelper"></span>
		</div>
		<div>
			<label for="memberPw">비밀번호  </label>
			<input type="password" name="memberPw" class="form-control"  id="memberPw"/>
		</div>
		<div>
			<span id="pwHelper"></span>
		</div>		  
		<div>
			<label for="memberPwConfirm">비밀번호 확인  </label>
			<input type="password" name="memberPwConfirm" class="form-control" id="memberPwConfirm"/>
		</div>
		
		<div>
			<span id="pwConfirmHelper"></span>
		</div>		
		<div>
			<label for="memberPhone">전화번호  </label>
			<input type="text" name="memberPhone" id="memberPhone" class="form-control" size=4 maxlength=4 value="${memberRequest.memberPhone }"/>
<%-- 			<input type="text" name="memberPhone2" id="memberPhone2" size=4 maxlength=4 value="${memberRequest.memberPhone2 }"/>- --%>
<%-- 			<input type="text" name="memberPhone3" id="memberPhone3" size=4 maxlength=4 value="${memberRequest.memberPhone3 }"/> --%>
		</div>
		<div>
			<span id="phoneHelper"></span>		
		</div>
		<div>
			<label for="memberPost">우편번호 </label>
			<input type="button" onclick="sample4_execDaumPostcode()" class="btn btn-default" value="우편번호 찾기"/>
			<input type="text" id="sample4_postcode" name="memberPost" class="form-control"  readonly="readonly" value="${memberRequest.memberPost}">
			<span id="memberPostHelper"></span>
		</div>
		<div>
			<label for="memberRoadAddr">도로명 주소 </label>
			<input type="text" id="sample4_roadAddress" name="memberRoadAddr" class="form-control"readonly="readonly" value="${memberRequest.memberParcleAddr}"/>
		</div>
		<div>
			<label for="memberParcleAddr">지번 주소 </label>
			<input type="text" id="sample4_jibunAddress" name="memberParcleAddr" class="form-control"  readonly="readonly" value="${memberRequest.memberRoadAddr }"/>
			<span id="guide" style="color:#999"></span>
		</div>
		<br/>
		</div>
		<div id="center">
			<input type="button" id="memberModiBtn" class="btn btn-default" value="회원정보 수정" />
			<a href="/MyPageController" ><input type="reset" class="btn btn-default" value="취소" /></a>
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
</body>
</html>