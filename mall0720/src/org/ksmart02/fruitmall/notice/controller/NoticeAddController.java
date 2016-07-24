package org.ksmart02.fruitmall.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.notice.model.Notice;
import org.ksmart02.fruitmall.notice.service.NoticeService;

/**
 * 공지사항을 추가하는 컨트롤러
 * @author 박종무
 *
 */
@WebServlet("/NoticeAddController")
public class NoticeAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NoticeService noticeService;
	Notice notice;
	Member member;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NoticeAddController 실행");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/notice/noticeForm.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NoticeAddController의 doPost");
		
		notice			= new Notice();
		noticeService	= new NoticeService();
		member			= new Member();
		notice.setNoticeTitle(request.getParameter("noticeTitle"));
		notice.setNoticeContent(request.getParameter("noticeContent"));
	
		HttpSession session = request.getSession();
		
		member.setMemberId((String) session.getAttribute("loginId"));
		notice.setMember(member);
		
		try {
			noticeService.insertNotice(notice);
		} catch (Exception e) {
			response.sendRedirect("/WEB-INF/Views/error.jsp");
			e.printStackTrace();
		}
		
		response.sendRedirect("/NoticeList");
	}

}
