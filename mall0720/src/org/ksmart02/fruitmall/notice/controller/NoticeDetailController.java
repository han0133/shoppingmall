package org.ksmart02.fruitmall.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.notice.model.Notice;
import org.ksmart02.fruitmall.notice.service.NoticeService;
/*
 * 공지사항의 내용을 보여주는 컨트롤러
 * 박종무
 */
@WebServlet("/NoticeDetailController")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Notice notice;
	private NoticeService noticeService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NoticeDetail 컨트롤러 실행");
		
		noticeService	= new NoticeService();
		notice 			= new Notice();
		int noticeNo 	= Integer.parseInt(request.getParameter("noticeNo"));
		
		try {
			notice = noticeService.selectNoticeDetail(noticeNo);
		} catch (Exception e) {
			response.sendRedirect("/WEB-INF/Views/error.jsp");
			e.printStackTrace();
		}
		request.setAttribute("notice", notice);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/notice/noticeDetail.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
