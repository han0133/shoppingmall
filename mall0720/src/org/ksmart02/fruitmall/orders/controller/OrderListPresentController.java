package org.ksmart02.fruitmall.orders.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.ksmart02.fruitmall.orders.service.OrdersService;
/**
 * 현재 주문한 상품을 보여주는 컨트롤러
 * @author 안소영
 * @date 2016-07-09
 *
 */
@WebServlet("/OrderListPresentController")
public class OrderListPresentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdersDao ordersDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderListPresentController의 doProcess 실행");
		//주문 내역을 보여줍니다
		
		//세션 받아서 memberId에 담기
		HttpSession session	= request.getSession();
		String memberId		= (String) session.getAttribute("loginId");
		System.out.println(memberId+"ID이올시다");
		Member member = new Member();
		member.setMemberId(memberId);
		
		List<Map> list = new ArrayList<Map>();
		
		OrdersService ordersService	= new OrdersService();
		list 					= ordersService.checkRefund(member);
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd	= request.getRequestDispatcher("/WEB-INF/Views/orders/ordersListPresent.jsp");
		rd.forward(request, response);
		
	}

}
