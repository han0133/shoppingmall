<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fruitMall: 마이페이지</title>
<style>
	footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	img{
		padding: 2%;
		width: 250px;
	}
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 안소영 2016-07-13 -->
<!-- 서지연 2016-07-15 -->

		<div style="text-align: center;">
		<!-- 장바구니  -->
			<a href="/MyCartListController"><img src="https://lh4.googleusercontent.com/-MiM6SOCLR-s/V4g0G7O1YOI/AAAAAAAAAD8/rusknNr8fQE9GQ6vLLswi7DQPnNQcF6rgCL0B/w367-h325-no/cart.png"></a>
		<!-- 주문확인  -->
			<a href="/OrderListPresentController"><img src="https://lh6.googleusercontent.com/-aDyCb7pnSrA/V4g0HHnpJZI/AAAAAAAAAD8/oFJ_BO7u9u0aRAs1V4YH0w9mhQMTmwW1ACL0B/w367-h325-no/order.png"></a>
		<!-- 구매내역  -->
			<a href="/OrderListPastController"><img src="https://lh6.googleusercontent.com/-Njxi54reARc/V4g0G_Zo5pI/AAAAAAAAAD8/kH5dWZFzk54eNSlzPjrFen1pIpC3aaeGgCL0B/w367-h325-no/buy.png"></a>
		<!-- 회원정보수정  -->
			<a href="/myInfoModifyController"><img src="https://lh4.googleusercontent.com/-WS_mpYhDceo/V4g0GzSNDwI/AAAAAAAAAD8/pWT37qiYmUwV0AfNhS3EVzs8Y5nAPaZowCL0B/w367-h325-no/mypage.png"></a>
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