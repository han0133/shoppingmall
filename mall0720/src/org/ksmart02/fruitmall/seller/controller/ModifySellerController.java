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

import jdk.nashorn.internal.ir.RuntimeNode.Request;


@WebServlet("/ModifySellerController")
public class ModifySellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SellerService sellerService;   
   /**
    * 한명 판매자 정보를 리턴하는 doGet입니다.
    * 
    * request 안에 sellerNo을 받아서 selectSeller 메서드 매개변수로주고
    * seller변수에 담습니다
    * seller 변수를 request에 setTing 하고 포워딩 합니다
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sellerService = new SellerService();
	//	System.out.println("여기:::::::::::::: ");
		Seller seller = sellerService.selectSeller(Integer.parseInt(request.getParameter("sellerNo")));
		
		request.setAttribute("seller", seller);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/seller/modifySeller.jsp");
		rd.forward(request, response);
	}

	/**
	 * 한명 판매자 정보를 입력받아서 수정 하는 doPost입니다
	 * 
	 * form에서 넘겨받은 sellerNo,sellerName,sellerAddr,sellerPhone을 seller객체에담아서
	 * modifySeller 메소드의 매개변수로 주고
	 * 성공여부를 int 값으로 받아옵니다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sellerService = new SellerService();
		
		Seller seller = new Seller();
		seller.setSellerNo(Integer.parseInt(request.getParameter("sellerNo")));
		seller.setSellerAddr(request.getParameter("sellerAddr"));
		seller.setSellerPhone(request.getParameter("sellerPhone"));
		System.out.println(seller);
		int result = sellerService.modifySeller(seller);
		
		if(result == 1){
			response.sendRedirect("/SellerListController");
		}else{
			response.sendRedirect("/ModifySellerController?sellerNo="+seller.getSellerNo());
		}
		
		
	}

}
