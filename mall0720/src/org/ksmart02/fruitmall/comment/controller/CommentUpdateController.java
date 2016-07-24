package org.ksmart02.fruitmall.comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.comment.model.Comment;
import org.ksmart02.fruitmall.comment.service.CommentService;

@WebServlet("/CommentUpdateController")
public class CommentUpdateController extends HttpServlet{
	private Comment comment;
	private Comment comment2;
	private CommentService commentService;
	
	//form으로
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		
		comment = new Comment();
		comment.setCommentNo(commentNo);
		
		commentService = new CommentService();
		comment2 = new Comment();
		comment2 = commentService.commentUpdateFormService(comment);
		System.out.println("CommentUpdateController comment2 : " + comment2);
		
		request.setAttribute("comment2", comment2);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/comment/commentUpdate.jsp");
		rd.forward(request, response);
	}
	
	//실제 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String commentTitle = request.getParameter("commentTitle");
		String commentContent = request.getParameter("commentContent");
		int commentRate = Integer.parseInt(request.getParameter("commentRate"));
		String commentStar = request.getParameter("commentStar");
		
		
		int result =0;
		
		comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setItemNo(itemNo);
		comment.setCommentTitle(commentTitle);
		comment.setCommentContent(commentContent);
		comment.setCommentRate(commentRate);
		comment.setCommentStar(commentStar);
		
		System.out.println("CommentUpdateController dopost comment : " + comment);
		
		commentService = new CommentService();
		result = commentService.commentUpdateService(comment);
		
		if(result == 1){
			System.out.println("update성공!");
			response.sendRedirect("/itemDetailController?itemNo="+itemNo);
		}else{
			System.out.println("update실패!");
			response.sendRedirect("/CommentUpdateController?commentNo="+commentNo);
		}
	}
}
