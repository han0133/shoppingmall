package org.ksmart02.fruitmall.orders.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.orders.model.OrdersDao;
/*
 * 고객이 구매확정을 할 수 있게 합니다
 * 0713 안소영
 */
@WebServlet("/OrderConfirmController")
public class OrderConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderConfirmController 실행");
		
		//1. ordersListPresent.jsp에서 넘어온 값을 받습니다
		int ordersNo	= Integer.parseInt(request.getParameter("ordersNo"));
		
		//2. 구매확정을 실행하는 메서드 호출
		OrdersDao orderDao	= new OrdersDao();
		try {
			orderDao.updateOrdersConfirm(ordersNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/OrderListPresentController");
	}
}
