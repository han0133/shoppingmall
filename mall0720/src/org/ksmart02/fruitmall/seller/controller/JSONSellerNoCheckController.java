package org.ksmart02.fruitmall.seller.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.member.model.MemberDao;
import org.ksmart02.fruitmall.seller.model.SellerDao;

/**
 * Servlet implementation class JSONSellerNoCheckController
 */
@WebServlet("/JSONSellerNoCheckController")
public class JSONSellerNoCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SellerDao sellerDao;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("json/text");
		System.out.println("제이손");
		int sellerNo = 0;
		if(request.getParameter("sellerNo") !=null){
			sellerNo = Integer.parseInt(request.getParameter("sellerNo"));
		}
		sellerDao = new SellerDao();
		String result = null;
		try {
			result = sellerDao.sellerNoCheck(sellerNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		if( result.equals("available sellerNO")){
			System.out.println("없는 판매자번호");
			out.write("{\"result\": \"true\"}");
		}else if( result.equals("unavailable sellerNO")){
			System.out.println("있는 판매자번호");
			out.write("{\"result\": \"false\"}");
		} 
		
	}

	}

