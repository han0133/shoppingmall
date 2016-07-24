package org.ksmart02.fruitmall.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.member.service.MemberService;
/**
 * 회원가입 프로그램 컨트롤러
 * @author 201-07
 *
 */
@WebServlet("/JoinMemberController")
public class JoinMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// memberAdd.jsp 로 redirect
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/Views/member/memberAdd.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//memberAdd.jsp 에서 입력한 값을 파라미터로 받아서 insert하는 메서드 호출
	
		
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		member.setMemberName(request.getParameter("memberName"));
		member.setMemberPost(request.getParameter("memberPost"));
		member.setMemberParcleAddr(request.getParameter("memberParcleAddr"));
		member.setMemberRoadAddr(request.getParameter("memberRoadAddr"));
		member.setMemberPhone(request.getParameter("memberPhone")+request.getParameter("memberPhone2")+request.getParameter("memberPhone3"));
	
		memberService = new MemberService();
		memberService.joinMember(member);
		
		request.setAttribute("joined", "joined");
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/Views/member/login.jsp");
		rd.forward(request, response);
	}

}
