package org.ksmart02.fruitmall.member.controller;

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
 * 아이디 중복검사 컨트롤러
 * @author 201-07
 *
 */
@WebServlet("/JSONIDCheckController")
public class JSONIDCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDao memberDao;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("json/text");
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		
		memberDao = new MemberDao();
		String result = null;
		try {
			result = memberDao.IdCheck(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		if( result.equals("available ID")){
			System.out.println("사용가능 아이디");
			out.write("{\"result\": \"true\"}");
		}else if( result.equals("unavailable ID")){
			System.out.println("사용불가 아이디");
			out.write("{\"result\": \"false\"}");
		} 
		
	}

}
