package org.ksmart02.fruitmall.orders.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.orders.model.OrdersRequest;
import org.ksmart02.fruitmall.orders.service.OrdersService;
import org.ksmart02.fruitmall.cart.model.Cart;
import org.ksmart02.fruitmall.item.model.ItemRequest;
import org.ksmart02.fruitmall.member.model.Member;

/**
 * 상품을 주문하는 컨트롤러
 * @author 안소영 
 * @date 2016-07-09
 *
 */
@WebServlet("/OrderItemController")
public class OrderItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdersService ordersService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//itemNo와 ordersCount를 가지고 주문하기 페이지로 forward
		System.out.println("OrderItemController의 doGet 실행..");
		
		String[] itemNoList		= request.getParameterValues("itemNo");
		request.setAttribute("itemNoList", itemNoList);
		String[] ordersCount	= request.getParameterValues("ordersCount");
		request.setAttribute("ordersCount", ordersCount);
		String[] itemPrice	= request.getParameterValues("itemPrice");
		request.setAttribute("itemPrice", itemPrice);
		//cartNo가 있다면 request에 세팅합니다
		if( request.getParameterValues("checkedItem") != null ){
			String[] cartNo			= request.getParameterValues("checkedItem");
			request.setAttribute("cartNo", cartNo);
		}
		
		HttpSession session		= request.getSession();
		String memberId			= (String) session.getAttribute("loginId");
		request.setAttribute("memberId", memberId);
		 
		RequestDispatcher rd	= request.getRequestDispatcher("/WEB-INF/Views/orders/ordersForm.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//주문 form에서 값을 받아 dao의 메서드 호출
		System.out.println("OrderItemController의 doPost 실행..");
		
		//1. 세션 받아서 memberId에 담기
		HttpSession session				= request.getSession();
		String memberId					= (String) session.getAttribute("loginId");
		
		
		//2. itemNo(상품번호)와 ordersCount(수량)를 배열에 담기
		Object[] itemNoListTemp			= request.getParameterValues("itemNoList");
	
		int[] itemNoList				= new int[itemNoListTemp.length];
		
		for(int i = 0; i<itemNoListTemp.length; i++){
			itemNoList[i] 				=  Integer.parseInt((String) itemNoListTemp[i]);
		}
		
		Object[] ordersCountListTemp	= request.getParameterValues("ordersCount");
		int[] ordersCountList			= new int[ordersCountListTemp.length];
		
		for(int i = 0; i<ordersCountListTemp.length; i++){
			ordersCountList[i] 			=  Integer.parseInt((String) ordersCountListTemp[i]);
		}
		
		Object[] itemPriceListTemp	= request.getParameterValues("itemPrice");
		System.out.println("여기::: itemPriceListTemp:: "+itemPriceListTemp[0]);
		int[] itemPriceList			= new int[itemPriceListTemp.length];
		System.out.println("여기::: itemPriceList:: "+itemPriceList[0]);
		
		for(int i = 0; i<itemPriceListTemp.length; i++){
			itemPriceList[i] 			=  Integer.parseInt((String) itemPriceListTemp[i]);
		}
		
		//3. 넘어온 다른 값들을 받는다
		String recipientName			= request.getParameter("recipientName");
		int recipientPost 				= Integer.parseInt(request.getParameter("recipientPost"));
		String recipientParcelAddr 		= request.getParameter("recipientParcelAddr");
		String recipientRoadAddr 		= request.getParameter("recipientRoadAddr");
		String recipientPhone 			= request.getParameter("recipientPhone");
		String paymentMethod 			= request.getParameter("paymentMethod");
		
		//4. 받은 값들을 OrdersRequest에 담는다
		OrdersRequest ordersRequest 	= new OrdersRequest();
		ItemRequest itemRequest			= new ItemRequest();
		itemRequest.setItemNo(itemNoList);
		ordersRequest.setItemRequest(itemRequest);
		
		Member member = new Member();
		member.setMemberId(memberId);
		ordersRequest.setMember(member);
		
		ordersRequest.setOrdersTotalPrice(itemPriceList);
		ordersRequest.setOrdersCount(ordersCountList);
		ordersRequest.setOrdersRecipientName(recipientName);
		ordersRequest.setOrdersRecipientPhone(recipientPhone);
		ordersRequest.setOrdersRecipientPost(recipientPost);
		ordersRequest.setOrdersRecipientParceladdr(recipientParcelAddr);
		ordersRequest.setOrdersRecipientRoadAddr(recipientRoadAddr);
		ordersRequest.setOrdersPaymentMethod(paymentMethod);
		
		//5. 장바구니에서 주문할 때는 cartNo가 넘어옵니다. 넘어오면 받아서 ordersRequest객체에 cart에 담는다
		
		if( request.getParameterValues("cartNo") != null ){
			String[] checkedCartNoTemp	= request.getParameterValues("cartNo");
			int[] checkedCartNo			= new int[checkedCartNoTemp.length];
			for(int i=0; i<checkedCartNoTemp.length; i++){
				checkedCartNo[i] = Integer.parseInt(checkedCartNoTemp[i]); 
			}
			ordersRequest.setCart(checkedCartNo);
		}
		
		try {
			ordersService = new OrdersService();
			ordersService.orderItems(ordersRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/OrderListPresentController");
		
	}

}
