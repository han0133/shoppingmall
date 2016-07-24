package org.ksmart02.fruitmall.orders.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.orders.model.OrdersDao;
/**
 * 과거 주문내역을 보여주는 컨트롤러입니다
 * @author 안소영
 * @date 2016-07-09
 *
 */
@WebServlet("/OrderListPastController")
public class OrderListPastController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrdersDao ordersDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//과거 주문 내역을 보여줍니다
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LogInController의 doProcess 실행");
		
		//세션 받아서 memberId에 담기
		HttpSession session	= request.getSession();
		String memberId		= (String) session.getAttribute("loginId");
		
		Member member = new Member();
		member.setMemberId(memberId);
		
		ArrayList<Map> list = new ArrayList<Map>();
		ordersDao				= new OrdersDao();
		list 					= ordersDao.selectOrdersPastByMemberId(member);
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd	= request.getRequestDispatcher("/WEB-INF/Views/orders/ordersListPast.jsp");
		rd.forward(request, response);
			
		}
}
