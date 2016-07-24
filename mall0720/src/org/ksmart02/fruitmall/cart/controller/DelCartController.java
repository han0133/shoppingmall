package org.ksmart02.fruitmall.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.cart.model.Cart;
import org.ksmart02.fruitmall.cart.service.CartService;

/**
 * 0716 박효민
 * 장바구니에서 체크된 아이템을 삭제하는 컨트롤러
 */
@WebServlet("/DelCartController")
public class DelCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartService cartService;
	Cart cart;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//카트에 담긴 아이템을 삭제하는 프로그램
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DelCartController 실행..");
		
		String[] checkedItems = request.getParameterValues("checkedItem");
	
		ArrayList<Cart> cartList;
		cartService = new CartService();
		
		cartList	= cartService.cartDelService(checkedItems);
		request.setAttribute("cartList", cartList);
		
		response.sendRedirect("/MyCartListController");
	}
}

