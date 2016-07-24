<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>주문 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //우편번호 찾기 다음 API 시작
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample4_roadAddress').value = fullRoadAddr;
                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
 	//우편번호 찾기 다음 API 끝
  
	//기존 정보를 불러오는 함수입니다
	var getMemberInfo = function() {
	 $.ajax({
		 type		: "POST"
		,url		: "/GetMemberInfoForOrderController"
		,data		: { 'memberId'	: $('#memberId').val() }
	  	,dataType	: "JSON"
	  	,success	: function(data){
	  	
	  	// 서버에서 받아온 값을 view의 input text에 세팅합니다
	  		$('input[name=recipientName]').attr('value',data.memberName);
	  		$('input[name=recipientPhone]').attr('value',data.memberPhone);
	  		$('input[name=recipientPost]').attr('value',data.memberPost);
	  		$('input[name=recipientRoadAddr]').attr('value',data.memberRoadAddr);
	  		$('input[name=recipientParcelAddr]').attr('value',data.memberParcleAddr);
	  	}
	 });
	};
  
	$(document).ready(function(){
		$('input:radio[name=orderInfo]').click(function(){
			//console.log('라디오 click');
			
			//기존 정보를 불러옵니다	  	
			if( $('input:radio[name=orderInfo]:checked').val() == "same"){
			//	console.log($('input:radio[name=orderInfo]:checked').val());
			//	console.log('기존 정보 불러오기');
				getMemberInfo();
			}
			//새로운 정보를 입력할 수 있도록 value를 공백으로 세팅합니다
			else if( $('input:radio[name=orderInfo]:checked').val() == "notSame" ) {
			//	console.log($('input:radio[name=orderInfo]:checked').val());
			//	console.log('기존 정보 지우기');
				$('input[name=recipientName]').attr('value',"");
				$('input[name=recipientPhone]').attr('value',"");
				$('input[name=recipientPost]').attr('value',"");
				$('input[name=recipientRoadAddr]').attr('value',"");
				$('input[name=recipientParcelAddr]').attr('value',"");
			} 
	  });
		
		//유효성 검사
		$('#orderBtn').click(function(){
			if( $('#recipientName').val() == ""){
				$('#nameHelper').css('color', 'red');
				$('#nameHelper').text('성명을 입력해주세요');
			}else if( $('#recipientPhone').val() == ""){
				$('#phoneHelper').css('color', 'red');
				$('#phoneHelper').text('번호를 입력해주세요');
			}else if( $('#recipientPost').val() == ""){
				$('#postHelper').css('color', 'red');
				$('#postHelper').text('우편번호를 입력해주세요');
			}else if( $('#sample4_roadAddress').val() == ""){
				$('#roadAddrHelper').css('color', 'red');
				$('#roadAddrHelper').text('주소를 입력해주세요');
			}else if( $('#sample4_jibunAddress').val() == ""){
				$('#roadAddrHelper').css('color', 'red');
				$('#roadAddrHelper').text('주소를 입력해주세요');
			}else if( $('#sample4_jibunAddress').val() == ""){
				$('#parcelAddrHelper').css('color', 'red');
				$('#parcelAddrHelper').text('주소를 입력해주세요');
			}else if($('input:radio[name=paymentMethod]:checked').length < 1){
				$('#paymentHelper').css('color', 'red');
				$('#paymentHelper').text('결제방식을 선택해주세요');
			}else{
				$('#orderForm').submit();
			}
			
		});
	});
  
  
</script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	#orderForm{
		width: 100%;
	}
	.row{
		padding-left: 18%;
	}
	#div1{
 		width: 60%; 
	}
	#div2{
 		width: 60%;
	}
	#button{
		text-align: center;
	}
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!-- 주문 form입니다 -->
	
