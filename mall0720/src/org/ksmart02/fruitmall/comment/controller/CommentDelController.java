package org.ksmart02.fruitmall.comment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.ksmart02.fruitmall.comment.model.Comment;
import org.ksmart02.fruitmall.comment.service.CommentService;

@WebServlet("/CommentDelController")
public class CommentDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService;
	private Comment comment;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		
		int result = 0;
		commentService = new CommentService();
		comment = new Comment();
		comment.setCommentNo(commentNo);
		result = commentService.commentDelService(comment);
		if(result == 1){
			System.out.println("comment삭제성공");
			response.sendRedirect("/itemDetailController?itemNo="+itemNo);
		}else{
			System.out.println("comment삭제실패");
			response.sendRedirect("/itemDetailController?itemNo="+itemNo);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
