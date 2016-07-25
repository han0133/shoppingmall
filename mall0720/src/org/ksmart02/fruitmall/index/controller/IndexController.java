package org.ksmart02.fruitmall.index.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.item.service.ItemService;

@WebServlet("/")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IndexController 실행");
		System.out.println("오빠 안녕? 오늘도 고마웡");
		
		
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
		doGet(request, response);
	}

}



