package org.ksmart02.fruitmall.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.member.model.MemberRequest;
import org.ksmart02.fruitmall.member.service.MemberService;
/**
 * 나의 정보를 수정하는 컨트롤러
 * @author 박종무
 *
 */
@WebServlet("/myInfoModifyController")
public class MyInfoModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("myInfoModifyController의 실행");
		
		MemberService memberService = new MemberService();
		MemberRequest memberRequest = new MemberRequest();
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("loginId");
		
		memberRequest = memberService.modifyForSelectMember(memberId);
		request.setAttribute("memberRequest", memberRequest);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/myPage/myInfoModify.jsp");
		
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = new Member();
		MemberService memberService = new MemberService();
		HttpSession session = request.getSession();
		
		//memberId 꼭 세션에서 받아야함(박종무)
		String memberId = (String) session.getAttribute("loginId");
		member.setMemberId(memberId);
	//	member.setMemberId(request.getParameter("memberId"));
		member.setMemberName(request.getParameter("memberName"));
		member.setMemberPw(request.getParameter("memberPw"));
		member.setMemberPhone(request.getParameter("memberPhone"));
//		+request.getParameter("memberPhone2")+request.getParameter("memberPhone3") 
		member.setMemberPost(request.getParameter("memberPost"));
		member.setMemberRoadAddr(request.getParameter("memberRoadAddr"));
		member.setMemberParcleAddr(request.getParameter("memberParcleAddr"));
		
		memberService.modifyMember(member);
		
	/*	System.out.println(member.getMemberId());
		System.out.println(member.getMemberName());
		System.out.println(member.getMemberParcleAddr());
		System.out.println(member.getMemberPhone());
		System.out.println(member.getMemberPost());
		System.out.println(member.getMemberPw());
		System.out.println(member.getMemberRoadAddr());*/
		
		response.sendRedirect("/MyPageController");

	}

}
