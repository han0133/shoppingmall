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

/**
 * 공지사항을 수정하는 컨트롤러
 * 박종무
 */
@WebServlet("/NoticeModifyController")
public class NoticeModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NoticeModifyController 호출");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		Notice notice = new Notice();
		NoticeService noticeService = new NoticeService();
		try {
			notice = noticeService.selectNoticeDetail(noticeNo);
		} catch (Exception e) {
			response.sendRedirect("/WEB-INF/Views/error.jsp");
			e.printStackTrace();
		}
		request.setAttribute("notice", notice);
	
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Views/notice/modifyForm.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NoticeModifyController 호출");
		
		Notice notice = new Notice();
		NoticeService noticeService = new NoticeService();
		
		notice.setNoticeNo(Integer.parseInt(request.getParameter("noticeNo")));
		notice.setNoticeContent(request.getParameter("noticeContent"));
		notice.setNoticeTitle(request.getParameter("noticeTitle"));
		
		try {
			noticeService.updateNoticeDetail(notice);
		} catch (Exception e) {
			response.sendRedirect("/WEB-INF/Views/error.jsp");
			e.printStackTrace();
		}
		response.sendRedirect("/NoticeList");
	}
}
