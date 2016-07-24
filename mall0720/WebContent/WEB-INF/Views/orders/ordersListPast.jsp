<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	table{
		width: 70%;
		text-align: center;
		margin-left: 16%;
		margin-right: 10%;	
	}
	h2{
		width: 70%;
		margin-left: 16%;
		margin-right: 10%;
	}
	#th{
		border-bottom : 1px solid;
		border-bottom-color:#ffb3b3;
		border-bottom-width: 80%;
	}
	#img{
 		width:100%;
 		max-width: auto; 
 		max-height: 70px;
    }
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 과거 주문 내역 -->
<h2>과거 주문 내역 </h2>
<table>
	<tr id="th">
		<td>주문날짜</td>
		<td>주문 정보</td>
		<td>상품</td>
			<!-- 아이템이름을 클릭하면 해당 아이템 detail로 이동합니다 -->
		<td>상품 이름</td>
		<td>상품 가격</td>
		<td>수량</td>
	</tr>
	<c:forEach var="orderList" items="${list}">
		<tr>
			<td>${orderList.ordersDate}<input type="hidden" name="ordersDate" value="${orderList.ordersDate}" /></td>
			<td><a href="/ShowOrderDetailController?ordersNo=${orderList.ordersNo}">상세보기</a></td>
			<td style="width: 70px; height: 70px; overflow: hidden">
				<a href="/itemDetailController?itemNo=${orderList.itemNo}">
				<img src="${orderList.itemImage}" id="img"/></a>
			</td>
			<td><a href="/itemDetailController?itemNo=${orderList.itemNo}">
			${orderList.itemName}<input type="hidden" name="itemName" value="${orderList.itemName}" /></a></td>
			<td>${orderList.ordersTotalPrice}<input type="hidden" name="ordersCount" value="${orderList.ordersTotalPrice}" /></td>
			<td>${orderList.ordersCount}<input type="hidden" name="ordersDate" value="${orderList.ordersCount}" /></td>
	</c:forEach>
</table>
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