<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body{
		margin-left: 30%;
		margin-right: 30%;
	}
	.title{
			width: 100px;
	}
</style>
</head>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<% request.setCharacterEncoding("UTF-8"); %>
<h1>공지사항</h1>
 modifyForm 입니다
	<form action="NoticeModifyController" method="post">
		<div>
			<div>
				<input class="title" type="text" value="${notice.noticeTitle}" name="noticeTitle">
			</div>
			<div>	
				<textarea rows="20" cols="60" name="noticeContent">${notice.noticeContent}</textarea>
			</div>
			<div>
				<input type="hidden" value="${notice.noticeNo}" name="noticeNo" >
			</div>
			
		</div>
		<div>
			<div>    
				<a href="/NoticeModifyController?noticeNo=${notice.noticeNo }"><button>수정</button></a>
			</div>
		</div>	
	</form>	
	<div>	
		<a href="/NoticeListController?noticeNo=${notice.noticeNo }"><button>취소</button></a>
	</div>
	
</body>
</html>