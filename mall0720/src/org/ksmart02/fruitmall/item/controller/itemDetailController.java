package org.ksmart02.fruitmall.item.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.comment.model.Comment;
import org.ksmart02.fruitmall.comment.service.CommentService;
import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.item.service.ItemService;
import org.ksmart02.fruitmall.util.PageHelper;

@WebServlet("/itemDetailController")
public class itemDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService;
	private CommentService commentService;
	private Comment comment;
	private Comment comment2;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//최초 기입 박종무 
		//두번째 기입(댓글리스트 관련) 오성현
		
		request.setCharacterEncoding("UTF-8");
		
		int itemNo 				= Integer.parseInt(request.getParameter("itemNo"));
		System.out.println("itemDetailController itemNo : " + itemNo);
		itemService 			= new ItemService();
		
		Item itemDetail 		= itemService.itemDetail(itemNo);
		request.setAttribute("itemDetail", itemDetail);
		
		//댓글리스트 보여주자 (2016-07-16 댓글페이징 추가)
		PageHelper pageHelper = new PageHelper();
		if(request.getParameter("nowPage") != null){
			pageHelper.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
		}
		if(request.getParameter("movePage") != null){
			pageHelper.setMovePage(Integer.parseInt(request.getParameter("movePage")));
		}
		if(request.getParameter("limitLink") != null){
			pageHelper.setLimitLink(Integer.parseInt(request.getParameter("limitLink")));
		}
		if(request.getParameter("limitList") != null){
			pageHelper.setLimitList(Integer.parseInt(request.getParameter("limitList")));
		}
		
		commentService 			= new CommentService();
		comment = new Comment();
		comment.setItemNo(itemNo);
	//	System.out.println("comment : " + comment);
	
		//보낼때 사용하는 맵 map1
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("pageHelper", pageHelper);
		map1.put("comment", comment);
		
		//받아올때 사용하는 맵 map2
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 = commentService.commentListService(map1);
		//ArrayList<Comment> listComment = commentService.commentListService(comment);
		//System.out.println("listComment : " + listComment);
		//request.setAttribute("listComment", listComment);
		System.out.println("map2 에 잘담겼나? : "+ map2);
		request.setAttribute("map", map2);
	
		HttpSession session	= request.getSession();
		String sessionId	= (String) session.getAttribute("loginId");
		System.out.println("sessionId : " + sessionId);
		request.setAttribute("sessionId", sessionId);
		
		//한 itemNo에 memberId로 한번만 입력할 수 있도록 
		//이미 memberId가 있다면 입력폼을 숨기고, memberId가없다면 그때 입력폼이 보이도록 하자
		//그렇다면 where itemNo and memberId에 해당하는 리스트를 출력하고 출력결과가없으면 입력폼을보여주고 출력결과가 있다면 입력폼을 보이지 않도록해보자
		String memberId = sessionId;
		int result =0;
		comment = new Comment();
		comment.setItemNo(itemNo);
		comment.setMemberId(memberId);
		System.out.println("itemDetailController comment :"+ comment);
		
		result = commentService.commentListServiceByItemNoAndMemberId(comment);
		System.out.println("result : "+ result);
		request.setAttribute("result", result);
		
		//해당아이템의 평균별점을 구하자
		comment = new Comment();
		comment.setItemNo(itemNo);
		int avgStar = 0;
		avgStar = commentService.commentAvgStarService(comment);
		System.out.println("avgStar : " + avgStar);
		request.setAttribute("avgStar", avgStar);
		
		
		RequestDispatcher rd 	= request.getRequestDispatcher("/WEB-INF/Views/item/itemDetail.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
