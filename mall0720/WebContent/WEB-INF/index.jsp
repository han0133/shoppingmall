<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Index</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
    .img-responsive{
		width:100%;
 		max-width: auto; 
 		max-height: auto;
	}
	#css{
		padding-left: 20%;
	}
	#paging{
		text-align: center;
	}
</style>
</head>
<body>
<jsp:include page="../module/nav.jsp" flush="false"/>
	<div>
		<ul class="nav nav-tabs nav-justified">
		    <li class="active"><a href="/itemListController">전체상품</a></li>
		    <li><a href="/itemListController?categoryKeyWord=Spring">봄, Spring</a></li>
		    <li><a href="/itemListController?categoryKeyWord=Summer">여름, Summer</a></li>
		    <li><a href="/itemListController?categoryKeyWord=fall">가을, Fall</a></li>
		    <li><a href="/itemListController?categoryKeyWord=Winter">겨울, Winter</a></li>
		</ul>
	</div>
	<br/>
<div class="container-fluid bg-3 text-center">
  <div class="row">
	<c:forEach var="itemList" items="${map.itemList}">
	    <div class="col-sm-3">
	   		<label for="">상품번호 : ${itemList.itemNo}</label>
		      <p>${itemList.itemName}</p>
		      <div style="width: 350px; height: 180px; overflow: hidden; "id="css">
			      <a href="/itemDetailController?itemNo=${itemList.itemNo}">
		      	 <img src="${itemList.itemImage}" class="img-responsive" alt="Image"></a>
		      </div>		     
	  		<label for="">상품명 : ${itemList.itemName}</label><br/>
	  		<label for="">가격 : ${itemList.itemPrice}</label><br/>
			<label for="">원산지 : ${itemList.itemOrigin}</label> 
	    <br/>
	    <br/>
	    </div>
	</c:forEach>
  </div>
</div><br>
<!-- 0717페이징 관련 박종무 -->
<div id="paging">	
	<span class="paging">
		<a href="/itemListController?nowPage=1"> ◀◀ </a>
		
		<c:if test="${map.pageHelper.movePage-limitLink > map.pageHelper.limitLink}">
			<a href="/itemListController?nowPage=${map.pageHelper.movePage-map.pageHelper.limitLink}">◀</a>
		</c:if>
			
		<c:if test="${map.pageHelper.movePage <= map.pageHelper.limitLink}">
			◀
		</c:if>
		
		<c:forEach var="link" items="${map.pageHelper.linkPages}">
			<a href="/itemListController?nowPage=${link}">[${link}]</a>
		</c:forEach>
		
		<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink < map.pageHelper.totalPage}">			
			<a href="/itemListController?nowPage=${map.pageHelper.movePage+map.pageHelper.limitLink}">▶</a>
		</c:if>
		
		<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink > map.pageHelper.totalPage}">
			▶
		</c:if>
		
		<a href="/itemListController?nowPage=${map.pageHelper.totalPage}"> ▶▶ </a>
	</span>
</div>	
<footer class="container-fluid text-center">
	<address>
		<p>
			<p><strong>(주)FruitMall</strong> ksmart <span><strong>개인정보보호담당자</strong> 서지연</span></p>
			<p><strong>사업자등록번호</strong> 010-9201-4268 <span><strong>통신판매업신고</strong> 2015-기린대로-0589</span></p>
		</p>
		<p>
			<p><strong>주소</strong> 전주시 덕진구 송천동 주공아파트 130동 703호</p>
			<p><span><strong>TEL/FAX</strong> 010-9201-4268</span></p>
		</p>
	</address>
	<div>(주)FruitMall은 신선한 과일을 공급하고 있습니다. 유기농 제품만을 취급합니다.</div>
	<div>Copyright ⓒ Seojiyeon Co, Ltd. All rights reserved</div>
</footer>

</body>
>>>>>>> branch 'master' of https://github.com/han0133/fruitMall.git
</html>