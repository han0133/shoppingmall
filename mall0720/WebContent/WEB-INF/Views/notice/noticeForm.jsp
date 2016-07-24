<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
	html{
		margin-top: 3%;
		margin-left: 20%;
		margin-right: 20%;	
	}
	label{
		vertical-align: top;
	}
	#title{
			width: 60%;
			margin-top: 20px;
			margin-bottom: 1px;
	}
	#content{
			font-size:9pt;
			margin-top: 1px;
			width: 60%;
			table-layout: fixed;
			width: 500px;
			height: 400px;
	}
	#enroll{
			margin-left:410px;
			margin-top: 10px;
			text-align:right;
	}
	div, ul, li { margin:0; padding:0; }
	
	#notice{
		margin-left: 20%;
		margin-right: 27%;
	}

	.blogMenu {
		float:left;   
		margin:0px 5px 3px 5px;
		padding:0 0 3px 0; 
		border-radius: 3px;    /* 테두리를 둘그렇게  */
	}

	.blogMenu ul li {
		float:left;
		list-style-type:none; /*글 목록*/
	}

	.blogMenu a {
		height:16px;
		font-size:12px;
		padding:0 10px 0 10px;  /*오왼탑바텀*/
		text-decoration:none;  /* 글자 밑줄*/
	}

	.blogMenu a:hover {
		border:1px solid 000000;
	}

	.blogMenu ul ul {
		display:none;
		position:absolute;
	}
	.editTool{
		border:thin solid black;
	}
	
	.blogMenu ul li:hover ul {
		display: block;
	}
	
	 .blogMenu ul ul li { 
	 	float:none; 
	 } 
	 table td{
	 		border:thin solid;
	 }
	 
	 aa{
	 	float:left;
	 	text-decoration:none;
	 }
	 
	 #fontWeight{
	 }

</style>
</head>
<script>
	var count = 0;
	
	$(document).ready(function(){
		
		$('#enroll').click(function(){
			if($('#title').val()==""){
				alert("제목을 입력하세요");
			}
			
			else if($('#content').val()==""){
				alert("내용을 입력하세요");
			}else {
				$('#noticeForm').attr('/NoticeAddController');
				$('#noticeForm').submit();
			}	
		});
			
		//conetent(글 내용)의 폰트를 변경하는 메서드(0715박종무)
		$('#fontSize10').click(function(){
			$('#content').css('font-size','10px');
		});	
		$('#fontSize11').click(function(){
			$('#content').css('font-size','11px');
		});	
		$('#fontSize12').click(function(){
			$('#content').css('font-size','12px');
		});		
		$('#fontSize14').click(function(){
			$('#content').css('font-size','14px');
		});
		$('#fontSize18').click(function(){
			$('#content').css('font-size','18px');
		});	
		
		//폰트 굵게 하는 메서드(0715 박종무)
		$('#fontWeight').click(function(){
			if(count==0){
				$('#content').css('font-weight', 'bolder');
				count += 1;
			//	console.log("count 1");
			}else{
				$('#content').css('font-weight', 'normal');
				count -= 1;
			//	console.log("count 0");
			}
		});
		
		//폰트 컬러 바꾸는 메서드(0716 박종무)
		$('#fongColorRed').click(function(){
			$('#content').css('color', '#FF0000');
		});
		
		$('#fongColorYellow').click(function(){
			$('#content').css('color', '#FFE400');
		});
		$('#fongColorGreen').click(function(){
			$('#content').css('color', '#1DDB16');
		});
		$('#fongColorBlue').click(function(){
			$('#content').css('color', '#0054FF');
		});
		$('#fongColorBlack').click(function(){
			$('#content').css('color', '#000000');
		});
		$('#fongColorPurple').click(function(){
			$('#content').css('color', '#8041D9');
		});
		$('#fongColorWhite').click(function(){
			$('#content').css('color', '#FFFFFF');
		});
		
		//글꼴 변경 메서드(0717 박종무)
		$('#fontFamilyBatang').click(function(){
			$('#content').css('font-family', '바탕체');
		});
		$('#fontFamilyGungse').click(function(){
			$('#content').css('font-family', '궁서체');
		});
		$('#fontFamilyGeorgia').click(function(){
			$('#content').css('font-family', 'Georgia');
		});
		$('#fontFamilyMalgunGothic').click(function(){
			$('#content').css('font-family', '"맑은 고딕", "Malgun Gothic", verdana');
		});
		$('#fontFamilyCourier').click(function(){
			$('#content').css('font-family', 'courier');
		});
		$('#fontFamilyTimes').click(function(){
			$('#content').css('font-family', 'times');
		});
		$('#fontFamilyDotum').click(function(){
			$('#content').css('font-family', '"돋음", Dotum, Baekmuk Dotum, Undotum, Apple Gothic, Latin font, sans-serif');
		});
		$('#fontFamilyGulim').click(function(){
			$('#content').css('font-family', '"굴림", Gulim, Arial, sans-serif');
		});
		$('#fontFamilyArialBlack').click(function(){
			$('#content').css('font-family', 'ArialBlack');
		});
	});	
	
