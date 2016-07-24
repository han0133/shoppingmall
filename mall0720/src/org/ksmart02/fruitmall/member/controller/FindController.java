package org.ksmart02.fruitmall.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 아이디를 찾는 find.jsp로 가는 컨트롤러입니다
 * @author 안소영
 * @date 2016-07-13
 */
@WebServlet("/FindController")
public class FindController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FindIdController 실행");
		
		RequestDispatcher rd	= request.getRequestDispatcher("/WEB-INF/Views/myPage/find.jsp");
		rd.forward(request, response);
	}

}
