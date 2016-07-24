package org.ksmart02.fruitmall.item.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.item.service.ItemService;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 최초 이한녕
 */
@WebServlet("/ItemModfiyController")
public class ItemModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ItemService itemService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		itemService 				= new ItemService();
		Item itemDetail 			= new Item(); 
		
		int itemNo =				Integer.parseInt(request.getParameter("itemNo"));
		
		itemDetail = itemService.itemDetail(itemNo);
		
		
		request.setAttribute("itemDetail", itemDetail);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/item/itemModify.jsp");
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
		File f						= new File(pathname);
		
	//	System.out.println(request.getParameter("itemOut")+"뭐야이거");
		//파일이없으면 파일 생성
		if (!f.exists()) {
			f.mkdirs();
			}
		try{
			MultipartRequest multi 	= new MultipartRequest(request, pathname, maxSize, encType, new DefaultFileRenamePolicy());
			File itemImagePath = null;
			if(multi.getFile("itemImage") !=null){
				itemImagePath 		= multi.getFile("itemImage");
				item.setItemImage	(itemImagePath.getAbsolutePath());
			}else{
				item.setItemImage	(multi.getParameter("itemImageModify"));
			}
			System.out.println		(itemImagePath+"파일경로"); // 첨부된 파일의 전체경로
			
			item.setItemNo			(Integer.parseInt(multi.getParameter("itemNo")));
			item.setSellerNo		(Integer.parseInt(multi.getParameter("sellerNo")));
			item.setItemName		(multi.getParameter("itemName"));
			item.setItemCategory	(multi.getParameter("itemCategory"));
			item.setItemDetail		(multi.getParameter("itemDetail"));
			item.setItemPrice		(Integer.parseInt(multi.getParameter("itemPrice")));
			item.setItemOrigin		(multi.getParameter("itemOrigin"));
			item.setItemQuantity	(Integer.parseInt(multi.getParameter("itemQuantity")));
			item.setItemHarvest		(multi.getParameter("itemHarvest"));
			item.setItemStock		(Integer.parseInt(multi.getParameter("itemStock")));
			item.setItemOut			(multi.getParameter("itemOut"));
			
			System.out.println		(item);
		 } catch(Exception e) {
		  e.printStackTrace();
		 }
		System.out.println		(item);
		itemService.modifyItem(item);
		
		

		if(item.getItemOut() == "Y"){
			response.sendRedirect("/ItemListController");
			
		}else{
		 response.sendRedirect("/ItemDetailController?itemNo="+item.getItemNo());
	
		}
	}

}
