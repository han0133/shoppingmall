package org.ksmart02.fruitmall.cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.cart.model.Cart;
import org.ksmart02.fruitmall.cart.model.CartDao;
/**
 * 아이템 상세페이지에서 상품을 장바구니에 담는 컨트롤러
 * @author 안소영
 * @date 2016-07-10
 *
 */
@WebServlet("/PutCartController")
public class PutCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cart cart;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. itemDetail.jsp 에서 넘어온 값을 받습니다.
		 * 2. session에 담긴 id를 변수에 세팅합니다
		 * 3. VO객체에 담습니다
		 * 4. cart 테이블에 insert하는 메서드를 호출합니다
		 */
		
		//1. session에 담긴 아이디를 받습니다
		HttpSession session = request.getSession();
		//1.1 로그인이 되어있지 않으면 아이템 상세페이지로 되돌아 갑니다
		if( session.getAttribute("loginId") == null || session.getAttribute("loginId") == "" ){
			request.setAttribute("noSession", "noSession");
			RequestDispatcher rd	= request.getRequestDispatcher("WEB-INF/Views/item/itemDetail.jsp");
			rd.forward(request, response);
		}else{
			
			String memberId		= (String) session.getAttribute("loginId");
			
			//2. itemDetail.jsp에서 넘어온 값을 받습니다
			int cartCount		= Integer.parseInt(request.getParameter("ordersCount"));
			int itemNo			= Integer.parseInt(request.getParameter("itemNo"));
			
			//3. VO객체에 담습니다
			cart				= new Cart();
			cart.setMemberId(memberId);
			cart.setItemNo(itemNo);
			cart.setCartCount(cartCount);
			
			//4. cart테이블에 insert하는 메서드를 호출합니다
			CartDao cartDao		= new CartDao();
			try {
				cartDao.insertCart(cart);
			} catch (Exception e) {
				System.out.println("cart테이블에 insert 실패");
				e.printStackTrace();
			}
			
			response.sendRedirect("/MyCartListController");
		}
	}
}
