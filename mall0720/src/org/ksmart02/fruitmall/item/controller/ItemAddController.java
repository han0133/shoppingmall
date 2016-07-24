package org.ksmart02.fruitmall.item.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.item.model.ItemDomain;
import org.ksmart02.fruitmall.item.service.ItemService;
import org.ksmart02.fruitmall.seller.model.Seller;

import com.oreilly.servlet.MultipartFilter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/ItemAddController")
public class ItemAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/item/itemAdd.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		itemService 				= new ItemService();
		Item item 					= new Item(); 
	
		String root 				= request.getSession().getServletContext().getRealPath("/");
		System.out.println(root);
		String pathname 			= root + "img";
		int maxSize 				= 1024*1024*5;
		String encType 				= "utf-8";
		File f1						= new File(pathname);
		
		//파일이없으면 파일 생성
		if (!f1.exists()) {
			f1.mkdirs();
			}
		try{
			MultipartRequest multi 	= new MultipartRequest(request, pathname, maxSize, encType, new DefaultFileRenamePolicy());
			File itemImagePath 		= multi.getFile("itemImage");
		//	System.out.println		(itemImagePath+"파일경로"); // 첨부된 파일의 전체경로
			
			item.setSellerNo		(Integer.parseInt(multi.getParameter("sellerNo")));
			item.setItemName		(multi.getParameter("itemName"));
			item.setItemCategory	(multi.getParameter("itemCategory"));
			item.setItemDetail		(multi.getParameter("itemDetail"));
			item.setItemImage		(itemImagePath.getAbsolutePath());
			item.setItemPrice		(Integer.parseInt(multi.getParameter("itemPrice")));
			item.setItemOrigin		(multi.getParameter("itemOrigin"));
			item.setItemQuantity	(Integer.parseInt(multi.getParameter("itemQuantity")));
			item.setItemHarvest		(multi.getParameter("itemHarvest"));
			item.setItemStock		(Integer.parseInt(multi.getParameter("itemStock")));
			
		//	System.out.println		(item);
		 } catch(Exception e) {
		  e.printStackTrace();
		 }

		int result = itemService.addItem(item);
		
		response.sendRedirect("/ItemListController");
	}

}
