package org.ksmart02.fruitmall.item.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.item.service.ItemService;

/**
 * Servlet implementation class ItemDeleteController
 */
@WebServlet("/ItemDeleteController")
public class ItemDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ItemService itemService;   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		itemService = new ItemService();
		Item item = new Item();
		item.setItemNo(Integer.parseInt(request.getParameter("itemNo")));
		item.setItemOut("Y");
		
		System.out.println(item);
		itemService.itemDelete(item);
		
		response.sendRedirect("/ItemListController");
	}

}
