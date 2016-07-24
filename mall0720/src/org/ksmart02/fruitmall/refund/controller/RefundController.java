package org.ksmart02.fruitmall.refund.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ksmart02.fruitmall.refund.model.RefundRequest;
import org.ksmart02.fruitmall.refund.service.RefundService;

/*
 * 환불폼으로 이동하고
 * 환불 프로그램을 실행하는 컨트롤러
 * @ 안소영 2016-07-10
 */
@WebServlet("/RefundController")
public class RefundController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RefundService refundService;
	
	//requestRefund.jsp에서 넘어온 값을 받아서 환불 프로그램을 실행합니다
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. requestRefudn.jsp에서 넘어온 ordersNo, refundMoney, refundReason을 받는다
		 * 2. 세션에 담긴 아이디를 String으로 받는다
		 * 3. 값들을 refundRequest라는 VO 객체에 담아  
		 * 3-1. service의 refundProcess 메서드의 매개변수로 넘긴다
		 * 4. service단에서 [refund 테이블에 insert하는 작업]과   [orders 테이블의 해당 row의 orders_refund의 값을 update하는 작업]을 수행한다
		 * 5. service단에서 두 작업을 transaction 건다
		 */
		
		// 1. requestRefund.jsp에서 넘어온 값 받기
		int ordersNo		= Integer.parseInt(request.getParameter("ordersNo"));
		int refundMoney		= Integer.parseInt(request.getParameter("refundMoney"));
		String refundReason	= request.getParameter("refundReason");
		
		// 2. 세션에 담긴 아이디 받기
		HttpSession session = request.getSession();
		String memberId		= (String) session.getAttribute("loginId");
		
		// 3. VO 객체에 값 세팅하기
		RefundRequest refundRequest = new RefundRequest();
		refundRequest.setMemberId(memberId);
		refundRequest.setOrdersNo(ordersNo);
		refundRequest.setRefundReason(refundReason);
		refundRequest.setRefundMoney(refundMoney);
		
		// 3-1. service의 메서드 호출하기
		refundService = new RefundService();
		try {
			refundService.refundProcess(refundRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/");
	}
	
	//orderListPresent.jsp에서 넘어온 환불 요청을 받고 환불 form으로 이동합니다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ordersNo = Integer.parseInt(request.getParameter("ordersNo"));
		int refundMoney = Integer.parseInt(request.getParameter("refundMoney"));
		
		request.setAttribute("ordersNo", ordersNo);
		request.setAttribute("refundMoney", refundMoney);
		RequestDispatcher rd	= request.getRequestDispatcher("/WEB-INF/Views/refund/requestRefund.jsp");
		rd.forward(request, response);
	}
}
