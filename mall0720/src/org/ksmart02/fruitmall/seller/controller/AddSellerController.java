package org.ksmart02.fruitmall.seller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.seller.model.Seller;
import org.ksmart02.fruitmall.seller.service.SellerService;


@WebServlet("/AddSellerController")
public class AddSellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService sellerService;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/seller/addSeller.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Seller seller = new Seller();
		sellerService = new SellerService();
		
		seller.setSellerName(request.getParameter("sellerName"));
		seller.setSellerAddr(request.getParameter("sellerAddr"));
		seller.setSellerPhone(request.getParameter("sellerPhone"));
		
		int result = sellerService.addSeller(seller);
		
		if(result == 1){
			response.sendRedirect("/IndexController");
		}else{
			response.sendRedirect("/AddSellerController");
		}
	}

}
