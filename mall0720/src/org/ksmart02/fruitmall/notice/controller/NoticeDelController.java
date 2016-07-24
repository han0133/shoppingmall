package org.ksmart02.fruitmall.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.notice.service.NoticeService;

/**
 * 공지사항을 삭제하는 컨트롤러
 * @author 박종무
 *
 */
@WebServlet("/NoticeDelController")
public class NoticeDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NoticeDel 실행");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeService noticeService = new NoticeService(); 
		try {
			noticeService.delNoticeDetail(noticeNo);
		} catch (Exception e) {
			response.sendRedirect("/WEB-INF/Views/error.jsp");
			e.printStackTrace();
		}
		
		response.sendRedirect("/NoticeList");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
