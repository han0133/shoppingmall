<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#confirm').click(function(){
	
			var confirmMsg = confirm('구매를 확정하시겠습니까?');
			
			if(!confirmMsg){
				console.log('취소');
				$('#confirm').prop('href','#');
			}/* else{
				console.log('확인');
				$('#confirm').prop('href','#');
			} */
		});
	});
</script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	table{
		width: 80%;
		text-align: center;
		margin-left: 10%;
		margin-right: 10%;	
	}
	h2{
		width: 80%;
		margin-left: 10%;
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
<!-- 요청 중인 주문 리스트 -->
<h2>요청 중인 주문 리스트</h2>
<table>
	<tr id="th">
		<td>상품</td>
		<td>상품 이름</td>
		<td>장바구니 주문그룹</td>
		<td>상품 가격</td>
		<td>주문 수량</td>
		<td>주문 날짜</td>
		<td>비고</td>
	</tr>
	<c:forEach var="orderList" items="${list}">
		<tr>
			<td style="width: 70px; height: 70px; overflow: hidden">
				<a href="/itemDetailController?itemNo=${orderList.itemNo}">
				<img src="${orderList.itemImage}" id="img"/></a>
			</td>
			<td>${orderList.itemName}<input type="hidden" name="itemName" value="${orderList.itemName}" /></td>
			<td>${orderList.ordersGroup}<input type="hidden" name="ordersGroup" value="${orderList.ordersGroup}" /></td>
			<td>${orderList.ordersTotalPrice}<input type="hidden" name="ordersTotalPrice" value="${orderList.ordersTotalPrice}" /></td>
			<td>${orderList.ordersCount}<input type="hidden" name="ordersCount" value="${orderList.ordersCount}" /></td>
			<td>${orderList.ordersDate}<input type="hidden" name="ordersDate" value="${orderList.ordersDate}" /></td>
			<td>
				<c:if test="${orderList.ordersRefund == 'N'}"> 
					<a href="/RefundController?ordersNo=${orderList.ordersNo}&refundMoney=${orderList.ordersTotalPrice}">환불요청</a>
				</c:if>
				
				<c:if test="${ (orderList.ordersRefund == 'Y') && (orderList.refundConfirm == 'N') }">
					<div>환불 진행 중</div>
				</c:if>
				<c:if test="${ (orderList.ordersRefund == 'Y') && (orderList.refundConfirm == 'Y') }">
					<div>환불 완료</div>
				</c:if>
				
				<c:if test="${orderList.deliveryConfirm == 'Y'}">
					<div>
						<a id="confirm" href="/OrderConfirmController?ordersNo=${orderList.ordersNo}" >구매확정</a>
					</div>
				</c:if>
			</td>
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