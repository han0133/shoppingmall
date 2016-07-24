<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
    footer {
      background-color: #ffb3b3;
      padding: 25px;
    }
	h3{
		text-align: center;
	}
	h1{
		text-align: center;
		text-decoration: underline;
	}
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<br/>
	<!-- 아이디 찾기 결과를 보여줍니다 -->
	<c:if test="${id ne null}">
		<c:if test="${id eq 'fail' }">
			<h2>입력하신 정보와 일치하는 아이디가 없습니다.</h2>
		</c:if>
		<h3>고객님의 아이디는 </h3>
		<h1>${id}</h1>
		<h3>입니다.</h3>
	</c:if>
	
	<c:if test="${pw ne null}">
		<h3>고객님의 비밀번호는</h3>
		<h1>${pw}</h1>
		<h3>입니다.</h3>
	</c:if>
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