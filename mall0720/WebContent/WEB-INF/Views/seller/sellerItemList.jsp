<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style type="text/css">
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
   footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
   #th{
      border-bottom : 1px solid;
      border-bottom-color:#ffb3b3;
      border-bottom-width: 80%;
   }
   #paging{
   text-align: center;}
   #img{
       width:100%;
       max-width: auto; 
       max-height: 70px;
    }
    #modBtn{
    	margin-left: 15%;
    	margin-right: 15%;
    	text-align: right;
    }
</style>
<script>
	$(document).ready(function(){
		$('#modifyBtn').click(function(){
			$(location).attr('href', "/ModifySellerController");
		});
	});
</script>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<!-- 요청 중인 주문 리스트 -->
<h2>판매중인 상품 리스트</h2>
	<table>
	   <tr id="th">
	      <td>상품</td>
	      <td>상품 이름</td>
	      <td>카테고리</td>
	      <td>상품 가격</td>
	      <td>남은 수량</td>
	      <td>수확 날짜</td>
	   </tr>
	   <c:forEach var="list" items="${map.list}">
	      <tr>
	         <td style="width: 70px; height: 70px; overflow: hidden">
	            <a href="/itemDetailController?itemNo=${list.itemNo}">
	            <img src="${list.itemImage}" id="img"/></a>
	         </td>
	         <td>${list.itemName}</td>
	         <td>${list.itemCategory}</td>
	         <td>${list.itemPrice}</td>
	         <td>${list.itemStock}</td>
	         <td>${list.itemHarvest}</td>
	   </c:forEach>
	</table>
	<br/>
	<br/>
	<div id="paging">
		<a href="/SellerItemListController?nowPage=1&sellerNo=${map.sellerNo}"> [◀◀] </a>
			
			<c:if test="${map.pageHelper.movePage-limitLink > map.pageHelper.limitLink}">
				<a href="/SellerItemListController?nowPage=${map.pageHelper.movePage-map.pageHelper.limitLink}&sellerNo=${map.sellerNo}">[◀]</a>
			</c:if>
				
			<c:if test="${map.pageHelper.movePage <= map.pageHelper.limitLink}">
				[◀]
			</c:if>
			<c:forEach var="link" items="${map.pageHelper.linkPages}">
				<a href="/SellerItemListController?nowPage=${link}&sellerNo=${map.sellerNo}">[${link}]</a>
			</c:forEach>
			
			<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink < map.pageHelper.totalPage}">			
				<a href="/SellerItemListController?nowPage=${map.pageHelper.movePage+map.pageHelper.limitLink}&sellerNo=${map.sellerNo}">[▶]</a>
			</c:if>
			<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink > map.pageHelper.totalPage}">
				[▶]
			</c:if>
			
			<a href="/SellerItemListController?nowPage=${map.pageHelper.totalPage}&sellerNo=${map.sellerNo}"> [▶▶] </a>
	</div>
	<form action="/ModifySellerController">
		<div id="modBtn">
			<input type="hidden" name="sellerNo" value="${map.sellerNo}" />
			<input type="button" id="modifyBtn" class="btn btn-default" value="판매자 수정">
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