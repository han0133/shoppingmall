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
	h1,#center{
		text-align: center;
	}
	footer {
      background-color: #ffb3b3;
      padding: 25px;
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
 
  //유효성 검사
  $(document).ready(function(){		
		
		
		//이름 유효성
		$('#sellerName').blur(function(){
			if( $('#sellerName').val().length ==''){
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
		$('#sellerAddBtn').click(function(){
			
			//아이디 중복체크 여부 검사 
			if ($('#sellerName').val() == ""){
				alert("이름을 입력해주세요.");
			}else if ($('#sellerAddr').val() == ""){
				alert("주소를입력해주세요.");
			}else if ($('#sellerPhone2').val() == ""){
				alert("전화번호를 입력 해주세요.");
			}else if ($('#sellerPhone3').val() == ""){
				alert("전화번호를 입력 해주세요.");
			}else{
				$('#addForm').submit();
			}
		});
});
</script>

</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 회원가입 폼 (아이디, 비번, 전화번호, 주소, 이메일)  -->
	<form id="addForm" action="/AddSellerController" method="post">
	<h1>판매자 등록</h1>
	<br/>
	<div id="formDiv">
		<div>
			<label for="sellerName">이름 : </label>
			<input type="text" name="sellerName" id="sellerName"/>
		</div>
		<div>
			<span id="nameHelper"></span>
		</div>
		<div>
			<label for="sellerAddr">주소 : </label>
			<input type="text" name="sellerAddr" id="sellerAddr"/>
		</div>
		<div>
			<span id="addrHelper"></span>
		</div>		
		<div>
			<label for="sellerPhone">전화번호 : </label>
			<select name="sellerPhone" style="width: 50px">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
				</select>-	
			<input type="text" name="sellerPhone2" id="sellerPhone2" size=4 maxlength=4/>-
			<input type="text" name="sellerPhone3" id="sellerPhone3" size=4 maxlength=4/>
		</div>
		<div>
			<span id="phoneHelper"></span>		
		</div>
		
		<br/>
		</div>
		<div id="center">
			<input type="button" id="sellerAddBtn" class="btn btn-default" value="판매자등록" />
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