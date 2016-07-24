<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
	h1{text-align: center;}
</style>
<title>Index</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
	footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	#btn{
	margin-left:15%;
	margin-right:15%;
	text-align: right;}
	#paging{
	text-align: center;}
</style>
<script>
	$(document).ready(function(){
		$('#addSeller').click(function(){
			$(location).attr('href', "/AddSellerController");
		});
	});
</script>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
	<div id="main">
		<h1>판매자 리스트</h1>
		<hr/>
		<c:forEach var="list" items="${map.list}">
			<div class="row">
				<div class="col-sm-2"></div>
			    <div class="col-sm-2"><p>판매자번호 : ${list.sellerNo}</p></div>
			    <div class="col-sm-2"><p>판매자 :<a href="/SellerItemListController?sellerNo=${list.sellerNo}">${list.sellerName}</a></p></div>
			    <div class="col-sm-2"><p>판매자주소 :${list.sellerAddr}</p></div>
			    <div class="col-sm-2"><p>판매자연락처 :${list.sellerPhone}</p></div>
			</div>
		</c:forEach>
		<br/>
		<br/>
		<div id="paging">
			<a href="/SellerListController?nowPage=1"> [◀◀] </a>
				
				<c:if test="${map.pageHelper.movePage-limitLink > map.pageHelper.limitLink}">
					<a href="/SellerListController?nowPage=${map.pageHelper.movePage-map.pageHelper.limitLink}">[◀]</a>
				</c:if>
					
				<c:if test="${map.pageHelper.movePage <= map.pageHelper.limitLink}">
					[◀]
				</c:if>
				<c:forEach var="link" items="${map.pageHelper.linkPages}">
					<a href="/SellerListController?nowPage=${link}">[${link}]</a>
				</c:forEach>
				
				<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink < map.pageHelper.totalPage}">			
					<a href="/SellerListController?nowPage=${map.pageHelper.movePage+map.pageHelper.limitLink}">[▶]</a>
				</c:if>
				<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink > map.pageHelper.totalPage}">
					[▶]
				</c:if>
				
				<a href="/SellerListController?nowPage=${map.pageHelper.totalPage}"> [▶▶] </a>
		</div>
		<c:if test="">
			<div id="btn">
				<input type="button" id="addSeller" class="btn btn-default" value="판매자 등록">
			</div>
		</c:if>
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