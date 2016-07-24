package org.ksmart02.fruitmall.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.member.model.MemberDao;

/*
 * 0716 박종무
 * 아이디 비번이 존재하는지 유무를 판단하는 컨트롤러
 */
 
@WebServlet("/JSONIdPwCheckController")
public class JSONIdPwCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDao memberDao;   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("json/text");
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("memberId");
		String pw = request.getParameter("memberPw");
		String result = null;		
		memberDao= new MemberDao();
		
		
		try {
			map = memberDao.selectMemberByIdandPw(id, pw);
			result = (String) map.get("result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		
		//0716 박종무(수정)
		if(result.equals("success")){
			out.write("{\"result\": \"true\"}");
			System.out.println("아이디 비번이 존재 함");
		}else{
			out.write("{\"result\": \"false\"}");
			System.out.println("아이디 비번이 존재하지 않음");
		}
			
		
	}

}
