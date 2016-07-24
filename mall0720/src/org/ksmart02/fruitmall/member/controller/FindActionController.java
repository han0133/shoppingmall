package org.ksmart02.fruitmall.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.member.model.MemberDao;

/**
 * 아이디와 비밀번호를 찾는 컨트롤러입니다
 * @author 안소영
 * @date 2016-07-13
 */
@WebServlet("/FindActionController")
public class FindActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDao memberDao;

	//비밀번호를 찾는 메서드입니다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FindActionController의 doGet");
		
		String memberId		= request.getParameter("memberId");
		String memberPhone	= request.getParameter("memberPhone");
		
		Member member		= new Member();
		member.setMemberId(memberId);
		member.setMemberPhone(memberPhone);
		
		memberDao			= new MemberDao();
		String pw			= null;
		try {
			pw				= memberDao.findPw(member);
		} catch (Exception e) {
			System.out.println("비밀번호 찾기 메서드 예외 발생");
			e.printStackTrace();
		}
		
		request.setAttribute("pw", pw);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/myPage/findAction.jsp");
		rd.forward(request, response);
	}
	
	//아이디를 찾는 메서드입니다
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FindActionController의 doPost");
		
		String memberName		= request.getParameter("memberName");
		String memberPhone	= request.getParameter("memberPhone");
		
		Member member		= new Member();
		member.setMemberName(memberName);
		member.setMemberPhone(memberPhone);
		
		memberDao			= new MemberDao();
		String id			= null;
		try {
			id				= memberDao.findId(member);
		} catch (Exception e) {
			System.out.println("아이디 찾기 메서드 예외 발생");
			e.printStackTrace();
		}
		
		request.setAttribute("id", id);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/myPage/findAction.jsp");
		rd.forward(request, response);
	}

}
