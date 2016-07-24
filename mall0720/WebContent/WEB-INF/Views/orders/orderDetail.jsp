<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문 상세 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	div.groupbox{
		width: 700px;		
		height: auto;				
		margin: 2em 0 1em 0;	
		border: 2px solid #ffb3b3;		
		padding: 0 8px;	
		margin-left: 25%;
		margin-right: 25%;
	}	
	
	#orderDetailMap{
		padding: 5%;
	}
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 주문상세를 보여줍니다 -->
<div class="groupbox">
	<div id="orderDetailMap">
	<h1><span>주문상세</span></h1>
		<c:set var="list" value="${orderDetailMap}"/>
		상품명: ${list.itemName} <br/>
		경작지: ${list.itemOrigin} <br/>
		판매자 번호: ${list.sellerNo} <br/>
		결제수단: ${list.sellerNo} <br/>
		판매자 번호: ${list.PaymentMethod} <br/>
		<c:choose>
			<c:when test="${list.deliveryDate eq null}">
				배송상태: 배송대기중 <br/>
			</c:when>
			<c:otherwise>
				배송날짜: ${list.deliveryDate} <br/>
			</c:otherwise>
		</c:choose>
		
		수취인 이름: ${list.recipientName} <br/>
		수취인 번호: ${list.recipientPhone} <br/>
		수취인 주소: ${list.recipientAddr} <br/>
		<c:choose>
			<c:when test="${list.recipientConfirmDate eq 'N'}">
				상태: 미수령
			</c:when>
			<c:otherwise>
				수령일: ${list.recipientConfirmDate} <br/>
			</c:otherwise>
		</c:choose>
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