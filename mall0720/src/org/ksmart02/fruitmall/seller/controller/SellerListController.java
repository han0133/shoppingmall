package org.ksmart02.fruitmall.seller.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.seller.model.Seller;
import org.ksmart02.fruitmall.seller.model.SellerList;
import org.ksmart02.fruitmall.seller.service.SellerService;
import org.ksmart02.fruitmall.util.PageHelper;

/**
 * Servlet implementation class SellerListController
 */
@WebServlet("/SellerListController")
public class SellerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private SellerService sellerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		sellerService 	= new SellerService();
		PageHelper pageHelper = new PageHelper();
		if(request.getParameter("nowPage") != null){
			pageHelper.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
		}
		if(request.getParameter("movePage") != null){
			pageHelper.setMovePage(Integer.parseInt(request.getParameter("movePage")));
		}
		if(request.getParameter("limitLink") != null){
			pageHelper.setLimitLink(Integer.parseInt(request.getParameter("limitLink")));
		}
		if(request.getParameter("limitList") != null){
			pageHelper.setLimitList(Integer.parseInt(request.getParameter("limitList")));
		}
		
		sellerService = new SellerService();
		Map<String, Object> map = sellerService.getSellerList(pageHelper);
		System.out.println(map);
		request.setAttribute("map", map);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/seller/sellerList.jsp");
		rd.forward(request, response);	
	}
}
