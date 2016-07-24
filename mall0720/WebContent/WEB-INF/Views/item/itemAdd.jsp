<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Add</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
	#itemAddForm{
		padding-left:30%;
		width: 60%;
	}	
	#itemAddBtn{
		text-align: right;
	}
</style>
<script>
$(document).ready(function(){		
	
	//판매자번호 사용가능 유무
	$('#sellerNo').focusout(function(){
		$.ajax({
		      type		: "POST",
		      url		: "/JSONSellerNoCheckController",
		      data		: { sellerNo	: $('#sellerNo').val() },
		      dataType	: "JSON",
		      success	: function(str) {
					if(str.result=="true"){
						alert('없는 판매자 번호입니다');
						$('#sellerNo').val('');
					}else{ 
						alert('사용가능한 판매자 번호 입니다.');
						
					}
		      }
	  });
	});
	
	
	
	// 폼 제출
	$('#itemAddBtn').click(function(){
		//아이디 중복체크 여부 검사 
		if ($('#sellerNo').val() == ""){
			alert("판매자번호를 입력해주세요.");
		}else if ($('#itemName').val() == ""){
			alert("상품명을 입력해주세요.");
		}else if ($('#itemCategory').val() == ""){
			alert("카테고리를 입력해주세요.");
		}else if ($('#itemImage').val() == ""){
			alert("대표이미지를 입력해주세요.");
		}else if ($('#itemPrice').val() == ""){
			alert("상품 가격을 입력해주세요.");
		}else if ($('#itemOrigin').val() == ""){
			alert("원산지를 입력 해주세요.");
		}else if($('#itemQuantity').val() == ""){
			alert("(1box)수량을 입력해주세요.");
		}else if($('#itemHarvest').val() == ""){
			alert("수확일을 입력해주세요.");
		}else if($('#itemStock').val() == ""){
			alert("재고량을 입력해주세요.");
		}else{
			console.log("모든 체크끝");
// 			$('#itemAddForm').submit();
		}
	});
});
</script>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
	<form action="/ItemAddController" method="POST" enctype="multipart/form-data" id="itemAddForm">
		<div class="form-group">
			<h2>상품 등록</h2>
			<div><label>판매자 번호</label></div>
			<div><input type="text" id="sellerNo" name="sellerNo" class="form-control" placeholder="판매자 번호를 입력해주세요."></div>
			<div><label>상품 이름</label></div>
			<div><input type="text" id="itemName" name="itemName" class="form-control" placeholder="상품 이름을 입력해주세요."></div>
			<div><label>카테고리</label></div>
<!-- 			<div><input type="text" id="itemCategory" name="itemCategory" class="form-control" placeholder="계절 카테고리를 입력해주세요."></div> -->
			<div>
				<select id="itemCategory" name="itemCategory" class="form-control">
					<option value="">::카테고리를 선택하세요::</option>
					<option value="spring">봄</option>
					<option value="summer">여름</option>
					<option value="fall">가을</option>
					<option value="winter">겨울</option>
				</select>
			</div>
			<div><label>제품 사진</label></div>
			<div><input type="file" id="itemImage" name="itemImage" class="btn btn-default"></div>
			<div><label>가격</label></div>
			<div><input type="text" id="itemPrice" name="itemPrice" class="form-control" placeholder="(원을 제외한)가격을 입력해주세요."></div>
			<div><label>원산지</label></div>
			<div><input type="text" id="itemOrigin" name="itemOrigin" class="form-control" placeholder="원산지를 입력해주세요."></div>
			<div><label>수량(1box)</label></div>
			<div><input type="number" id="itemQuantity" name="itemQuantity" class="form-control" min="1" placeholder="1box내 수량을 입력해주세요."></div>
			<div><label>수확일</label></div>
			<div><input type="date" id="itemHarvest" name="itemHarvest" class="form-control" placeholder="수확 일자 입력해주세요."></div>
			<div><label>재고</label></div>
			<div><input type="number" id="itemStock" name="itemStock" class="form-control" min="1" placeholder="박스 재고를 입력해주세요."></div>
		</div>
		<div style="text-align: right;">
			<input type="button" class="btn btn-default" id="itemAddBtn" value="올리기">
		</div>
	</form>
	<br/>
</body>
</html>