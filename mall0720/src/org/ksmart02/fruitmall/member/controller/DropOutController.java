package org.ksmart02.fruitmall.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.member.service.MemberService;
/**
 * 탈퇴 프로그램 컨트롤러입니다
 * @author 201-07
 *
 */
@WebServlet("/DropOutController")
public class DropOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	/**
	 * get방식으로 요청이 오면 session이 null인지 확인한 후에 
	 * 회원탈퇴 화면으로 갑니다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginId")!= null){
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/myPage/dropOut.jsp");
			rd.forward(request, response);
		}else{			
			response.sendRedirect("/");
		}
	}
	/**
	 * post방식으로 요청이 오면 session에서 memberId를 꺼내고
	 * request에서 입력받은 memberPw를 꺼냅니다.
	 * memberService의 dropOutMember 메소드 에 매개변수로 id,pw 를 넣고 결과를
	 * result에 담습니다.
	 * 성공했다면 session영역을 비워주고 index로 redirect하고
	 * 
	 * 실패했다면 다시 삭제 화면으로 돌아갑니다
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId 	= (String) session.getAttribute("loginId");
		String memberPw 	= request.getParameter("memberPw");
		memberService 		= new MemberService();
		int result 			= memberService.dropOutMember(memberId,memberPw);
		
		if(result!= 0){
			session.invalidate();
			response.sendRedirect("/");
		}else{
			response.sendRedirect("/DropOutController");
		}	
		
	}

}
