<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>navbar</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
	.main{
		margin-top: 3%;
	}
	#itemSearchForm{
		margin-top: 4%;
	}
	.navbar {
		margin-bottom: 0;
		background-color: #ffb3b3;
		z-index: 9999;
		border: 0;
		font-size: 12px !important;
		line-height: 1.42857143 !important;
		letter-spacing: 4px;
		border-radius: 0;
		font-family: Montserrat, sans-serif;
	}
	
	.navbar li a, .navbar .navbar-brand {
		color: #fff !important;
	}
	
	.navbar-nav li a:hover, .navbar-nav li.active a {
		color: #ffb3b3 !important;
		background-color: #fff !important;
	}
	
	.navbar-default .navbar-toggle {
		border-color: transparent;
		color: #fff !important;
	}
</style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

	<!-- 로그인 되었을 때 실행되는 부분입니다 -->
	<c:if test="${loginId ne null}">
		<div>
			<nav class="navbar navbar-default navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="/">Fruit_Mall</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="/LogoutController">로그아웃</a></li>
							<li><a href="/MyPageController">마이페이지</a></li>
							<li><a href="/DropOutController">회원탈퇴</a></li>
							<li><a href="/NoticeListController">공지사항</a></li>	
							<li><a href="/SellerListController">판매자 리스트</a></li>
							<li>
								<!-- 상품검색 -->
								<form id="itemSearchForm" action="/itemListController" method="post">
									<input name="searchKeyWord" id="itemSearch" type="text"> 
									<input id="itemSearchBtn" type="button" class="btn btn-danger" value="검색">
								</form>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</c:if>

	<!-- 로그인이 안되었을 때 실행되는 부분입니다 -->
	<c:if test="${loginId eq null}">
		<div>
			<nav class="navbar navbar-default navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="/">Fruit_Mall</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="/LoginController">로그인</a></li>
							<li><a href="/JoinMemberController">회원가입</a></li>
							<li><a href="/NoticeListController">공지사항</a></li>	
							<li>
								<!-- 상품검색 -->
								<form id="itemSearchForm" action="/itemListController" method="post">
									<input name="searchKeyWord" id="itemSearch" type="text"> 
									<input id="itemSearchBtn" type="button" class="btn btn-danger" value="검색">
								</form>
							</li>
						</ul>
					</div>
				</div>
			</nav>	
		</div>	
	</c:if>
	<a href="/"><img src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQS6CrfwtpcKNuZF6P3F0FsfRniSChR-bj5pQ8v4VQrY_Q8_bhH" class="main" style="width: 100%"/></a>
</body>
</html>