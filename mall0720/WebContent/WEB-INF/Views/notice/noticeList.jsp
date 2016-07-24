<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
	.paging{
			magin
	}
</style>
<title>공지사항</title>
<style>

 	a:link { color: black; text-decoration: none;}
	a:visited { color: black; text-decoration: none;}
	a:hover { color: black; text-decoration: underline;}
	
 	#main{ 
		margin-left: 15%; 
 	    margin-right: 15%;
 	}
 	#notice{
 		font-size: large; 
 	}
 	#paging{
 		font-size: medium;
 	}
	#noticeMain,#paging{
		text-align: center;
	}
	#addNotice{
		text-align: right;
		margin-left: 15%; 
 	    margin-right: 15%;
 	}

</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<% request.setCharacterEncoding("UTF-8"); %>
<div id="noticeMain">
	<h1>공지사항</h1>
</div>
<div id="main">
	<div id="notice">
	<div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-3">
				<label class="list_a">No</label>
			</div>
			<div class="col-sm-3">
					<label class="list">제목</label>
			</div>
			<div class="col-sm-3">
				<label class="list">등록일</label>
			</div>
		</div>
	</div>
	<div>
		<c:forEach var="list" items="${map.list}"> 
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-3">
				<span>${list.noticeNo }</span>
			</div>
			<div class="col-sm-3">
				<a href ="/NoticeDetailController?noticeNo=${ list.noticeNo }">
					<span>${list.noticeTitle }</span>
				</a>
			</div>
			<div class="col-sm-3">
					<span>${list.noticeDate}"</span>
			</div>
		</div>	
		</c:forEach>
	</div>	
	</div>
	<div>	
		<div id="paging">	
			<span class="paging">
			<a href="/NoticeListController?nowPage=1"> ◀◀ </a>
			
			<c:if test="${map.pageHelper.movePage-limitLink > map.pageHelper.limitLink}">
				<a href="/NoticeListController?nowPage=${map.pageHelper.movePage-map.pageHelper.limitLink}">◀</a>
			</c:if>
				
			<c:if test="${map.pageHelper.movePage <= map.pageHelper.limitLink}">
				◀
			</c:if>
			
			<c:forEach var="link" items="${map.pageHelper.linkPages}">
				<a href="/NoticeListController?nowPage=${link}">[${link}]</a>
			</c:forEach>
			
			<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink < map.pageHelper.totalPage}">			
				<a href="/NoticeListController?nowPage=${map.pageHelper.movePage+map.pageHelper.limitLink}">▶</a>
			</c:if>
			
			<c:if test="${map.pageHelper.movePage+map.pageHelper.limitLink > map.pageHelper.totalPage}">
				▶
			</c:if>
			
			<a href="/NoticeListController?nowPage=${map.pageHelper.totalPage}"> ▶▶ </a>
			</span>
		</div>	
	<c:if test="${ memberLevel eq '관리자'}">
		<div id="addNotice">
			<label>
				<a href="/NoticeAddController"><input type="button" class="btn btn-default" value="공지쓰기"></a>
			</label>
		</div>
	</c:if>
</div>
</div>
</body>
</html>