<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		var itemStock = $('#ordersCountView').attr('max');
		//
		//재고가 있다면 if문 실행
		if(itemStock > 0){
			$('#addCart').click(function(){
				$('#addFrom').attr('action',"/PutCartController");
				$('#addFrom').attr('method',"POST");
				var ordersCountView = $('#ordersCountView').val();
				$('#ordersCount').val(ordersCountView);
				$('#addFrom').submit();
			});
			$('#addOrders').click(function(){
				$('#addFrom').attr('action',"/OrderItemController");
				$('#addFrom').attr('method',"GET");
				var ordersCountView = $('#ordersCountView').val();
				$('#ordersCount').val(ordersCountView);
				$('#addFrom').submit();
			});
		}else{
			alert('재고가 부족합니다');
		}
		
		//평균별점에 따른 별 넣어주기
		if($("#avgStar").val()==1){
			$("#avgStarView").val("★☆☆☆☆");
		}else if($("#avgStar").val()==2){
			$("#avgStarView").val("★★☆☆☆");
		}else if($("#avgStar").val()==3){
			$("#avgStarView").val("★★★☆☆");
		}else if($("#avgStar").val()==4){
			$("#avgStarView").val("★★★★☆");
		}else if($("#avgStar").val()==5){
			$("#avgStarView").val("★★★★★");
		}
		
		$("#selectStar").change(function(){
			console.log("change");
			
			if($("#selectStar").val()==1){
				$("#commentRate").val("1");
				$("#commentStar").val("★☆☆☆☆");
				
			}else if($("#selectStar").val()==2){
				$("#commentRate").val("2");
				$("#commentStar").val("★★☆☆☆");
			
			}else if($("#selectStar").val()==3){
				$("#commentRate").val("3");
				$("#commentStar").val("★★★☆☆");
			
			}else if($("#selectStar").val()==4){
				$("#commentRate").val("4");
				$("#commentStar").val("★★★★☆");
				
			}else if($("#selectStar").val()==5){
				$("#commentRate").val("5");
				$("#commentStar").val("★★★★★");
			}
			
		});
		
		$("#commentAdd").click(function(){
			console.log("click");
			
			if($("#commentTitle").val()==""){
				$("#msgboxCommentTitle").text("제목을 입력하세요");
				$("#msgboxCommentContent").text("");
				$("#msgboxSelectStar").text("");
			}else if($("#commentContent").val()==""){
				$("#msgboxCommentTitle").text("");
				$("#msgboxCommentContent").text("내용을 입력하세요");
				$("#msgboxSelectStar").text("");
			}else if($("#selectStar").val()== ""){
				$("#msgboxCommentTitle").text("");
				$("#msgboxCommentContent").text("");
				$("#msgboxSelectStar").text("별점을 입력하세요");
			}else{
				$("#commentAddForm").submit();
			}
	})
	});
</script>
<style>
	footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	div{
		text-align: center;
		margin-left: 10%;
		margin-right: 10%;	
	}
	#avgStarView{
		text-align: center;
		border:none;
		width: 100px; 
	}
	table{
		width: 100%;
		text-align: center;

	}
	#th{
		border-bottom : 1px solid;
		border-bottom-color:#ffb3b3;
		border-bottom-width: 100%;
	}
	#comment{
		text-align: left;
		margin-left: 10%;
		margin-right: 10%;
	}
	label{
		vertical-align:top;
	}
	#img{
		width:100%;
 		max-width: auto; 
 		max-height: 300px;
 		
	}
</style>
</head>
<a href="/itemListController">리스트목록으로</a>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
	<div><label>no :</label>${itemDetail.itemNo} &nbsp; <a href="/ItemModfiyController?itemNo=${itemDetail.itemNo}">수정</a></div>	
	<div><h2>${itemDetail.itemName}</h2></div>
		<div>
<!-- 			<label>평균별점 : </label> -->
			<input id="avgStar" type="hidden" name="avgStar" value="${avgStar}" readonly="readonly"/>
			<input id="avgStarView" type="text" name="avgStarView" value="" readonly="readonly" />
		</div>
		<div style="margin-left: 25%;">
			<div style="width: 500px; height: 300px; overflow: hidden; ">
				<img src="${itemDetail.itemImage}" id="img">
			</div>
		</div>
<div>
<br/>
<br/>
<table>
	<tr id="th">
		<td>가격</td>
		<td>원산지</td>
		<td>수량</td>
		<td>판매자</td>
		<td>등록일</td>
		<td>수확일자</td>
		<td>재고</td>
		<td>수량</td>
	</tr>
	<tr>
		<td>${itemDetail.itemPrice}</td>
		<td>${itemDetail.itemOrigin}</td>
		<td>${itemDetail.itemQuantity}</td>
		<td>${itemDetail.sellerNo}</td>
		<td>${itemDetail.itemDate}</td>
		<td>${itemDetail.itemHarvest}</td>
		<td>${itemDetail.itemStock}</td>
		<td><input type="number" id="ordersCountView" name="ordersCountView" max="${itemDetail.itemStock}" min="1" value="1"></td>
	</tr>
