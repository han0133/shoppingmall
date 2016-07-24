package org.ksmart02.fruitmall.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.cart.model.Cart;
import org.ksmart02.fruitmall.cart.model.CartRequest;
import org.ksmart02.fruitmall.cart.service.CartService;


/**
 * 장바구니에 담긴 상품 리스트를 보여줍니다
 * @author 201-07
 *
 */
@WebServlet("/MyCartListController")
public class MyCartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	
	Cart cart;
	CartService cartService;
	//0709효민 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyCartListController의 doGet실행");
		
		// 세션에 담긴 아이디 받기
		HttpSession session = request.getSession();
		String memberId		= (String) session.getAttribute("loginId");
		
		cartService = new CartService();
		
		if(request.getParameter("memberId") != null) {
			memberId = request.getParameter("memberId");
		}
		
		ArrayList<CartRequest> cartList;
		
		try {
			cartList = cartService.cartList(memberId);
			request.setAttribute("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/cart/cartList.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
