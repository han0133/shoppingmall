package org.ksmart02.fruitmall.notice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.ksmart02.fruitmall.notice.model.Notice;
import org.ksmart02.fruitmall.notice.service.NoticeService;
import org.ksmart02.fruitmall.util.PageHelper;


@WebServlet("/NoticeListController")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
ArrayList<Notice> list = new ArrayList<Notice>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		NoticeService noticeService = new NoticeService();
		Map<String, Object> map;
		HttpSession session = request.getSession();
		String memberLevel = (String) session.getAttribute("memberLevel");
		try {
			map = noticeService.selectNotice(pageHelper);
			request.setAttribute("map", map);
			request.setAttribute("memberLevel", memberLevel);
			
		} catch (Exception e) {
			response.sendRedirect("/WEB-INF/Views/error.jsp");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/notice/noticeList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