</table>
</div>	
<div id="comment">
	<br/>
	<form id="addFrom" action="" method="">
		<input type="hidden" name="itemPrice" value="${itemDetail.itemPrice}">
		<input type="hidden" name="itemNo" value="${itemDetail.itemNo}">
		<input type="hidden" id="ordersCount" name="ordersCount" value="">
		<div style="text-align: right;"><input id="addCart" type="button" class="btn btn-default" value="장바구니">
		<input id="addOrders" type="button" class="btn btn-default" value="구입"></div>
	</form>

	<div>${itemDetail.itemDetail}</div>
	
	<c:if test="${result == 0 and sessionId != null}">
		<!-- result가 0입니다. 결과값이 없으므로 입력폼을 보임 -->
		<!-- 댓글add폼입니다. -->
		<h3>댓글추가하기</h3>                  
		<form id="commentAddForm" action="/CommentAddController" method="post">
			<input type="hidden" name="itemNo" value="${itemDetail.itemNo}">
			<input type="hidden" name="memberId" value="${sessionId}">
			<div style="padding-left: 1%;text-align: left;">
				<label>제목 : </label>
				<input type="text" id="commentTitle" name="commentTitle">
			<br/>
				<label>내용 : </label>
				<textarea style="resize:none" rows="3" cols="80" id="commentContent" name="commentContent"></textarea>
				<select id="selectStar">
					<option value="">☆☆☆☆☆</option>
					<option value="1">★☆☆☆☆</option>
					<option value="2">★★☆☆☆</option>
					<option value="3">★★★☆☆</option>
					<option value="4">★★★★☆</option>
					<option value="5">★★★★★</option>
				</select>
				<input id="commentRate" type="hidden" name="commentRate" value="">
				<input id="commentStar" type="hidden" name="commentStar" value="">
	
				<input id="commentAdd" type="button"class="btn btn-default"  value="등록"/>
			</div>

		</form>	
	</c:if>
<h3>댓글리스트</h3>
	<c:if test="${result == 1}">
		<h5>[이미 작성하신 댓글이 있습니다.]</h5>
		<!-- result가 1입니다. 결과값이 있으므로 입력폼을 숨김 -->
	</c:if>

	<!-- 댓글리스트입니다. -->

		<table border=1 style="border-color: #ffb3b3;" >
			<tr>
				<td>commentNo</td>
				<td>itemNo</td>
				<td>memberId</td>
				<td>commentTitle</td>
				<td>commentContent</td>
				<td>commentRate</td>
				<td>commentDate</td>
				<td>commentStar</td>
				<td>commentUpdate</td>
				<td>commentDelete</td>
			</tr>
			<c:forEach var="c" items="${map.listComment}">
			<tr>
				<td>${c.commentNo}</td>	
				<td>${c.itemNo}</td>
				<td>${c.memberId}</td>
				<td>${c.commentTitle}</td>
				<td>${c.commentContent}</td>
				<td>${c.commentRate}</td>
				<td>${c.commentDate}</td>
				<td>${c.commentStar}</td>
				
				
				<c:if test="${sessionId eq c.memberId}">
					<td><a href="/CommentUpdateController?commentNo=${c.commentNo}&itemNo=${c.itemNo}">수정</a></td>
				</c:if>
				<c:if test="${sessionId ne c.memberId}">
					<td></td>
				</c:if>
				
				<c:if test="${sessionId eq c.memberId}">
					<td><a href="/CommentDelController?commentNo=${c.commentNo}&itemNo=${c.itemNo}">삭제</a></td>
				</c:if>	
				<c:if test="${sessionId ne c.memberId}">
					<td></td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
		<!-- 페이징구간 -->
			<div>
	 	<a href="/itemDetailController?itemNo=${itemDetail.itemNo}&nowPage=1">◀◀ </a> 
		<c:if test="${map.pageHelper.movePage-limitLink > map.pageHelper.limitLink}">
			<a href="/itemDetailController?itemNo=${itemDetail.itemNo}&nowPage=${map.pageHelper.movePage-map.pageHelper.limitLink}">◀</a>
		</c:if>
		<c:if test="${map.pageHelper.movePage <= map.pageHelper.limitLink}">
			◀
		</c:if>
		<c:forEach var="link" items="${map.pageHelper.linkPages}">
			<a href="/itemDetailController?itemNo=${itemDetail.itemNo}&nowPage=${link}">[${link}]</a>
		</c:forEach>
		<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink < map.pageHelper.totalPage}">			
			<a href="/itemDetailController?itemNo=${itemDetail.itemNo}&nowPage=${map.pageHelper.movePage+map.pageHelper.limitLink}">▶</a>
		</c:if>
		<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink > map.pageHelper.totalPage}">
			▶
		</c:if>
		<a href="/itemDetailController?itemNo=${itemDetail.itemNo}&nowPage=${map.pageHelper.totalPage}"> ▶▶ </a>
		</div>
		
		<br/>
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