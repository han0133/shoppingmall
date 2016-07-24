<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	console.log("ready");
	
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
	
	$("#commentUpdate").click(function(){
		console.log("click");
		
		//유효성검사
		if($("#commentTitle").val()==""){
			$("#msgBoxCommentTitle").text("제목을 입력해주세요");
			$("#msgBoxCommentContent").text("");
			$("#msgBoxSelectStar").text("");
		}else if($("#commentContent").val()==""){
			$("#msgBoxCommentTitle").text("");
			$("#msgBoxCommentContent").text("내용을 입력해주세요");
			$("#msgBoxSelectStar").text("");
		}else if($("#selectStar").val()==""){
			$("#msgBoxCommentTitle").text("");
			$("#msgBoxCommentContent").text("");
			$("#msgBoxSelectStar").text("별점을 선택해주세요");
		}else{
			$("#commentUpdateForm").submit();
		}
		
		
		
			
		
	});
});
</script>
<style>
	label{
		vertical-align:top;
	}
</style>
<title>Insert title here</title>
</head>
<jsp:include page="/module/nav.jsp" flush="false"/>
<body>
	<form id="commentUpdateForm" action="/CommentUpdateController" method="post">
		<input type="hidden" name="commentNo" value="${comment2.commentNo}">
		<input type="hidden" name="itemNo" value="${comment2.itemNo}">
		<div>
			<label>제목 : </label>
			<input id="commentTitle" type="text" name="commentTitle" value="${comment2.commentTitle}">
			<span id="msgBoxCommentTitle"></span>
			
		</div>
		<div>
			<label>내용 : </label>
			<textarea id="commentContent" style="resize:none" rows="3" cols="80" name="commentContent">${comment2.commentContent}</textarea>
			<span id="msgBoxCommentContent"></span>
			<input id="commentUpdate" type="button" class="btn btn-default" value="댓글수정"/>
		</div>
		<input id="commentRate" type="hidden" name="commentRate" value="${comment2.commentRate}">
		<div>
			<label>수정 전 : </label>
			<input id="commentStar" type="text" name="commentStar" style="border:none;" value="${comment2.commentStar}" readonly="readonly">
		</div>
		<div>
			<label>수정 후 : </label>
			<select id="selectStar">
				<option value="">☆☆☆☆☆</option>
				<option value="1">★☆☆☆☆</option>
				<option value="2">★★☆☆☆</option>
				<option value="3">★★★☆☆</option>
				<option value="4">★★★★☆</option>
				<option value="5">★★★★★</option>
			</select>
				<span id="msgBoxSelectStar"></span>			
		</div>
	</form>
</body>
</html>