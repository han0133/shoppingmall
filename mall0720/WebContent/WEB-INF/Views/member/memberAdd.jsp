<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	h1,#center{
		text-align: center;
	}
	label{
		width:20%; 
		text-align: right;
	}
	span{
		margin-left: 21%; 
	}
	#addForm{
		background-color: rgba( 255, 179, 179, 0.5 );
		padding:3%;
		margin-left: 20%;
		margin-right:  20%;	
	}
	#formDiv{
		padding-left:20%;
	}
</style>
<script language="JavaScript" src="http://blogattach.naver.net/26b33a899fc7c21e30d2bc87bd59215cfdaa56b168/20160724_35_blogfile/nyn0133_1469344596069_127Duv_js/addressAPI.js">
 //유효성 검사
  $(document).ready(function(){		
		
		var id = null;
		
		//아이디 길이 체크
		$('#memberId').blur(function(){
			if( $('#memberId').val().length < 5 ){
				if( $('#idHelper').hide() ){
					$('#idHelper').show();
				}				
				$('#idHelper').css('color','red');
				$('#idHelper').text('아이디는 5글자 이상입니다.');
			}else{
				$('#idHelper').hide();
			}
		});
		
		//아이디 중복체크
		$('#repeatingCheck').click(function(){
			if( $('#memberId').val().length>5){
				$.ajax({
				      type		: "POST",
				      url		: "/JSONIDCheckController",
				      data		: { memberId	: $('#memberId').val() },
				      dataType	: "JSON",
				      success	: function(str) {
							console.log("성공");
							if(str.result=="true"){
								alert('사용할 수 있는 아이디입니다');
								id = "idOK";
							}else{ 
								alert('이미 존재하는 아이디입니다');
								id = "idNotOK";
								$('#memberId').val('');
							}
				      }
			  });
			}else{
				alert('아이디를 길이를 해주세요');
			}
		});
		
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
		$('#memberAddBtn').click(function(){
			console.log('id: '+id);
			$('#memberId').val().length < 5
			//아이디 중복체크 여부 검사 
			if ($('#memberName').val() == ""){
				alert("이름 입력해주세요.");
			}else if ($('#memberId').val().length < 5){
				alert("아이디 입력해주세요.");
			}else if ($('#memberPw').val().length < 5){
				alert("비밀번호를 입력해주세요.");
			}else if ($('#memberPwConfirm').length < 5){
				alert("비밀번호 확인을 입력해주세요.");
			}else if ($('#memberPw').val() != $('#memberPwConfirm').val()){
				alert("비밀번호가 일치하지 않습니다.");
			}else if ($('#memberPhone2').val().length < 4){
				alert("전화번호를 입력 해주세요.");
			}else if ($('#memberPhone3').val().length < 4){
				alert("전화번호를 입력 해주세요.");
			}else if($('#sample4_postcode').val().length < 6){
				alert("주소를 입력 해주세요.");
			}else if(id == "idNotOK" || id == null ){
					alert("아이디 중복확인을 해주세요");
			}else if( id == "idOK" ){
				$('#addForm').submit();
			}
		});
});
</script>

</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 회원가입 폼 (아이디, 비번, 전화번호, 주소, 이메일)  -->
	<form id="addForm" action="/JoinMemberController" method="post">
	<h1>회원가입</h1>
	<br/>
	<div id="formDiv">
		<div>
			<label for="memberName">이름 : </label>
			<input type="text" name="memberName" id="memberName"/>
		</div>
		<div>
			<span id="nameHelper"></span>
		</div>
		<div>
			<label for="memberId">아이디 : </label>
			<input type="text" name="memberId" id="memberId"/>
			<input type="button" class="btn btn-default" id="repeatingCheck" value="중복확인" />
		</div>
		<div>
			<span id="idHelper"></span>
		</div>
		<div>
			<label for="memberPw">비밀번호 : </label>
			<input type="password" name="memberPw" id="memberPw"/>
		</div>
		<div>
			<span id="pwHelper"></span>
		</div>		  
		<div>
			<label for="memberPwConfirm">비밀번호 확인 : </label>
			<input type="password" name="memberPwConfirm" id="memberPwConfirm"/>
		</div>
		
		<div>
			<span id="pwConfirmHelper"></span>
		</div>		
		<div>
			<label for="memberPhone">전화번호 : </label>
			<select name="memberPhone" style="width: 50px">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
				</select>-	
			<input type="text" name="memberPhone2" id="memberPhone2" size=4 maxlength=4/>-
			<input type="text" name="memberPhone3" id="memberPhone3" size=4 maxlength=4/>
		</div>
		<div>
			<span id="phoneHelper"></span>		
		</div>
		<div>
			<label for="memberPost">우편번호: </label>
			<input type="text" id="sample4_postcode" name="memberPost" readonly="readonly">
			<input type="button" onclick="sample4_execDaumPostcode()" class="btn btn-default" value="우편번호 찾기"/>
			<span id="memberPostHelper"></span>
		</div>
		<div>
			<label for="memberRoadAddr">도로명 주소: </label>
			<input type="text" id="sample4_roadAddress" name="memberRoadAddr" style="width: 300px" readonly="readonly"/>
		</div>
		<div>
			<label for="memberParcleAddr">지번 주소: </label>
			<input type="text" id="sample4_jibunAddress" name="memberParcleAddr" style="width: 300px"/>
			<span id="guide" style="color:#999"></span>
		</div>
		<br/>
		</div>
		<div id="center">
			<input type="button" id="memberAddBtn" class="btn btn-default" value="회원가입" />
			<input type="reset" class="btn btn-default"value="입력취소" />
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