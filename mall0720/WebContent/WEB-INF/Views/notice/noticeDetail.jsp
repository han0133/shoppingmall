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
 noticeDetail 입니다
	<h1>공지사항</h1>
	<div>
		<div>
			<input readonly="readonly" class="title" type="text" value="${notice.noticeTitle}" name="noticeTitle">
		</div>
		<div>	
			<textarea readonly="readonly" rows="20" cols="60" name="noticeContent">${notice.noticeContent}</textarea>
		</div>
	</div>
	<c:if test="${ memberLevel eq '관리자'}">
		<div>
			<div>    
				<a href="/NoticeModifyController?noticeNo=${notice.noticeNo }"><button>수정</button></a>
			</div>
			<div>	
				<a href="/NoticeDelController?noticeNo=${notice.noticeNo }"><button>삭제</button></a>
			</div>
		</div>
	</c:if>	
</body>
</html>