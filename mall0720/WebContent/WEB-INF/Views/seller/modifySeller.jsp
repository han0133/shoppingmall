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
	$('#sellerAddr').blur(function(){
		if( $('#sellerAddr').val().length < 6 ){
			if( $('#addrHelper').hide() ){
				$('#addrHelper').show();
			}				
			$('#addrHelper').css('color','red');
			$('#addrHelper').text('주소 길이는 6글자 이상입니다.');
		}else{
			$('#addrHelper').hide();
		}
	});
	//패스워드확인 일치 체크
	$('#sellerPhone').blur(function(){
		if( $('#sellerPhone').val().length < 10 ){
			if( $('#phoneHelper').hide() ){
				$('#phoneHelper').show();
			}				
			$('#phoneHelper').css('color','red');
			$('#phoneHelper').text('전화번호 길이는 10글자 이상입니다.');
		}else{
			$('#phoneHelper').hide();
		}
	});
	$('#sellerModiBtn').click(function(){
		 
		if ($('#sellerAddr').val() == ""){
			alert("주소를입력해주세요.");
		}else if ($('#sellerPhone2').val() == ""){
			alert("전화번호를 입력 해주세요.");
		}else if ($('#sellerPhone3').val() == ""){
			alert("전화번호를 입력 해주세요.");
		}else{
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


<form id="modiForm" action="/ModifySellerController" method="post">
	<h1>판매자 수정</h1>
	<br/>
	<div id="formDiv">
		<input type="hidden" name="sellerNo" value="${seller.sellerNo}">
		<div>
			<label for="sellerName">이름  </label>
			<input type="text" name="sellerName" id="sellerName" class="form-control"  value="${seller.sellerName}" readonly="readonly"/>
		</div>
		<div>
			<label for="sellerAddr">주소  </label>
			<input type="text" name="sellerAddr" id="sellerAddr" class="form-control" value="${seller.sellerAddr}"/>
		</div>
		<div>
			<span id="addrHelper"></span>		
		</div>
		<div>
			<label for="sellerPhone">전화번호  </label>
			<input type="text" name="sellerPhone" id="sellerPhone" class="form-control" size=4 maxlength=11 value="${seller.sellerPhone}"/>
		</div>
		<div>
			<span id="phoneHelper"></span>		
		</div>
	
		<br/>
		</div>
		<div id="center">
			<input type="button" id="sellerModiBtn" class="btn btn-default" value="판매자 정보 수정" />
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