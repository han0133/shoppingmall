package org.ksmart02.fruitmall.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.member.model.MemberDao;
/**
 * 로그인 프로그램 컨트롤러
 * @author 201-07
 *
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LogInController의 doGet 실행");
		
		//원래 sendRedirect를 써줘야 하는데 자꾸 404 에러가 난다.
		//response.sendRedirect("/WEB-INF/Views/member/login.jsp");
		
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/Views/member/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LogInController의 doPost 실행");
		Map<String, Object> map = new HashMap<String, Object>();
		//login.jsp에서 아이디와 비밀번호를 받는다
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberLevel = "";
		//MemberDao의 selectMemberByIdandPw 메서드 호출
		MemberDao memberDao = new MemberDao();
		HttpSession session = request.getSession();
		//메서드의 return을 받기위해 String 변수 선언
		String result = "";
		try {
			map		= memberDao.selectMemberByIdandPw(memberId, memberPw);
			
			memberLevel = (String) map.get("memberLevel");
			result = (String) map.get("result");
			System.out.println("result : "+result);
			System.out.println("memberLevel");
		} catch (Exception e) {
			System.out.println("로그인 메서드 오류 발생");
			e.printStackTrace();
		}
		
		//로그인 성공시 session에 아이디 담음
		if(result == "success"){
			session.setAttribute("loginId", memberId);
			session.setAttribute("memberLevel", memberLevel);
		}
		
		response.sendRedirect("/");
		
	}

}
