package org.ksmart02.fruitmall.orders.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.member.model.MemberDao;
/**
 * 주문form에서 본인 정보와 동일에 체크하면 기존 정보를 불러옵니다
 * @author 안소영
 *
 */
@WebServlet("/GetMemberInfoForOrderController")
public class GetMemberInfoForOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDao memberDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetMemberInfoForOrderController실행..");
		
		memberDao = new MemberDao();
		String memberId	= request.getParameter("memberId");
	//	System.out.println("memberID: "+memberId);
		
		try {
		//	System.out.println("try절 실행");
			Member member			= new Member();
			member					= memberDao.selectMember(memberId);

			String memberName		= member.getMemberName();
			String memberPost		= member.getMemberPost();
			String memberRoadAddr	= member.getMemberRoadAddr();
			String memberParcleAddr	= member.getMemberParcleAddr();
			String memberPhone		= member.getMemberPhone();
			
		//	System.out.println("여기:"+member.toString());
			
			response.setContentType("json/text");
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			out.write("{\"memberName\": \""+memberName+"\", \"memberPost\": \""+memberPost+"\", \"memberRoadAddr\": \""+memberRoadAddr+"\", \"memberParcleAddr\": \""+memberParcleAddr+"\", \"memberPhone\": \""+memberPhone+"\"}");
			
		} catch (Exception e) {
			System.out.println("GetMemberInfoForOrder Controller에서 예외 발생");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