<%-- <c:forEach>
		<!-- 상품 이미지 -->
		<!-- 상품명 -->
		<!-- 판매가  -->
		<!-- 수량  -->
		<!-- 상품별 합계 (개별 상품*수량) -->
	</c:forEach> --%>
	
	<!-- ajax실행을 위해 세션에 담긴 아이디를 받습니다 -->
	<input type="hidden" id="memberId" value="${memberId }"/>
	
	<form action="/OrderItemController" method="post" id="orderForm">
	<div class="row">
	<div class="col-sm-6" >	
		<div id="div1">
		<h1>주문form</h1>
		<!-- 카트에서 주문일 경우 cartNo를 받습니다 -->
		<c:forEach var="cart" items="${cartNo }">
			<input type="hidden" name="cartNo" value="${cart}"/>
		</c:forEach>
		
 	<!-- 주문하려는 상품의 itemNo와 수량을 모두 보냅니다 -->
		<c:forEach var="ordersCount" items="${ordersCount}">
			<input type="hidden" name="ordersCount" value="${ordersCount}" />
		</c:forEach>
		<c:forEach var="itemNoList" items="${itemNoList}">
			<input type="hidden" name="itemNoList" value="${itemNoList}" />
		</c:forEach>
		<c:forEach var="itemPrice" items="${itemPrice}">
			<input type="hidden" name="itemPrice" value="${itemPrice}" />
		</c:forEach>
		
		주문 정보가 본인 정보와 동일합니까? 
		<div>
			<input type="radio" name="orderInfo" value="same"/> 본인 정보와 동일
			<input type="radio" name="orderInfo" value="notSame" /> 새로운 주문 정보
		</div>
		
		<div>주문자 정보</div>
		<div>
			<label for="recipientName">성명: </label>
			<input type="text" id="recipientName" name="recipientName" class="form-control" />
			<span id="nameHelper"></span>
		</div>
		<div>
			<label for="recipientPhone">휴대전화: </label>
			<input type="text" id="recipientPhone" name="recipientPhone" class="form-control" placeholder="- 없이 번호만 입력하세요" class="form-control" />
			<span id="phoneHelper"></span>
		</div>
		<br/>
		</div>
	</div>
	<div class="col-sm-6" >
		<div id="div2">	
		<h2>배송지 정보</h2>
		<div>
			<label for="recipientPost">우편번호: </label>
			<input type="text" id="sample4_postcode" name="recipientPost" placeholder="우편번호" readonly="readonly" class="form-control" >
			<input type="button" onclick="sample4_execDaumPostcode()" class="btn btn-default" value="우편번호 찾기"><br>
			<span id="postHelper"></span>
		</div>
		<div>
			<label for="recipientAddr">도로명 주소: </label>
			<input type="text" id="sample4_roadAddress" name="recipientRoadAddr" placeholder="도로명주소" readonly="readonly" class="form-control" >
			<span id="roadAddrHelper"></span>
		</div>
		<div>
			<label for="recipientDetail">지번 주소: </label>
			<input type="text" id="sample4_jibunAddress" name="recipientParcelAddr" readonly="readonly" placeholder="지번주소" class="form-control" >
			<span id="guide" style="color:#999"></span>
			<span id="parcelAddrHelper"></span>
		</div>
		
		<div>결제 정보</div>
		<div>
			<label for="paymentPrice">결제금액: </label>
			<span name="paymentPrice"> <%-- ${총 결제금액 변수 } --%> </span>
			<input type="hidden" name="ordersTotalPrice" value="10000<%-- ${총 결제금액 변수 } --%>" />
		</div>
		<div>
			<label for="paymentMethod">결제방식 선택</label>
			<input type="radio" name="paymentMethod" value="payCard"  /> 카드결제
			<input type="radio" name="paymentMethod" value="payPhone"  /> 휴대폰결제
			<span id="paymentHelper"></span>
		</div>
		<input type="button" value="결제하기" id="orderBtn" class="btn btn-default" />
		<br/>
		</div>
		</div>
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