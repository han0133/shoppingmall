<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>itemModify</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		$('#modifyItemImage').click(function(){
			$('#itemImage').attr('type','file');
			$('#modifyItemImage').attr('type','hidden');
		});
		$('#modifyItem').click(function(){
			$('#itemForm').attr('action','/ItemModfiyController');
			$('#itemForm').submit();
		});
		$('#deleteItem').click(function(){
			$('#itemForm').attr('action','/ItemDeleteController');
			$('#itemForm').attr('enctype','');
			$('#itemOut').val('Y');
			$('#itemForm').submit();
		});
	});
</script>
<style>
	footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	div{
		text-align: center;	
	}
	input{
		text-align: center;	
		border-color: #ffe6e6;
	}
	table{
		width: 100%;
		text-align: center;
	
	}
	#th{
		background-color : #ffe6e6;
		text-decoration: #ffffff;
	}
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
	<form id="itemForm" action="" method="POST" enctype="multipart/form-data">
		<div><label>no :</label>${itemDetail.itemNo}</div>	
		<div><h2>${itemDetail.itemName}</h2></div>
		<div><input type="image" id="itemImage" name="itemImage" src="file:${itemDetail.itemImage}">
		<input type="button" id="modifyItemImage" class="btn btn-default" value="이미지수정"></div>
		<input type="hidden" name="itemImageModify" value="${itemDetail.itemImage}">
		<input type="hidden" name="itemNo" value="${itemDetail.itemNo}">
		<input type="hidden" name="itemName" value="${itemDetail.itemName}">
		<input type="hidden" name="sellerNo" value="${itemDetail.sellerNo}">
	
<table>
	<tr id="th">
		<td>판매자번호</td>
		<td>상품카테고리</td>
		<td>상품가격</td>
		<td>상품원산지</td>
		<td>상품등록일</td>
		<td>상품 수량</td>
		<td>상품수확일</td>
		<td>삼품재고량</td>
	</tr>
	<tr>
	
		<td>${itemDetail.sellerNo}</td>
		<td><input type="text" name="itemCategory" value="${itemDetail.itemCategory}"></td>
		<td><input type="text" name="itemPrice" value="${itemDetail.itemPrice}"></td>
		<td><input type="text" name="itemOrigin" value="${itemDetail.itemOrigin}"></td>
		<td>${itemDetail.itemDate}</td>
		<td><input type="number" name="itemQuantity" value="${itemDetail.itemQuantity}"></td>
		<td><input type="date" name="itemHarvest" value="${itemDetail.itemHarvest}"></td>
		<td><input type="number" name="itemStock" value="${itemDetail.itemStock}">
			<input type="hidden" id="itemOut" name="itemOut" value="${itemDetail.itemOut}"></td>
		<td>
	</tr>
</table>			
		<div style="text-align: right;"><input id="modifyItem" type="button" class="btn btn-default" value="수정">
		<input id="deleteItem" type="button" class="btn btn-default" value="판매중지"></div>
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