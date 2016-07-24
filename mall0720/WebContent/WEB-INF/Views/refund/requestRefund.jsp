<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	form{
		text-align: center;
	}
</style>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 환불 요청 form입니다 -->
<form action="/RefundController" method="post">
	<h1>환불 사유</h1>
		<input type="text" name="refundReason" style="width: 400px; margin-left: 35%;" class="form-control"/>
		<input type="hidden" value="${ordersNo}" name="ordersNo"/>
		<input type="hidden" value="${refundMoney}" name="refundMoney"/>
		<br/>
		<button class="btn btn-default">신청하기</button>
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