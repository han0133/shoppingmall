package org.ksmart02.fruitmall.orders.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.orders.model.OrdersDao;
/*
 * 주문 상세를 보여주는 컨트롤러입니다
 * 안소영 2016-07-10
 */
@WebServlet("/ShowOrderDetailController")
public class ShowOrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersDao ordersDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ShowOrderDetailController의 doGet 실행");
		
		//1. orderListPast.jsp에서 넘어온 값을 받습니다
		int ordersNo = Integer.parseInt(request.getParameter("ordersNo"));
		
		//2. ordersNo로 orders 테이블을 select하는 메서드를 호출합니다
		ordersDao							= new OrdersDao();
		Map<String, Object> orderDetailMap 	= new HashMap<String, Object>();
		try {
			orderDetailMap					= ordersDao.selectOrdersByOrdersNo(ordersNo);
		} catch (Exception e) {
			System.out.println("ShowOrderDetailController의 selectOrdersByOrdersNo 메서드 호출 실패");
			e.printStackTrace();
		}
		
		request.setAttribute("orderDetailMap", orderDetailMap);
		RequestDispatcher rd				= request.getRequestDispatcher("/WEB-INF/Views/orders/orderDetail.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
