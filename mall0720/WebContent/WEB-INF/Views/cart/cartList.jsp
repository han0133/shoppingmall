<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('.cartNoChk').click(function(){
		$('#cartNoChk:checked').each(function() { 
	        alert($(this).val());
	   });
	});
	
	//전체체크
	$("input[name='checkedAll']").click(function(){
		var chk = $('.cartNoChk');
		for(var i=0; i< chk.length; i++) {
			chk[i].checked = this.checked
		}
	})
	//버튼클릭에 따른 구동입니다.
	$('#cartOrder').click(function(){
		if($('.cartNoChk:checked').size()<1){
	        alert("1개 이상 체크해주세요");
	        console.log("delete");
	    }else{
			$('#cartForm').prop('action','/OrderItemController');
			$('#cartForm').prop('method','GET');
			$('#cartForm').submit();
	    }
	});
	
	
	
	$('.view_items').each(function (index, item) {    // 클래스 view_items 의 index 및 객체 item
	    if( $(item).css('display') == 'block' ){    // 현재 출력되고 있는 item 채크
	        $('.view_items').css('display', 'none');
	        if( index == 0){
	            $('.view_items').last().css('display', 'block');    // index 가 0이면, 마지막 객체를 출력
	        }else{
	            $('.view_items').eq(index-1).css('display', 'block');    // 현재 출력된 item의 바로 이전것 출력
	                                    // eq() : 특정 위치에 있는 문서 객체 선택, first()는 첫번째, last()는 마지막
	        }
	        return false;    // each 문을 빠져나감(break) 
	    }
	});
	
	
	/* $('.ordersCount').change(function(){
		console.log('change');
		$('.ordersCount').each(function (index, item) {
			var max = $(this).attr('max');
			var val = $(this).val();
			console.log('max:::: '+max);
			console.log('val:::: '+val);
			
			if( max < val ){
				console.log('초과');
				$(this).val('0');
			}
		});
	}); */
	
	$('#cartDelete').click(function(){
		 if($('.cartNoChk:checked').size()<1){
	         alert("1개 이상 체크해 주세요")
	      }else{
			$('#cartNoChk:checked').each(function() { 
				$('#cartNoChk:checked').prop('name','cartNos');
	  	 	});
			$('#cartForm').prop('action','/DelCartController');
			$('#cartForm').prop('method','POST');
			$('#cartForm').submit();
	      }
	});
});
</script>
<style type="text/css">
	table{
		width: 80%;
		text-align: center;
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
<% request.setCharacterEncoding("UTF-8"); %>
	<form id="cartForm" action="" method="">
	
		<table>
			<tr id="th">
				<td>전체선택<input type="checkbox" name="checkedAll" value="전체선택" /></td>
				<td>상품</td>
				<td>장바구니 번호</td>
				<td>상품 번호</td>
				<td>주문수량</td>
			</tr>
			<c:forEach var="list" items="${cartList}">
				<tr>
				<input type="hidden" name="itemPrice" value="${list.item.itemPrice}">
					<td><input type="checkbox" class="cartNoChk" name="checkedItem" value="${list.cartNo}"/></td>
					<td style="width: 70px; height: 70px; overflow: hidden">
	            <a href="/itemDetailController?itemNo=${list.item.itemNo}">
	            <img src="${list.item.itemImage}" id="img"/></a>
	         </td> 
					<td>${list.cartNo}<input type="hidden" name="cartNo" value="${list.cartNo}" /></td>
					<td>${list.item.itemNo}	<input type="hidden" name="itemNo" value="${list.item.itemNo}" /> </td>
					<td><input type="number" class="ordersCount" id="ordersCount" name="ordersCount" value="${list.cartCount}" min="1" max="${list.item.itemStock}" /></td>
			</c:forEach> 
			<tr align="right">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<input type="button" class="btn btn-default" id="cartOrder" value="주문">
					<input type="button" class="btn btn-default" id="cartDelete" value="삭제">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>