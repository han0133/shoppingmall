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

import org.ksmart02.fruitmall.seller.service.SellerService;
import org.ksmart02.fruitmall.util.PageHelper;

@WebServlet("/SellerItemListController")
public class SellerItemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService sellerService;
	/**
	 * get으로 요청 받아서 request에서 sellerNo을 받아서
	 * sellerService로 넘겨줍니다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int sellerNo 	= Integer.parseInt(request.getParameter("sellerNo"));
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
		
		Map<String, Object> map 	= sellerService.sellerItemList(sellerNo,pageHelper);
		
		request.setAttribute("map", map);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/seller/sellerItemList.jsp");
		rd.forward(request, response);
	}
}
