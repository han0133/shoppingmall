package org.ksmart02.fruitmall.item.controller;

import java.io.IOException;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.item.service.ItemService;
import org.ksmart02.fruitmall.util.PageHelper;

@WebServlet("/temp")
public class itemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("itemListController의 doGet실행");
		
		/*PageHelper pageHelper = new PageHelper();
		if(request.getParameter("nowPage") != null){
			pageHelper.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
		}
		if(request.getParameter("movePage") != null){
			pageHelper.setMovePage(Integer.parseInt(request.getParameter("movePage")));
		}
		
		pageHelper.setLimitLink(10);
		pageHelper.setLimitList(8);*/
		
		String categoryKeyWord 	= ""; 
		String searchKeyWord 	= "";
		itemService 			= new ItemService();
		
		if(request.getParameter("categoryKeyWord") != null){
			categoryKeyWord 	= request.getParameter("categoryKeyWord");
		}
		if(request.getParameter("searchKeyWord") != null){
			searchKeyWord 		= request.getParameter("searchKeyWord");
		}

		/*Map<String,Object> map = itemService.itemList(categoryKeyWord,searchKeyWord,pageHelper);*/
		Map<String,Object> map = itemService.itemList(categoryKeyWord,searchKeyWord);
		
		System.out.println("itemListController test 2");
		request.setAttribute("map", map);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ItemController의 doPost 실행");
		doGet(request, response);
	}

}
