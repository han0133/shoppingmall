package org.ksmart02.fruitmall.comment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.comment.model.Comment;
import org.ksmart02.fruitmall.comment.service.CommentService;

@WebServlet("/CommentAddController")
public class CommentAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Comment comment;
	private CommentService commentService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommentAddController 실행");
		
		int result = 0;
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String memberId = request.getParameter("memberId");
		String commentTitle = request.getParameter("commentTitle");
		String commentContent = request.getParameter("commentContent");
		int commentRate = Integer.parseInt(request.getParameter("commentRate"));
		String commentStar = request.getParameter("commentStar");
		
		System.out.println("itemNo :" + itemNo);
		System.out.println("memberId :" + memberId);
		System.out.println("commentTitle :" + commentTitle);
		System.out.println("commentContent : "+ commentContent);
		System.out.println("commentRate :" + commentRate);
		System.out.println("commentStar :" + commentStar);
		
		comment = new Comment();
		comment.setItemNo(itemNo);
		comment.setMemberId(memberId);
		comment.setCommentTitle(commentTitle);
		comment.setCommentContent(commentContent);
		comment.setCommentRate(commentRate);
		comment.setCommentStar(commentStar);
		
		System.out.println("CommentAddController comment : " + comment);
		commentService = new CommentService();
		result = commentService.commentAddService(comment);
		if(result == 1){
			System.out.println("commentAdd성공!!");
			response.sendRedirect("/itemDetailController?itemNo="+itemNo);
		}else{
			System.out.println("commentAdd실패!!");
			response.sendRedirect("/itemDetailController?itemNo="+itemNo);
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