</script>
<body>
<jsp:include page="/module/nav.jsp" flush="false"/>
<% request.setCharacterEncoding("UTF-8"); %>
<div id="notice">
	<form id="noticeForm" action="/NoticeAddController" method="post">
		<div>
			<div>
				<input type="text" id= "title" placeholder="제목을 입력하세요" name="noticeTitle"> 
			</div>
			<div>	
			<table>
				<tr>
					<td>						
						<div class="blogMenu">
						 	<ul>
						 		<li><a title="글꼴">글꼴</a>
						 			<ul style="font-stretch:  ">
						 				<li id="fontFamilyBackGround" class="editTool"><a>바탕<span style="font-family: 바탕체;" class="aa"> (가나다라abcd)</span></a></li>
						 				<li id="fontFamilyGungse"  class="editTool"><a>궁서<span style="font-family: 궁서체;" class="aa"> (가나다라abcd)</span></a></li>
						 				<li id="fontFamilyGeorgia"  class="editTool"><a>Georgia<span style="font-family: Georgia;" class="aa">(가나다라abcd)</span></a></li>
						 				<li id="fontFamilyMalgunGothic"  class="editTool"><a>맑은고딕<span style="font-family: '맑은 고딕', 'Malgun Gothic', verdana;" class="aa">(가나다라abcd)</span></a></li>
						 				<li id="fontFamilyCourier"  class="editTool"><a>courier<span style="font-family: courier;" class="aa">(가나다라abcd)</span></a></li>
						 				<li id="fontFamilyGulim"  class="editTool"><a>굴림<span style="font-family: '굴림', Gulim, Arial, sans-serif" class="aa">(가나다라abcd)</span></a></li>
						 				<li id="fontFamilyTimes"  class="editTool"><a>times<span style="font-family: times;" class="aa">(가나다라abcd)</span></a></li>
						 				<li id="fontFamilyDotum"  class="editTool"><a>돋음<span style="font-family: '돋음', Dotum, Baekmuk Dotum, Undotum, Apple Gothic, Latin font, sans-serif;" class="aa">(가나다라abcd)</span></a></li>
						 				<li id="fontFamilyArialBlack"  class="editTool"><a>ArialBlack<span style="font-family: Arial Black;" class="aa">(가나다라abcd)</span></a></li>
						 			</ul>
						 		</li>
						 	</ul>					 	
						 </div>
					</td>
					<td>						
						<div class="blogMenu">
						 	<ul>
						 		<li><a title="글자크기">9pt</a>
						 			<ul>
						 				<!-- <li><a>9pt</a></li> -->
						 				<li id="fontSize10" class="editTool"><a><span style="font-size: 10pt;">가나다라마바사</span>10pt</a></li>
						 				<li id="fontSize11" class="editTool"><a><span style="font-size: 11pt;">가나다라마바사</span>11pt</a></li>
						 				<li id="fontSize12" class="editTool"><a><span style="font-size: 12pt;">가나다라마바사</span>12pt</a></li>
						 				<li id="fontSize14" class="editTool"><a><span style="font-size: 14pt;">가나다라마바사</span>14pt</a></li>
						 				<li id="fontSize18" class="editTool"><a><span style="font-size: 18pt;">가나다라마바사</span>18pt</a></li>
						 			</ul>
						 		</li>
						 	</ul>					 	
						 </div>
					</td>
					<td>						
						<div class="blogMenu">
						 	<ul>
						 		<li id="fontWeight"><a title="굵게" style="font-weight: bolder;">가</a>
						 		</li>
						 	</ul>					 	
						 </div>
					</td>
					<td>						
						<div class="blogMenu">
						 	<ul>
						 		<li><a title="글자색"><img src="../WebContent/img/characterColor.PNG"></a>
						 			<ul>
						 				<li id="fongColorRed"><a><img src="../img/FF0000.PNG"></a></li>
						 				<li id="fongColorYellow"><a>노랑</a></li>
						 				<li id="fongColorGreen"><a>초록</a></li>
						 				<li id="fongColorBlue"><a>파랑</a></li>
						 				<li id="fongColorBlack"><a>검정</a></li>
						 				<li id="fongColorPurple"><a>보라</a></li>
						 				<li id="fongColorWhite"><a>하얀</a></li>
						 			</ul>
						 		</li>
						 	</ul>					 	
						 </div>
					</td>			 
				 </tr>
			</table>
			</div>
			<div>
				<textarea id="content" rows="20" cols="10" name="noticeContent" style=""></textarea>
			</div>
		</div>
		<input id = "enroll" type="button" value="등록">
	</form>
</div>
</body>
</html>